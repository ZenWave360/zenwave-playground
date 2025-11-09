package io.example.asyncapi.shoppingcart;

import io.example.asyncapi.shoppingcart.domain.*;
import io.example.asyncapi.shoppingcart.dtos.*;
import io.example.asyncapi.shoppingcart.mappers.*;
import io.example.asyncapi.shoppingcart.web.*;
import io.example.asyncapi.shoppingcart.web.model.*;
import java.math.*;
import java.time.*;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

/**
 * REST controller for ShoppingCartApi.
 */
@RestController
@RequestMapping("/api")
public class ShoppingCartApiController implements ShoppingCartApi {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private NativeWebRequest request;

    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartApiController setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
        return this;
    }

    private ShoppingCartDTOsMapper mapper = ShoppingCartDTOsMapper.INSTANCE;

    public ShoppingCartApiController(ShoppingCartService shoppingCartService) {

        this.shoppingCartService = shoppingCartService;
    }

    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<ShoppingCartDTO> loadShoppingCart(Long customerId) {
        log.debug("REST request to loadShoppingCart: {}", customerId);
        var shoppingCart = shoppingCartService.loadShoppingCart(customerId);
        ShoppingCartDTO responseDTO = mapper.asShoppingCartDTO(shoppingCart);
        return ResponseEntity.status(200).body(responseDTO);
    }

    @Override
    public ResponseEntity<Void> checkoutShoppingCart(Long customerId) {
        log.debug("REST request to checkoutShoppingCart: {}", customerId);
        shoppingCartService.checkoutShoppingCart(customerId);
        return ResponseEntity.status(204).build();
    }

    @Override
    public ResponseEntity<ShoppingCartDTO> addItem(Long customerId, ItemDTO reqBody) {
        log.debug("REST request to addItem: {}, {}", customerId, reqBody);
        var input = mapper.asItem(reqBody);
        var shoppingCart = shoppingCartService.addItem(customerId, input);
        ShoppingCartDTO responseDTO = mapper.asShoppingCartDTO(shoppingCart);
        return ResponseEntity.status(201).body(responseDTO);
    }

    @Override
    public ResponseEntity<ShoppingCartDTO> removeItem(Long customerId, String name) {
        log.debug("REST request to removeItem: {}, {}", customerId, name);
        var shoppingCart = shoppingCartService.removeItem(customerId, name);
        ShoppingCartDTO responseDTO = mapper.asShoppingCartDTO(shoppingCart);
        return ResponseEntity.status(204).body(responseDTO);
    }

    @Override
    public ResponseEntity<ShoppingCartDTO> updateItemQuantity(Long customerId, String name, ItemDTO reqBody) {
        log.debug("REST request to updateItemQuantity: {}, {}, {}", customerId, name, reqBody);
        var input = mapper.asItem(reqBody);
        var shoppingCart = shoppingCartService.updateItemQuantity(customerId, input);
        ShoppingCartDTO responseDTO = mapper.asShoppingCartDTO(shoppingCart);
        return ResponseEntity.status(200).body(responseDTO);
    }

    @Override
    public ResponseEntity<List<ShoppingCartDTO>> listShoppingCarts() {
        log.debug("REST request to listShoppingCarts: ");
        var shoppingCart = shoppingCartService.listShoppingCarts();
        var responseDTO = mapper.asShoppingCartDTOList(shoppingCart);
        return ResponseEntity.status(200).body(responseDTO);
    }

    protected Pageable pageOf(Integer page, Integer limit, List<String> sort) {
        Sort sortOrder = sort != null
                ? Sort.by(sort.stream()
                        .map(sortParam -> {
                            String[] parts = sortParam.split(":");
                            String property = parts[0];
                            Sort.Direction direction =
                                    parts.length > 1 ? Sort.Direction.fromString(parts[1]) : Sort.Direction.ASC;
                            return new Sort.Order(direction, property);
                        })
                        .toList())
                : Sort.unsorted();
        return PageRequest.of(page != null ? page : 0, limit != null ? limit : 10, sortOrder);
    }
}
