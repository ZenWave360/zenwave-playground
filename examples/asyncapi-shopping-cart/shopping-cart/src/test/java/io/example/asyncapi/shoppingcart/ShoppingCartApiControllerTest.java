package io.example.asyncapi.shoppingcart;

import io.example.asyncapi.shoppingcart.config.ServicesInMemoryConfig;
import io.example.asyncapi.shoppingcart.web.*;
import io.example.asyncapi.shoppingcart.web.model.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Test controller for ShoppingCartApiController.
 */
public class ShoppingCartApiControllerTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    ServicesInMemoryConfig context = new ServicesInMemoryConfig();

    ShoppingCartApiController controller = new ShoppingCartApiController(context.shoppingCartService());

    @BeforeEach
    void setUp() {
        context.reloadTestData();
    }

    @Test
    public void loadShoppingCartTest() {
        Long customerId = 1L;
        var response = controller.loadShoppingCart(customerId);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void checkoutShoppingCartTest() {
        Long customerId = 1L;
        var response = controller.checkoutShoppingCart(customerId);
        Assertions.assertEquals(204, response.getStatusCode().value());
    }

    @Test
    public void addItemTest() {
        Long customerId = 1L;
        ItemDTO reqBody = new ItemDTO().name("item").quantity(3);
        var response = controller.addItem(customerId, reqBody);
        Assertions.assertEquals(201, response.getStatusCode().value());
    }

    @Test
    public void removeItemTest() {
        Long customerId = 1L;
        String name = "itemToRemove";
        var response = controller.removeItem(customerId, name);
        Assertions.assertEquals(204, response.getStatusCode().value());
    }

    @Test
    public void updateItemQuantityTest() {
        Long customerId = 1L;
        String name = "itemToUpdate";
        ItemDTO reqBody = new ItemDTO().name("itemToUpdate").quantity(3);
        var response = controller.updateItemQuantity(customerId, name, reqBody);
        Assertions.assertEquals(200, response.getStatusCode().value());
    }

    @Test
    public void listShoppingCartsTest() {
        var response = controller.listShoppingCarts();
        Assertions.assertEquals(200, response.getStatusCode().value());
    }
}
