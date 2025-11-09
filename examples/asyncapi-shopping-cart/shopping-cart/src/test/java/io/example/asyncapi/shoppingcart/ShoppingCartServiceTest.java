package io.example.asyncapi.shoppingcart;

import static org.mockito.Mockito.*;

import io.example.asyncapi.shoppingcart.config.*;
import io.example.asyncapi.shoppingcart.domain.*;
import io.example.asyncapi.shoppingcart.dtos.*;
import io.example.asyncapi.shoppingcart.inmemory.*;
import io.example.asyncapi.shoppingcart.mappers.*;
import java.time.*;
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
    void loadShoppingCartTest() { // TODO: implement this test
    }

    @Test
    void addItemTest() { // TODO: implement this test
    }

    @Test
    void removeItemTest() { // TODO: implement this test
    }

    @Test
    void updateItemQuantityTest() { // TODO: implement this test
    }

    @Test
    void checkoutShoppingCartTest() { // TODO: implement this test
    }

    @Test
    void listShoppingCartsTest() {
        // var results = shoppingCartService.listShoppingCarts(PageRequest.of(0, 10));
        // assertNotNull(results);
    }
}
