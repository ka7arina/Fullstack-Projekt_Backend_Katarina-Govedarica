package noseryoung.ch.vinyl_store.logic.Vinyl;

import noseryoung.ch.vinyl_store.Vinyl.VinylGenreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/genres")
public class VinylGenreController {

    @Autowired
    private VinylGenreService genreService;

    @GetMapping
    public ResponseEntity<List<VinylGenreEntity>> getAllGenres() {
        return ResponseEntity.ok().body(genreService.getAllGenres());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<VinylGenreEntity>> getGenreById(@PathVariable Long id) {
        return ResponseEntity.ok().body(genreService.getGenreById(id));
    }

    @PostMapping
    public ResponseEntity<VinylGenreEntity> createNewGenre(@RequestBody VinylGenreEntity genre) {
        return ResponseEntity.ok().body(genreService.createNewGenre(genre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VinylGenreEntity> updateGenre(@PathVariable Long id, @RequestBody VinylGenreEntity genre) {
        return ResponseEntity.ok().body(genreService.updateGenre(id, genre));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long id) {
        genreService.deleteGenre(id);
        return ResponseEntity.noContent().build();
    }
}
