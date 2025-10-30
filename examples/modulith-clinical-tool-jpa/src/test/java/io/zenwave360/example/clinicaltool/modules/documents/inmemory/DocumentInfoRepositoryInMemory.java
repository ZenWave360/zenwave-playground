package io.zenwave360.example.clinicaltool.modules.documents.inmemory;

import static org.apache.commons.lang3.ObjectUtils.firstNonNull;

import io.zenwave360.example.clinicaltool.modules.documents.DocumentInfoRepository;
import io.zenwave360.example.clinicaltool.modules.documents.domain.*;
import java.math.*;
import java.time.*;
import java.util.*;

public class DocumentInfoRepositoryInMemory extends InMemoryJpaRepository<DocumentInfo>
        implements DocumentInfoRepository {
    @Override
    public <T extends DocumentInfo> T save(T entity) {
        var finalEntity = super.save(entity);
        finalEntity
                .getDocumentData()
                .setId(firstNonNull(finalEntity.getDocumentData().getId(), finalEntity.getId()));
        return (T) finalEntity;
    }
}
