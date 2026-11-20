package io.zenwave360.example.outbox;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.events.IncompleteEventPublications;

/**
 * Upgrade from spring-modulith-events-scs 1.3.x (Spring Modulith 2.1) to 1.4.x (Spring Modulith 2.2).
 *
 * <p>The externalizer listener id stored in {@code event_publication.listener_id} changed. Rows left incomplete by
 * 1.3.x are not resubmitted until they are moved to the new id with the SQL from the 1.4.0 release notes.
 */
class LegacyListenerIdUpgradeIntegrationTest extends AbstractOutboxIntegrationTest {

    static final String LEGACY_LISTENER_ID =
            "org.springframework.modulith.events.support.DelegatingEventExternalizer.externalize(java.lang.Object)";
    static final String LISTENER_ID =
            "org.springframework.modulith.events.support.EventExternalizerModuleListener.externalize(java.lang.Object)";

    @Autowired
    IncompleteEventPublications incompleteEventPublications;

    @Test
    void rowsWithLegacyListenerIdAreResubmittedAfterMigrationSql() throws Exception {
        String email = uniqueEmail();
        createCustomer(email);
        awaitCustomerEvents(email, 1);
        awaitPublicationCompleted(email);
        assertThat(listenerId(email)).isEqualTo(LISTENER_ID);

        // Simulate a publication left incomplete by 1.3.x
        int updated = update(
                "update event_publication set listener_id = ?, completion_date = null where serialized_event like ?",
                LEGACY_LISTENER_ID,
                "%" + email + "%");
        assertThat(updated).isEqualTo(1);

        // No listener with the legacy id: Spring Modulith skips it
        incompleteEventPublications.resubmitIncompletePublications(publication -> true);
        Thread.sleep(Duration.ofSeconds(5));
        assertThat(isCompleted(email)).isFalse();

        // Migration SQL from release-notes.v1.4.0.md
        int moved = update(
                "update event_publication set listener_id = ? where listener_id = ? and completion_date is null",
                LISTENER_ID,
                LEGACY_LISTENER_ID);
        assertThat(moved).isGreaterThanOrEqualTo(1);

        incompleteEventPublications.resubmitIncompletePublications(publication -> true);
        assertThat(awaitCustomerEvents(email, 2)).hasSizeGreaterThanOrEqualTo(2);
        awaitPublicationCompleted(email);
    }

    private String listenerId(String email) {
        return jdbcTemplate.queryForObject(
                "select listener_id from event_publication where serialized_event like ?",
                String.class,
                "%" + email + "%");
    }

    private boolean isCompleted(String email) {
        return jdbcTemplate.queryForObject(
                "select completion_date is not null from event_publication where serialized_event like ?",
                Boolean.class,
                "%" + email + "%");
    }
}
