package noseryoung.ch.vinyl_store.logic.Vinyl;

import noseryoung.ch.vinyl_store.Vinyl.VinylArtistEntity;
import noseryoung.ch.vinyl_store.Vinyl.VinylArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VinylArtistService {

    @Autowired
    private VinylArtistRepository vinylArtistRepository;

    public List<VinylArtistEntity> getAllArtists() {
        return vinylArtistRepository.findAll();
    }

    public Optional<VinylArtistEntity> getArtistById(Long id) {
        return vinylArtistRepository.findById(id);
    }

    public VinylArtistEntity createNewArtist(VinylArtistEntity artist) {
        return vinylArtistRepository.save(artist);
    }

    public VinylArtistEntity updateArtist(Long id, VinylArtistEntity updatedArtist) {
        Optional<VinylArtistEntity> existingArtist = vinylArtistRepository.findById(id);

        if (existingArtist.isPresent()) {
            VinylArtistEntity artist = existingArtist.get();
            artist.setName(updatedArtist.getName());
            return vinylArtistRepository.save(artist);
        } else {
            throw new RuntimeException("Artist not found");
        }
    }

    public void deleteArtist(Long id) {
        vinylArtistRepository.deleteById(id);
    }
}
