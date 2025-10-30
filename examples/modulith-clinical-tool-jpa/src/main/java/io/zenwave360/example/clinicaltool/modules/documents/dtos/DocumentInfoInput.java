package io.zenwave360.example.clinicaltool.modules.documents.dtos;

import io.zenwave360.example.clinicaltool.modules.documents.domain.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;

/**
 * DocumentInfoInput.
 */
@lombok.Getter
@lombok.Setter
public class DocumentInfoInput implements Serializable {

    private String uuid;

    private List<String> tags = new ArrayList<>();

    public DocumentInfoInput addTags(String tags) {
        this.tags.add(tags);
        return this;
    }
}
