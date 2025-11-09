package io.example.asyncapi.shoppingcart;

import io.example.asyncapi.shoppingcart.domain.*;
import io.example.asyncapi.shoppingcart.dtos.*;
import java.math.*;
import java.time.*;
import java.util.*;

/**
 * Inbound Service Port for managing [ShoppingCart].
 */
public interface ShoppingCartService {

    /**
     *
     *
     * With Events: [ShoppingCartCreated].
     *
     */
    public ShoppingCart loadShoppingCart(Long customerId);
    /**
     *
     *
     * With Events: [ShoppingCartItemAdded].
     *
     */
    public ShoppingCart addItem(Long customerId, Item input);
    /**
     *
     *
     * With Events: [ShoppingCartItemRemoved].
     *
     */
    public ShoppingCart removeItem(Long customerId, String name);
    /**
     *
     *
     * With Events: [ShoppingCartItemUpdated].
     *
     */
    public ShoppingCart updateItemQuantity(Long customerId, Item input);
    /**
     *
     *
     * With Events: [ShoppingCartCheckedOut].
     *
     */
    public void checkoutShoppingCart(Long customerId);
    /**
     *
     *
     */
    public List<ShoppingCart> listShoppingCarts();
}
