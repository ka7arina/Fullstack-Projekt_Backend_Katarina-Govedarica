package com.noseryoung.vinyl_store_backend.Vinyl;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is the model of the vinyl genre table.
 */
@Getter
@Setter
@Entity
@Table(name = "vinyl_genre")
public class VinylGenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_ID")
    private Long id;

    @Column(name = "name")
    private String name;
}