package org.example.db;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "persona")
public class Book {

    @Id
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String isbn, title, author;

    @Getter @Setter
    private BigDecimal price;



}
