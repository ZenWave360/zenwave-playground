package io.zenwave360.example.clinicaltool.modules.documents.core.outbound.jpa;

import io.zenwave360.example.clinicaltool.modules.documents.core.domain.*;
import java.math.*;
import java.time.*;
import java.util.*;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DocumentInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocumentInfoRepository extends JpaRepository<DocumentInfo, Long> {
}
