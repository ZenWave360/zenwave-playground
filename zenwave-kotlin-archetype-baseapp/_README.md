# ZenWave Kotlin Archetype Baseapp

#[[
A Kotlin + Spring Boot service created from the ZenWave archetype. Its structure is generated from a ZenWave domain model (ZDL), and the behavior the generator leaves out is completed with the zenwave-sdd agent skills and GitHub Spec Kit.

1. [Getting Started](#getting-started)
2. [Prerequisites](#prerequisites)
3. [Technologies](#technologies)
4. [Install the ZenWave tooling](#install-the-zenwave-tooling)
5. [Code Generation](#code-generation)
   1. [From the domain model: zenwave-scripts.zw](#from-the-domain-model-zenwave-scriptszw)
   2. [At build time: OpenAPI and AsyncAPI (Maven)](#at-build-time-openapi-and-asyncapi-maven)
6. [Spec-Driven Development with zenwave-sdd](#spec-driven-development-with-zenwave-sdd)
   1. [Install the skills and Spec Kit](#install-the-skills-and-spec-kit)
   2. [Run the skills](#run-the-skills)

## Getting Started

```bash
docker-compose up -d
mvn spring-boot:run
```

SwaggerUI: http://localhost:8080/swagger-ui/index.html

## Prerequisites

* Java 25+ (Kotlin sources, compiled with `kotlin-maven-plugin`)
* Maven 3.6+
* Docker / Docker Compose (local infrastructure and integration tests)
* Git and Git Bash (all commands below are Git Bash)
* JBang and the ZenWave CLI (`jbang zw`), see [Install the ZenWave tooling](#install-the-zenwave-tooling)
* For spec-driven development: `uv` (to install Spec Kit) and an AI coding agent (Claude Code, Codex, Grok…)
* SDKMAN! (optional, recommended for Java and Maven)

## Technologies

* Kotlin and Spring Boot 4.0.x
* Spring Data JPA or MongoDB
* Spring Cloud Stream for Kafka (or other brokers), Spring Modulith (optional)
* Spring Security
* ZenWave SDK for domain modeling and code generation

## Install the ZenWave tooling

Follow **[ZenWave Getting Started](https://www.zenwave360.io/docs/getting-started/)**. It covers installing the ZenWave CLI with JBang and the IDE plugins for editing `.zdl` and `.zw` files and running the generators. Quick reference for the CLI:

```bash
jbang alias add --fresh --force --name=zw release@zenwave360/zenwave-sdk
jbang zw --help list
```

## Code Generation

Code comes from two places: the ZenWave SDK, run from `zenwave-scripts.zw` when the domain model changes, and Maven plugins that run on every build for the API contracts.

### From the domain model: zenwave-scripts.zw

The project ships with two files:

* `zenwave-model.zdl`: the domain model. It's a sample: replace it with your own, or import a remote, governed version of the model.
* `zenwave-scripts.zw`: the ZenWave plugins to run, with their configuration.

The plugins in `zenwave-scripts.zw`, in the order to run them:

| Plugin | Generates |
|---|---|
| `ZDLToOpenAPIPlugin` | `src/main/resources/public/apis/openapi.yml` from the ZDL services |
| `ZDLToAsyncAPIPlugin` | `src/main/resources/public/apis/asyncapi.yml` from the ZDL events |
| `BackendApplicationDefaultPlugin` | domain entities, repositories, services and their implementations, DTOs, mappers, events, listeners, in-memory test configs and tests |
| `OpenAPIControllersPlugin` | Spring MVC controllers implementing the OpenAPI interfaces |
| `SpringWebTestClientPlugin` | API tests (per operation, and per business flow) |

**How to run them:** open `zenwave-scripts.zw` in the IDE with the ZenWave plugin and use the play button next to each plugin. Hovering the button shows the equivalent command line (`jbang zw -p <Plugin> --deps <...> key=value ...`), which you can copy and run from a terminal. `jbang zw` doesn't take a `.zw` file directly: each plugin block is one command.

After generating:

```bash
mvn -DskipTests clean test-compile    # also runs the Maven generators below
git add -A && git commit -m "feat: generate code from zenwave-model.zdl"
```

**Regenerating safely.** Some files are *generated once* and then belong to you: service implementations, event listeners and their MapStruct mappers, tests, `src/test/resources/data*` and `DockerComposeInitializer`. A normal run doesn't touch them. A run with `--force` / `-f` overwrites them, so check the command the IDE generates, and run `git diff` after every regeneration.

### At build time: OpenAPI and AsyncAPI (Maven)

These run in `generate-sources` on every `mvn compile`, from the API files the plugins above wrote. Their output goes to `target/` and is never edited by hand.

**OpenAPI** (`openapi-generator-maven-plugin`, generator `kotlin-spring`):
* reads `src/main/resources/public/apis/openapi.yml`;
* generates the Spring MVC API interfaces and the `*DTO` models into `target/generated-sources/openapi` (`interfaceOnly`: the controllers from `OpenAPIControllersPlugin` implement them);
* packages are set by the `openApiApiPackage` / `openApiModelPackage` properties in `pom.xml`.

**AsyncAPI** (`zenwave-sdk-maven-plugin`, generator `AsyncAPIGenerator`, profile `asyncapi-code-generation`):
* the profile **activates only when `src/main/resources/public/apis/asyncapi.yml` exists**. Without the file, nothing is generated;
* generates, as the API `provider`, the message DTOs and the Spring Cloud Stream producers and consumer interfaces into `target/generated-sources/zenwave`;
* with Spring Modulith, it also wires the transactional outbox (`transactionalOutbox=modulith`) and the application-event listener;
* packages are set by the `asyncApiModelPackage`, `asyncApiProducerApiPackage` and `asyncApiConsumerApiPackage` properties. See the [AsyncAPI generator docs](https://www.zenwave360.io/zenwave-sdk/plugins/asyncapi-generator/).

To serve more API files in SwaggerUI, add them to `application.yml` (`springdoc.swagger-ui.urls`, URLs relative to `src/main/resources/public`).

## Spec-Driven Development with zenwave-sdd

The generator creates the structure: entities, services, events, mappers and test skeletons. It leaves the behavior as `TODO`s. The [zenwave-sdd](https://github.com/ZenWave360/zenwave-sdd) skills find and fill those gaps, and [GitHub Spec Kit](https://github.com/github/spec-kit) gives the work a standard shape (`spec.md` → `plan.md` → `tasks.md`). The full workflow is in the [zenwave-sdd manual](https://github.com/ZenWave360/zenwave-sdd/blob/main/MANUAL.md).

The rule they all follow: **models own structure.** If a behavior needs a field, event, command or state that the ZDL doesn't have, fix the ZDL and regenerate. Never hand-add it to code.

### Install the skills and Spec Kit

Run once, from the project root, after the first generation. It installs a pinned zenwave-sdd release for Claude Code, Codex and Grok:

```bash
ZWSDD=v0.1.0-rc1                                     # zenwave-sdd release tag
tmp=$(mktemp -d)
git clone -q --depth 1 --branch "$ZWSDD" https://github.com/ZenWave360/zenwave-sdd "$tmp"

# the same skills into each agent's folder (copies; symlinks are unreliable on Windows)
for dir in .claude/skills .agents/skills .grok/skills; do
  mkdir -p "$dir" && cp -r "$tmp"/skills/* "$dir"/
done

mkdir -p docs
cp "$tmp"/templates/project-overlay.md docs/zenwave-sdd-overlay.md
cp "$tmp"/speckit/constitution-zenwave.md docs/
echo "$ZWSDD" > docs/zenwave-sdd.version
rm -rf "$tmp"

# Spec Kit, for every agent you use (claude is the default)
uv tool install specify-cli --from git+https://github.com/github/spec-kit.git
specify init --here --integration claude --script sh
specify integration install codex
specify integration install grok
specify integration use claude
```

Then:

1. **Fill in `docs/zenwave-sdd-overlay.md`.** It's how the skills find your ZFL and ZDL files, and learn the deployment shape (microservices or modulith), the packages, and the build and regenerate commands.
2. **Point every agent at it.** Create `AGENTS.md` (read by Codex and Grok) and a `CLAUDE.md` that imports it:

   ```bash
   cat > AGENTS.md <<'EOF'
   # Agent instructions
   - Before running any zenwave-* skill, read docs/zenwave-sdd-overlay.md.
   - Models own structure: never hand-edit generated structure; model gaps go back to the ZDL + regeneration (docs/constitution-zenwave.md).
   - Spec Kit features live in specs/NNN-<feature>/; the constitution is .specify/memory/constitution.md.
   EOF
   printf '@AGENTS.md\n' > CLAUDE.md
   ```

3. **Commit** the skill folders, `docs/`, `.specify/`, `AGENTS.md` and `CLAUDE.md`, so the whole team works with the same versions.

To upgrade, re-run the first block with a new `ZWSDD` tag, and review the `git diff`.

### Run the skills

Claude Code and Grok invoke skills as `/skill-name`, and Codex as `$skill-name`. Any agent also understands "run the zenwave-gap-report skill".

| Step | Run | Result |
|---|---|---|
| ZFL flow → ZDL (only when you start from a ZFL business flow) | `/zfl-to-zdl <flow.zfl>` | one ZDL per bounded context |
| Find what the generator left out | `/zenwave-gap-report` | `docs/zenwave-gap-report.md`: model gaps, missing wiring, unimplemented methods, mapper and test gaps, open questions |
| Fix model gaps | edit the ZDL, regenerate ([Code Generation](#code-generation)), re-run `/zenwave-gap-report` | no model gaps left |
| Generate test data (JPA) | `/zenwave-test-data` | `src/test/resources/data.sql` + `data/jpa/<Entity>/*.json` |
| Spec a flow | `/speckit-constitution` (once), then `/speckit-specify`, `/speckit-clarify`, `/speckit-plan`, `/speckit-tasks` | `specs/NNN-<flow>/` with tasks seeded from the gap report |
| Implement | `/speckit-implement Use the zenwave-fill-gaps skill for each task.`, or `/zenwave-fill-gaps <finding-id>` | code and tests |
| Check drift and close | `/speckit-converge`, `mvn test`, `/zenwave-gap-report` | no blockers or majors left |

To export only the Hibernate DDL (no project configuration needed):

```bash
bash .claude/skills/zenwave-test-data/scripts/export-ddl.sh    # -> target/ddl/schema.sql
```

The prompts for each Spec Kit step, and the reasoning behind them, are in the [zenwave-sdd manual](https://github.com/ZenWave360/zenwave-sdd/blob/main/MANUAL.md).

Happy Coding!! 🚀🚀🚀
]]#
