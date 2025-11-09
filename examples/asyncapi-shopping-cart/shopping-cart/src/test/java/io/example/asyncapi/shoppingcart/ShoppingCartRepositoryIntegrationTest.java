package io.example.asyncapi.shoppingcart;

import io.example.asyncapi.shoppingcart.domain.*;
import jakarta.persistence.EntityManager;
import java.time.*;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
        var shoppingCart = shoppingCartRepository.findById(id).orElseThrow();
        shoppingCart.setCustomerId(0L);
        shoppingCart.setItems(List.of(new Item()));

        shoppingCart = shoppingCartRepository.save(shoppingCart);
        Assertions.assertEquals(0L, shoppingCart.getCustomerId());
        Assertions.assertEquals(List.of(new Item()), shoppingCart.getItems());
    }

    @Test
    void deleteTest() {
        var id = 1L;
        shoppingCartRepository.deleteById(id);
        var notFound = shoppingCartRepository.findById(id);
        Assertions.assertFalse(notFound.isPresent());
    }
}
