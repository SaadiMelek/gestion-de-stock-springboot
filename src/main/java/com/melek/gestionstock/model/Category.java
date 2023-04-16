package com.melek.gestionstock.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Category")
public class Category extends AbstractEntity {
    private String code;
    private String designation;
    @JoinColumn(name="idEntreprise")
    private Integer idEntreprise;
    @OneToMany(mappedBy = "category")
    private List<Article> articles;
}
