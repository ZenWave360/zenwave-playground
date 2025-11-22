package io.example.asyncapi.shoppingcart;

import io.example.asyncapi.shoppingcart.domain.*;
import io.example.asyncapi.shoppingcart.events.*;
import io.example.asyncapi.shoppingcart.mappers.*;
// import io.example.asyncapi.shoppingcart.events.avro.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing [ShoppingCart].
 */
@Service
@Transactional
@lombok.AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final ShoppingCartServiceMapper shoppingCartServiceMapper = ShoppingCartServiceMapper.INSTANCE;

    private final ShoppingCartRepository shoppingCartRepository;

    private final EventsMapper eventsMapper = EventsMapper.INSTANCE;
    private final ShoppingCartEventsProducer eventsProducer;

    public ShoppingCart loadShoppingCart(Long customerId) {
        log.debug("Request loadShoppingCart: {}", customerId);

        return shoppingCartRepository
                .findByCustomerId(customerId)
                .orElseGet(() -> {
                    var newShoppingCart = new ShoppingCart().setCustomerId(customerId);
                    // emit events
                    var shoppingCartCreated = eventsMapper.asShoppingCartCreated(newShoppingCart);
                    eventsProducer.onShoppingCartCreated(shoppingCartCreated);
                    return shoppingCartRepository.save(newShoppingCart);
                });
    }

    public ShoppingCart addItem(Long customerId, Item input) {
        log.debug("Request addItem: {} {}", customerId, input);

        var shoppingCart = loadShoppingCart(customerId);
        shoppingCart.getItems().add(input);
        shoppingCartRepository.save(shoppingCart);
        // emit events
        var shoppingCartItemAdded = eventsMapper.asShoppingCartItemAdded(shoppingCart, input);
        eventsProducer.onShoppingCartItemAdded(shoppingCartItemAdded);
        return shoppingCart;
    }

    public ShoppingCart removeItem(Long customerId, String name) {
        log.debug("Request removeItem: {} {}", customerId, name);
        var shoppingCart = shoppingCartRepository
                .findByCustomerId(customerId)
                .orElseThrow();
        var item = shoppingCart.getItems().stream().filter(i -> i.getName().equals(name)).findFirst().orElseThrow();
        shoppingCart.getItems().remove(item);
        shoppingCartRepository.save(shoppingCart);
        // emit events
        var shoppingCartItemRemoved = eventsMapper.asShoppingCartItemRemoved(shoppingCart, item);
        eventsProducer.onShoppingCartItemRemoved(shoppingCartItemRemoved);
        return shoppingCart;
    }

    public ShoppingCart updateItemQuantity(Long customerId, Item input) {
        log.debug("Request updateItemQuantity: {} {}", customerId, input);

        var shoppingCart = shoppingCartRepository
                .findByCustomerId(customerId).orElseThrow();
        var item = shoppingCart.getItems().stream()
                .filter(i -> i.getName().equals(input.getName())).findFirst();
        if (item.isEmpty()) {
            return addItem(customerId, input);
        }
        var previousItem = new Item().setName(item.get().getName()).setQuantity(item.get().getQuantity());
        shoppingCartServiceMapper.update(shoppingCart, input);
        shoppingCartRepository.save(shoppingCart);
        // emit events
        var shoppingCartItemUpdated = eventsMapper.asShoppingCartItemUpdated(shoppingCart, input, previousItem);
        eventsProducer.onShoppingCartItemUpdated(shoppingCartItemUpdated);
        return shoppingCart;
    }

    public void checkoutShoppingCart(Long customerId) {
        log.debug("Request checkoutShoppingCart: {}", customerId);
        shoppingCartRepository.findByCustomerId(customerId)
                .ifPresent(shoppingCartRepository::delete);
    }

    public List<ShoppingCart> listShoppingCarts() {
        log.debug("Request listShoppingCarts");
        return shoppingCartRepository.findAll();
    }
}
