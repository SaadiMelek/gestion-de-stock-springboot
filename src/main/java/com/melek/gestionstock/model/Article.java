package com.melek.gestionstock.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "Article")
public class Article extends AbstractEntity {

    @Id
    @GeneratedValue
    private Integer id;
    @Column(name = "codeArticle")
    private String codeArticle;
    private String designation;
    private BigDecimal prixUnitaireHt;
    private BigDecimal taxTva;
    private BigDecimal prixUnitaireTtc;
    private String photo;
    private Integer idEntreprise;

    /*@CreatedDate
    @Column(name="creationDate", nullable = false)
    @JsonIgnore
    private Instant creationDate;

    @LastModifiedDate
    @Column(name="lastModifiedDate", nullable = false)
    @JsonIgnore
    private Instant lastModifiedDate;*/

    @ManyToOne
    @JoinColumn(name="idcategory")
    private Category category;

}
