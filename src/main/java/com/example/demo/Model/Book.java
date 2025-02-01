package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(precision = 10, scale = 2)
    private BigDecimal price; // Correct type for DECIMAL(10,2)

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;
}

