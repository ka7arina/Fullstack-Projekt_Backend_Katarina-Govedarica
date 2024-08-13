package com.noseryoung.vinyl_store_backend.Vinyl;


import org.springframework.data.jpa.repository.JpaRepository;

public interface VinylRepository extends JpaRepository<VinylEntity, Long> {}