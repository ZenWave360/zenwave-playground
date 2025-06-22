package io.zenwave360.example.clinicaltool.modules.documents.infrastructure.jpa.inmemory;

import io.zenwave360.example.clinicaltool.modules.documents.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.documents.core.outbound.jpa.DocumentInfoRepository;
import java.math.*;
import java.time.*;
import java.util.*;

public class DocumentInfoRepositoryInMemory extends InMemoryJpaRepository<DocumentInfo> implements DocumentInfoRepository {
}
