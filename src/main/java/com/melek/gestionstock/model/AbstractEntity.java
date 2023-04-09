package com.melek.gestionstock.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    //@CreatedDate
    @Column(name="creationDate", nullable = true)
    //@JsonIgnore
    private Instant creationDate = Instant.now();

    //@LastModifiedDate
    @Column(name="lastModifiedDate", nullable = true)
    //@JsonIgnore
    private Instant lastModifiedDate = Instant.now();

    @PrePersist
    void prePersist() {
        creationDate = Instant.now();
    }

    @PreUpdate
    void preUpdate() {
        lastModifiedDate = Instant.now();
    }
}
