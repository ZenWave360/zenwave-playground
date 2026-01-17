package io.zenwave360.example.applicantscoring.domain;

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
@Embeddable
public class City implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "city")
    private String city;
}
