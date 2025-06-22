package io.zenwave360.example.clinicaltool.modules.clinical.infrastructure.jpa.inmemory;

import io.zenwave360.example.clinicaltool.modules.clinical.core.domain.*;
import io.zenwave360.example.clinicaltool.modules.clinical.core.outbound.jpa.ProvisionalPatientRepository;
import java.math.*;
import java.time.*;
import java.util.*;

public class ProvisionalPatientRepositoryInMemory extends InMemoryJpaRepository<ProvisionalPatient> implements ProvisionalPatientRepository {
    @Override
    public java.util.Optional<ProvisionalPatient> findByPhoneNumberAndHisNumber(String phoneNumber, String hisNumber) {
        return getEntities().values().stream().filter(e ->
             isSameValue(phoneNumber, readField(e, "phoneNumber")) && isSameValue(hisNumber, readField(e, "hisNumber")) 
        ).findFirst();
    }
}
