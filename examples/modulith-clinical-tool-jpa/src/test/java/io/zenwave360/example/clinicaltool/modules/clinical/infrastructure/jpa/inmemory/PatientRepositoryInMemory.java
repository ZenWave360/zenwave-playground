package io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.jpa.inmemory;

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.PatientRepository;
import java.math.*;
import java.time.*;
import java.util.*;

public class PatientRepositoryInMemory extends InMemoryJpaRepository<Patient> implements PatientRepository {
    @Override
    public java.util.Optional<Patient> findByPhoneNumberAndHisNumber(String phoneNumber, String hisNumber) {
        return getEntities().values().stream()
                .filter(e -> isSameValue(phoneNumber, readField(e, "phoneNumber"))
                        && isSameValue(hisNumber, readField(e, "hisNumber")))
                .findFirst();
    }
}
