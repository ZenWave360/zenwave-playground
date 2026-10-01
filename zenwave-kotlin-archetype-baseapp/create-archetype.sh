#!/bin/sh
set -e

GENERATED=target/generated-sources/archetype
RESOURCES=$GENERATED/src/main/resources

mvn clean archetype:create-from-project -Darchetype.properties=./archetype/archetype.properties

echo "Replacing package name in the generated archetype"
find $RESOURCES/archetype-resources -type f -exec sed -i -r 's/io\.zenwave360\.example/${package}/g' {} \;

echo "Replacing project name in the generated archetype"
# covers <name> in pom.xml, the springdoc title in application.yml and `title` in the .zdl models
find $RESOURCES/archetype-resources -type f -exec sed -i -r 's/ZenWave Kotlin Archetype Baseapp/${projectName}/g' {} \;

echo "Copying archetype-post-generate.groovy to the generated archetype"
cp archetype/archetype-post-generate.groovy $RESOURCES/META-INF/archetype-post-generate.groovy
# the Java and Kotlin archetypes share this script byte-for-byte: fix it in one, copy it to the other
SIBLING_SCRIPT=../zenwave-java-archetype-baseapp/archetype/archetype-post-generate.groovy
if [ -f "$SIBLING_SCRIPT" ] && ! cmp -s archetype/archetype-post-generate.groovy "$SIBLING_SCRIPT"; then
    echo "WARNING: archetype-post-generate.groovy differs from $SIBLING_SCRIPT, keep both copies in sync"
fi

echo "Copying archetype-metadata.xml to the generated archetype"
cp archetype/archetype-metadata.xml $RESOURCES/META-INF/maven/archetype-metadata.xml

echo "Copying archetype.properties for tests"
# create-from-project writes a default 'basic' IT into BOTH the sources and the build output;
# the second copy survives a non-clean build and would be run as a 7th (unconfigured) IT
rm -rf $GENERATED/src/test/resources/projects/basic
rm -rf $GENERATED/target/test-classes/projects/basic
cp -R archetype/tests/* $GENERATED/src/test/resources/projects

echo ""
echo "Archetype sources ready at $GENERATED"
echo "Install it locally with:  (cd $GENERATED && mvn clean install)"
