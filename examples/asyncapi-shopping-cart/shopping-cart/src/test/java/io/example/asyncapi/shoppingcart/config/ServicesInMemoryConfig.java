package io.example.asyncapi.shoppingcart.config;

import io.example.asyncapi.shoppingcart.*;
import io.example.asyncapi.shoppingcart.domain.*;
import io.example.asyncapi.shoppingcart.events.*;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Services InMemory Config. It can be used standalone or with @SpringBootTest.
 */
@Configuration
@Profile("in-memory")
public class ServicesInMemoryConfig extends RepositoriesInMemoryConfig {

    protected final EventsProducerInMemoryContext eventsProducerInMemoryContext = new EventsProducerInMemoryContext();

    protected final ShoppingCartServiceImpl shoppingCartService = new ShoppingCartServiceImpl(
            shoppingCartRepository(), eventsProducerInMemoryContext.shoppingCartEventsProducer());

    @Bean
    public <T extends ShoppingCartService> T shoppingCartService() {
        return (T) shoppingCartService;
    }

    static List<ShoppingCart> _shoppingCarts;

    public void reloadTestData() {

        var testDataLoader = new TestDataLoader(List.of(ShoppingCart.class, Item.class));
        var shoppingCarts = _shoppingCarts != null
                ? _shoppingCarts
                : testDataLoader.loadCollectionTestDataAsObjects(ShoppingCart.class);
        shoppingCartRepository().deleteAll();
        shoppingCartRepository().saveAll(shoppingCarts);
    }

    public EventsProducerInMemoryContext getEventsProducerInMemoryContext() {
        return eventsProducerInMemoryContext;
    }
}
