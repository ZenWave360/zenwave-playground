package io.example.asyncapi.shoppingcart;

import io.example.asyncapi.shoppingcart.config.ServicesInMemoryConfig;
import io.example.asyncapi.shoppingcart.domain.Item;
import io.example.asyncapi.shoppingcart.inmemory.ShoppingCartRepositoryInMemory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Acceptance Test for ShoppingCartService.
 */
class ShoppingCartServiceTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();
    ShoppingCartServiceImpl shoppingCartService = context.shoppingCartService();

    ShoppingCartRepositoryInMemory shoppingCartRepository = context.shoppingCartRepository();

    @BeforeEach
    void setUp() {
        context.reloadTestData();
    }

    @Test
    void loadShoppingCartTest() {
        Long customerId = 1L;
        var shoppingCart = shoppingCartService.loadShoppingCart(customerId);

        Assertions.assertNotNull(shoppingCart);
        var capturedMessages = context.getEventsProducerInMemoryContext().shoppingCartEventsProducer()
                .getCapturedMessages("on-shopping-cart-created-out-0");
        Assertions.assertEquals(0, capturedMessages.size());
    }

    @Test
    void createShoppingCartTest() {
        Long customerId = 0L;  // not existing
        var shoppingCart = shoppingCartService.loadShoppingCart(customerId);

        Assertions.assertNotNull(shoppingCart);
        var capturedMessages = context.getEventsProducerInMemoryContext().shoppingCartEventsProducer()
                .getCapturedMessages("on-shopping-cart-created-out-0");
        Assertions.assertEquals(1, capturedMessages.size());
    }

    @Test
    void addItemTest() {
        Long customerId = 1L;
        Item input = new Item().setName("item").setQuantity(3);
        var shoppingCart = shoppingCartService.addItem(customerId, input);
        Assertions.assertNotNull(shoppingCart);
    }

    @Test
    void removeItemTest() {
        Long customerId = 1L;
        String name = "itemToRemove";
        var shoppingCart = shoppingCartService.removeItem(customerId, name);
        Assertions.assertNotNull(shoppingCart);
    }

    @Test
    void updateItemQuantityTest() {
        Long customerId = 1L;
        Item input = new Item().setName("itemToUpdate").setQuantity(3);
        var shoppingCart = shoppingCartService.updateItemQuantity(customerId, input);
        Assertions.assertNotNull(shoppingCart);
    }

    @Test
    void checkoutShoppingCartTest() {
        Long customerId = 1L;
        shoppingCartService.checkoutShoppingCart(customerId);
    }

    @Test
    void listShoppingCartsTest() {
        var shoppingCarts = shoppingCartService.listShoppingCarts();
        Assertions.assertNotNull(shoppingCarts);
    }
}
