package io.example.asyncapi.shoppingcart.mappers;

import io.example.asyncapi.shoppingcart.domain.*;
import io.example.asyncapi.shoppingcart.dtos.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {BaseMapper.class})
public interface ShoppingCartServiceMapper {

    ShoppingCartServiceMapper INSTANCE = Mappers.getMapper(ShoppingCartServiceMapper.class);

    // input mappings
    // ItemNamenull-ShoppingCart removeItem
    ShoppingCart asShoppingCart(String name);

    @Mapping(target = "id", ignore = true)
    ShoppingCart update(@MappingTarget ShoppingCart entity, String name);
    // Item-ShoppingCart updateItemQuantity
    ShoppingCart asShoppingCart(Item input);

    @Mapping(target = "id", ignore = true)
    ShoppingCart update(@MappingTarget ShoppingCart entity, Item item);
    // output mappings
}
