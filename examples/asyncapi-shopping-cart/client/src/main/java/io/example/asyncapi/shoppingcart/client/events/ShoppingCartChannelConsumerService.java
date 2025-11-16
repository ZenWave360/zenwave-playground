package io.example.asyncapi.shoppingcart.client.events;

import io.example.asyncapi.shoppingcart.client.events.IShoppingCartChannelConsumerService;
import io.example.asyncapi.shoppingcart.events.avro.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartChannelConsumerService implements IShoppingCartChannelConsumerService {

    private static final Logger log = LoggerFactory.getLogger(ShoppingCartChannelConsumerService.class);

    @Override public void onShoppingCartCreated(ShoppingCartCreated payload,
            ShoppingCartCreatedHeaders headers) {
        log.info("onShoppingCartCreated: {}", payload);
        throw new RuntimeException("Testing RetryableTopic");
    }

    @Override public void onShoppingCartItemAdded(ShoppingCartItemAdded payload,
            ShoppingCartItemAddedHeaders headers) {
        log.info("onShoppingCartItemAdded: {}", payload);
    }

    @Override public void onShoppingCartItemRemoved(ShoppingCartItemRemoved payload,
            ShoppingCartItemRemovedHeaders headers) {
        log.info("onShoppingCartItemRemoved: {}", payload);
    }

    @Override public void onShoppingCartItemUpdated(ShoppingCartItemUpdated payload,
            ShoppingCartItemUpdatedHeaders headers) {
        log.info("onShoppingCartItemUpdated: {}", payload);
    }

    @Override public void onShoppingCartCheckedOut(ShoppingCartCheckedOut payload,
            ShoppingCartCheckedOutHeaders headers) {
        log.info("onShoppingCartCheckedOut: {}", payload);
    }
}
