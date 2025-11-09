package io.example.asyncapi.shoppingcart;

import io.example.asyncapi.shoppingcart.domain.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ShoppingCart entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    java.util.Optional<ShoppingCart> findByCustomerId(Long customerId);
}
