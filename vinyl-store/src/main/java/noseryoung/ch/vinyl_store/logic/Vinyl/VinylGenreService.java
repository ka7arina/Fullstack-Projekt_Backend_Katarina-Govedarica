package noseryoung.ch.vinyl_store.logic.Vinyl;

import noseryoung.ch.vinyl_store.Vinyl.VinylGenreEntity;
import noseryoung.ch.vinyl_store.Vinyl.VinylGenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VinylGenreService {

    @Autowired
    private VinylGenreRepository vinylGenreRepository;

    public List<VinylGenreEntity> getAllGenres() {
        return vinylGenreRepository.findAll();
    }

    public Optional<VinylGenreEntity> getGenreById(Long id) {
        return vinylGenreRepository.findById(id);
    }

    public VinylGenreEntity createNewGenre(VinylGenreEntity genre) {
        return vinylGenreRepository.save(genre);
    }

    public VinylGenreEntity updateGenre(Long id, VinylGenreEntity updatedGenre) {
        Optional<VinylGenreEntity> existingGenre = vinylGenreRepository.findById(id);

        if (existingGenre.isPresent()) {
            VinylGenreEntity genre = existingGenre.get();
            genre.setName(updatedGenre.getName());
            return vinylGenreRepository.save(genre);
        } else {
            throw new RuntimeException("Genre not found");
        }
    }

    public void deleteGenre(Long id) {
        vinylGenreRepository.deleteById(id);
    }
}
