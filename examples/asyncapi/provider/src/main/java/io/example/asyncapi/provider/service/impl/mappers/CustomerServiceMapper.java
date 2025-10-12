package io.example.asyncapi.provider.service.impl.mappers;

import io.example.asyncapi.provider.domain.*;
import io.example.asyncapi.provider.service.dtos.*;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {BaseMapper.class})
public interface CustomerServiceMapper {

    CustomerServiceMapper INSTANCE = Mappers.getMapper(CustomerServiceMapper.class);

    // input mappings
    // Customer-Customer updateCustomer
    Customer asCustomer(Customer input);

    @Mapping(target = "id", ignore = true)
    Customer update(@MappingTarget Customer entity, Customer input);
    // output mappings
    @AfterMapping
    default void manageRelationships(@MappingTarget Customer entity) {
        if (entity.getPaymentMethods() != null) {
            entity.getPaymentMethods().forEach(paymentMethods -> paymentMethods.setCustomer(entity));
        }
    }
}
