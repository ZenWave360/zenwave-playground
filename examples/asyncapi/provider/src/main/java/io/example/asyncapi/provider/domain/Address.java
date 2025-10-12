package io.example.asyncapi.provider.domain;

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
public class Address implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(max = 254)
    @Column(name = "street", nullable = false, length = 254)
    private String street;

    @NotNull
    @Size(max = 254)
    @Column(name = "city", nullable = false, length = 254)
    private String city;
}
