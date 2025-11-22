package io.example.asyncapi.shoppingcart.mappers;

import io.example.asyncapi.shoppingcart.domain.*;
import io.example.asyncapi.shoppingcart.domain.Item;
import io.example.asyncapi.shoppingcart.domain.ShoppingCart;
import io.example.asyncapi.shoppingcart.dtos.*;
import io.example.asyncapi.shoppingcart.events.avro.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {BaseMapper.class})
public interface EventsMapper {

    EventsMapper INSTANCE = Mappers.getMapper(EventsMapper.class);

    @Mapping(target = "customerId", source = "shoppingCart.customerId")
    ShoppingCartCreated asShoppingCartCreated(ShoppingCart shoppingCart);

    @Mapping(target = "customerId", source = "shoppingCart.customerId")
    @Mapping(target = "item", source = "item")
    @Mapping(target = "shoppingCart", source = "shoppingCart")
    ShoppingCartItemAdded asShoppingCartItemAdded(ShoppingCart shoppingCart, Item item);

    @Mapping(target = "customerId", source = "shoppingCart.customerId")
    @Mapping(target = "item", source = "item")
    @Mapping(target = "previousItem", source = "previousItem")
    @Mapping(target = "shoppingCart", source = "shoppingCart")
    ShoppingCartItemUpdated asShoppingCartItemUpdated(ShoppingCart shoppingCart, Item item, Item previousItem);

    @Mapping(target = "customerId", source = "shoppingCart.customerId")
    @Mapping(target = "item", source = "item")
    @Mapping(target = "shoppingCart", source = "shoppingCart")
    ShoppingCartItemRemoved asShoppingCartItemRemoved(ShoppingCart shoppingCart, Item item);


    io.example.asyncapi.shoppingcart.events.avro.Item asItem(Item item);

    io.example.asyncapi.shoppingcart.events.avro.ShoppingCart asShoppingCart(ShoppingCart shoppingCart);
}
