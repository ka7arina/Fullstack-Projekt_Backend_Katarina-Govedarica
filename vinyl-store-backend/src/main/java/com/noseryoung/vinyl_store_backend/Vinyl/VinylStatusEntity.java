package com.noseryoung.vinyl_store_backend.Vinyl;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is the model of the meal categories table.
 */
@Getter
@Setter
@Entity
@Table(name = "vinyl_status")
public class VinylStatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "status_ID")
    private Long id;

    @Column(name = "name")
    private String name;
}