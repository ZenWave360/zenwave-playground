package io.example.asyncapi.client.events;

import io.example.asyncapi.shoppingcart.client.events.IShoppingCartChannelConsumerService;
import io.example.asyncapi.shoppingcart.events.avro.*;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartChannelConsumerService implements IShoppingCartChannelConsumerService {

    @Override public void onShoppingCartCreated(ShoppingCartCreated payload,
            ShoppingCartCreatedHeaders headers) {
        throw new RuntimeException("Testing RetryableTopic");
    }

    @Override public void onShoppingCartItemAdded(ShoppingCartItemAdded payload,
            ShoppingCartItemAddedHeaders headers) {

    }

    @Override public void onShoppingCartItemRemoved(ShoppingCartItemRemoved payload,
            ShoppingCartItemRemovedHeaders headers) {

    }

    @Override public void onShoppingCartItemUpdated(ShoppingCartItemUpdated payload,
            ShoppingCartItemUpdatedHeaders headers) {

    }

    @Override public void onShoppingCartCheckedOut(ShoppingCartCheckedOut payload,
            ShoppingCartCheckedOutHeaders headers) {

    }
}
