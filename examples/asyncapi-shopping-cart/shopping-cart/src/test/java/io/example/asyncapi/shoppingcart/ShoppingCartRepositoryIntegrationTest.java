package io.example.asyncapi.shoppingcart;

import io.example.asyncapi.shoppingcart.base.BaseRepositoryIntegrationTest;
import io.example.asyncapi.shoppingcart.domain.*;
import jakarta.persistence.EntityManager;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;

@Sql(scripts = "/test-data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ShoppingCartRepositoryIntegrationTest extends BaseRepositoryIntegrationTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Test
    void findAllTest() {
        var results = shoppingCartRepository.findAll();
        Assertions.assertFalse(results.isEmpty());
    }

    @Test
    void findByIdTest() {
        var id = 1L;
        var shoppingCart = shoppingCartRepository.findById(id).orElseThrow();
        Assertions.assertNotNull(shoppingCart.getId());
        Assertions.assertNotNull(shoppingCart.getVersion());
        Assertions.assertNotNull(shoppingCart.getCreatedBy());
        Assertions.assertNotNull(shoppingCart.getCreatedDate());
    }

    @Test
    void saveTest() {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setCustomerId(0L);
        shoppingCart.setItems(List.of(new Item()));

        // Persist aggregate root
        var created = shoppingCartRepository.save(shoppingCart);

        // reloading to get relationships persisted by id
        entityManager.flush();
        entityManager.refresh(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertNotNull(created.getVersion());
        Assertions.assertNotNull(created.getCreatedBy());
        Assertions.assertNotNull(created.getCreatedDate());
    }

    @Test
    void updateTest() {
        var id = 1L;
        var randomString = Math.random() + "";
        var shoppingCart = shoppingCartRepository.findById(id).orElseThrow();
        shoppingCart.setCustomerId(0L);
        shoppingCart.setItems(List.of(new Item().setName(randomString).setQuantity(3)));

        shoppingCart = shoppingCartRepository.save(shoppingCart);
        Assertions.assertEquals(0L, shoppingCart.getCustomerId());
        Assertions.assertEquals(shoppingCart.getItems().size(), 1);
        Assertions.assertEquals(shoppingCart.getItems().get(0).getName(), randomString);
    }

    @Test
    void deleteTest() {
        var id = 1L;
        shoppingCartRepository.deleteById(id);
        var notFound = shoppingCartRepository.findById(id);
        Assertions.assertFalse(notFound.isPresent());
    }
}
