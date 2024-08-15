package noseryoung.ch.vinyl_store.Vinyl;


import org.springframework.data.jpa.repository.JpaRepository;

public interface VinylArtistRepository extends JpaRepository<VinylArtistEntity, Long> {
    VinylArtistEntity findByName(String name);
}