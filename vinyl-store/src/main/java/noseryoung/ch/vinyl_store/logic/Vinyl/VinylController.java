package noseryoung.ch.vinyl_store.logic.Vinyl;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<VinylEntity>> getAllVinyls() {
        List<VinylEntity> vinyls = service.getAllVinyls();
        return ResponseEntity.ok(vinyls);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VinylEntity> getVinylByID(@PathVariable Long id) {
        return service.getVinylByID(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/status")
    public ResponseEntity<List<VinylEntity>> getAllVinylsSortedByStatus() {
        List<VinylEntity> vinyls = service.getAllVinylsSortedByStatus();
        return ResponseEntity.ok(vinyls);
    }

    @PostMapping
    public ResponseEntity<VinylEntity> createNewVinyl(@RequestBody VinylEntity vinyl) {
        try {
            VinylEntity createdVinyl = service.createNewVinyl(vinyl);
            return ResponseEntity.ok(createdVinyl);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<VinylEntity> updateExistingVinyl(@PathVariable Long id, @RequestBody VinylEntity vinyl) {
        try {
            VinylEntity updatedVinyl = service.updateExistingVinyl(id, vinyl);
            return ResponseEntity.ok(updatedVinyl);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
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
            return ResponseEntity.status(500).build();
        }
    }
}
