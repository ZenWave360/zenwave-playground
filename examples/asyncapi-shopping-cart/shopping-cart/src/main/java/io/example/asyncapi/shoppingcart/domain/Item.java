package io.example.asyncapi.shoppingcart.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;

/**
 *
 */
@lombok.Getter
@lombok.Setter
// @Embeddable // json embedded
public class Item implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 254)
    @Column(name = "name", nullable = false, length = 254)
    private String name;

    @NotNull
    @Min(1)
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
