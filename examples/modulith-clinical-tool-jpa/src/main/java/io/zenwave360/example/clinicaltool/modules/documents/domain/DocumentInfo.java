package io.zenwave360.example.clinicaltool.modules.documents.domain;

import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
* 
*/
@lombok.Getter @lombok.Setter
@Entity
@Table(name = "document_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public  class DocumentInfo  implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)

    private Long id;

    @Version
    private Integer version;

  @Column(name = "uuid" )
    private String uuid ;

  @NotNull@Column(name = "file_name" , nullable = false)
    private String fileName ;

  @NotNull@Column(name = "document_type" , nullable = false)
    private String documentType ;

  @NotNull@Column(name = "content_type" , nullable = false)
    private String contentType ;

  @Column(name = "tags" )
    private List<String> tags = new ArrayList<>();




  
    @NotNull
    
    
    @OneToOne(mappedBy = "document", fetch = FetchType.LAZY , cascade = CascadeType.ALL, orphanRemoval = true)
    private DocumentData documentData ;

    // manage relationships
    public DocumentInfo setDocumentData(DocumentData documentData) {
        this.documentData = documentData;
        if (documentData != null) {
            documentData.setDocument(this);
        }
        return this;
    }




/* https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/ */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof DocumentInfo)){
      return false;
    }
    DocumentInfo other = (DocumentInfo) o;
    return getId() != null && getId().equals(other.getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
