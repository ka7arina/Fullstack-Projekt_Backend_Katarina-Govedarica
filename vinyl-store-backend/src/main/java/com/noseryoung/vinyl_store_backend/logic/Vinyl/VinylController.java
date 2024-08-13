package com.noseryoung.vinyl_store_backend.logic.Vinyl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.noseryoung.vinyl_store_backend.Vinyl.VinylEntity;
import com.noseryoung.vinyl_store_backend.Vinyl.VinylGenreEntity;
import com.noseryoung.vinyl_store_backend.Vinyl.VinylArtistEntity;
import com.noseryoung.vinyl_store_backend.Vinyl.VinylStatusEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vinyls")
public class VinylController {

    @Autowired
    private VinylService vinylService;

    @GetMapping
    @Operation(summary = "returns all vinyls, sorted by their genre id")
    public ResponseEntity<List<VinylEntity>> getAllVinylsSortedByGenre(){
        try {
            return ResponseEntity.ok().body(service.getAllVinyls());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/{id}")
    @Operation(summary = "returns a vinyl with the same id")
    public ResponseEntity<Optional<VinylEntity>> getVinylByID(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(service.getVinylByID(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/genres")
    @Operation(summary = "returns all vinyl genres")
    public ResponseEntity<List<VinylGenreEntity>> getAllVinylGenres(){
        try {
            return ResponseEntity.ok().body(service.getAllVinylGenres());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/status")
    @Operation(summary = "returns all vinyls, sorted by status")
    public ResponseEntity<List<VinylStatusEntity>> getAllVinylsSortedByStatus() {
        try {
            return ResponseEntity.ok().body(service.getAllVinylsSortedByStatus());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }


    @PostMapping
    @Operation(summary = "creates a new vinyl in the database")
    public ResponseEntity<VinylEntity> createNewVinyl(@RequestBody VinylEntity vinyl) {
        try {
            return ResponseEntity.ok().body(service.createNewVinyl(vinyl));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "updates a vinyl in the database")
    public ResponseEntity<VinylEntity> updateExistingVinyl(@PathVariable Long id, @RequestBody VinylEntity vinyl) {
        try {
            return ResponseEntity.ok().body(service.updateExistingVinyl(id, vinyl));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}