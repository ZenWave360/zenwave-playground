package io.example.asyncapi.shoppingcart;

import io.example.asyncapi.shoppingcart.web.*;
import io.example.asyncapi.shoppingcart.base.BaseWebTestClientTest;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.http.HttpMethod.*;

/**
* Integration tests for the {@link ShoppingCartApi} REST controller.
*/
@Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ShoppingCartApiIntegrationTest extends BaseWebTestClientTest {



    /**
    * Test: loadShoppingCart for OK.
    */
    @Test
    public void testLoadShoppingCart_200() {
        var customerId = "1";

        webTestClient.method(GET).uri("/api/shopping-cart/{customerId}", customerId)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.customerId").isNotEmpty()
            .jsonPath("$.items").isNotEmpty()
            .jsonPath("$.items").isArray()
            .jsonPath("$.items[0].name").isNotEmpty()
            .jsonPath("$.items[0].quantity").isNotEmpty();
    }

    /**
    * Test: checkoutShoppingCart for OK.
    */
    @Test
    public void testCheckoutShoppingCart_204() {
        var customerId = "1";

        webTestClient.method(DELETE).uri("/api/shopping-cart/{customerId}", customerId)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isEqualTo(204);
    }

    /**
    * Test: addItem for OK.
    */
    @Test
    public void testAddItem_201() {
        var customerId = "1";
        String requestBody = """
            {
              "name" : "name-usyoelqozyzz8k1cqw27s",
              "quantity" : 31
            }
        """;

        webTestClient.method(POST).uri("/api/shopping-cart/{customerId}/items", customerId)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isEqualTo(201)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.customerId").isNotEmpty()
            .jsonPath("$.items").isNotEmpty()
            .jsonPath("$.items").isArray()
            .jsonPath("$.items[0].name").isNotEmpty()
            .jsonPath("$.items[0].quantity").isNotEmpty();
    }

    /**
    * Test: removeItem for OK.
    */
    @Test
    public void testRemoveItem_204() {
        var customerId = "1";
        var name = "itemToRemove";

        webTestClient.method(DELETE).uri("/api/shopping-cart/{customerId}/items/{name}", customerId, name)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isEqualTo(204)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.customerId").isNotEmpty()
            .jsonPath("$.items").isNotEmpty()
            .jsonPath("$.items").isArray()
            .jsonPath("$.items[0].name").isNotEmpty()
            .jsonPath("$.items[0].quantity").isNotEmpty();
    }

    /**
    * Test: updateItemQuantity for OK.
    */
    @Test
    public void testUpdateItemQuantity_200() {
        String requestBody = """
            {
              "name" : "name-740sxtk7zy6xvhxbk46no",
              "quantity" : 94
            }
        """;
        var customerId = "1";
        var name = "itemToUpdate";

        webTestClient.method(PUT).uri("/api/shopping-cart/{customerId}/items/{name}", customerId, name)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.version").isNotEmpty()
            .jsonPath("$.customerId").isNotEmpty()
            .jsonPath("$.items").isNotEmpty()
            .jsonPath("$.items").isArray()
            .jsonPath("$.items[0].name").isNotEmpty()
            .jsonPath("$.items[0].quantity").isNotEmpty();
    }

    /**
    * Test: listShoppingCarts for OK.
    */
    @Test
    public void testListShoppingCarts_200() {

        webTestClient.method(GET).uri("/api/shopping-cart")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isEqualTo(200)
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody();
    }

}
