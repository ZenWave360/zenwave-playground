package io.example.asyncapi.shoppingcart.config;

import io.example.asyncapi.shoppingcart.*;
import io.example.asyncapi.shoppingcart.inmemory.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

// @Configuration
public class RepositoriesInMemoryConfig {

    protected final ShoppingCartRepository shoppingCartRepository = new ShoppingCartRepositoryInMemory();

    @Bean
    @Primary
    public <T extends ShoppingCartRepository> T shoppingCartRepository() {
        return (T) shoppingCartRepository;
    }
}
