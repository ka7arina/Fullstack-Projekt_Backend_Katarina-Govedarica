package com.noseryoung.vinyl_store_backend.Vinyl;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is the model of the vinyl artist table.
 */
@Getter
@Setter
@Entity
@Table(name = "meal_categories")
public class VinylArtistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_ID")
    private Long id;

    @Column(name = "name")
    private String name;
}