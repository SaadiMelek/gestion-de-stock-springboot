package com.melek.gestionstock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Article")
public class Article extends AbstractEntity {

    @Column(name = "codeArticle")
    private String codeArticle;
    private String designation;
    private BigDecimal prixUnitaireHt;
    private BigDecimal taxTva;
    private BigDecimal prixUnitaireTtc;
    private String photo;

    @ManyToOne
    @JoinColumn(name="idcategory")
    private Category category;

}
