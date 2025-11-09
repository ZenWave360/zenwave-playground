package io.example.asyncapi.shoppingcart.mappers;

import io.example.asyncapi.shoppingcart.domain.*;
import io.example.asyncapi.shoppingcart.dtos.*;
import io.example.asyncapi.shoppingcart.web.model.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = BaseMapper.class)
public interface ShoppingCartDTOsMapper {

    ShoppingCartDTOsMapper INSTANCE = Mappers.getMapper(ShoppingCartDTOsMapper.class);

    // request mappings
    Item asItem(ItemDTO dto);

    // response mappings

    List<ShoppingCartDTO> asShoppingCartDTOList(List<ShoppingCart> entityList);

    ShoppingCartDTO asShoppingCartDTO(ShoppingCart entity);
}
