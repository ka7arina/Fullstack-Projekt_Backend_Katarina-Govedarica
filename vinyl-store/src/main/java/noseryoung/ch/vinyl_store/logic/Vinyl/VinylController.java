package noseryoung.ch.vinyl_store.logic.Vinyl;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import noseryoung.ch.vinyl_store.Vinyl.VinylEntity;
import noseryoung.ch.vinyl_store.Vinyl.VinylGenreEntity;
import noseryoung.ch.vinyl_store.Vinyl.VinylArtistEntity;
import noseryoung.ch.vinyl_store.Vinyl.VinylStatusEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vinyls")
public class VinylController {

    @Autowired
    private VinylService service;

    @GetMapping
    public ResponseEntity<List<VinylEntity>> getAllVinylsSortedByGenre(){
        try {
            return ResponseEntity.ok().body(service.getAllVinyls());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<VinylEntity>> getVinylByID(@PathVariable Long id) {
        try {
            return ResponseEntity.ok().body(service.getVinylByID(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }


    @GetMapping("/status")
    public ResponseEntity<List<VinylEntity>> getAllVinylsSortedByStatus() {
        try {
            return ResponseEntity.ok().body(service.getAllVinylsSortedByStatus());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }

    }


    @PostMapping
    public ResponseEntity<VinylEntity> createNewVinyl(@RequestBody VinylEntity vinyl) {
        try {
            return ResponseEntity.ok().body(service.createNewVinyl(vinyl));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<VinylEntity> updateExistingVinyl(@PathVariable Long id, @RequestBody VinylEntity vinyl) {
        try {
            return ResponseEntity.ok().body(service.updateExistingVinyl(id, vinyl));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVinyl(@PathVariable Long id) {
        try {
            service.deleteVinyl(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).build(); // Or another status code you find appropriate
        }
    }

}