package io.example.asyncapi.shoppingcart.inmemory;

import io.example.asyncapi.shoppingcart.ShoppingCartRepository;
import io.example.asyncapi.shoppingcart.domain.*;
import java.math.*;
import java.time.*;
import java.util.*;

public class ShoppingCartRepositoryInMemory extends InMemoryJpaRepository<ShoppingCart>
        implements ShoppingCartRepository {
    @Override
    public java.util.Optional<ShoppingCart> findByCustomerId(Long customerId) {
        return getEntities().values().stream()
                .filter(e -> isSameValue(customerId, readField(e, "customerId")))
                .findFirst();
    }
}
