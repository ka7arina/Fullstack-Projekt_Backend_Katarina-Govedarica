package noseryoung.ch.vinyl_store.logic.Vinyl;

import noseryoung.ch.vinyl_store.Vinyl.VinylArtistEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class VinylArtistController {

    @Autowired
    private VinylArtistService artistService;

    @GetMapping
    public ResponseEntity<List<VinylArtistEntity>> getAllArtists() {
        return ResponseEntity.ok().body(artistService.getAllArtists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<VinylArtistEntity>> getArtistById(@PathVariable Long id) {
        return ResponseEntity.ok().body(artistService.getArtistById(id));
    }

    @PostMapping
    public ResponseEntity<VinylArtistEntity> createNewArtist(@RequestBody VinylArtistEntity artist) {
        return ResponseEntity.ok().body(artistService.createNewArtist(artist));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VinylArtistEntity> updateArtist(@PathVariable Long id, @RequestBody VinylArtistEntity artist) {
        return ResponseEntity.ok().body(artistService.updateArtist(id, artist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);
        return ResponseEntity.noContent().build();
    }
}
