package noseryoung.ch.vinyl_store.logic.Vinyl;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import noseryoung.ch.vinyl_store.Vinyl.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import noseryoung.ch.vinyl_store.Vinyl.VinylEntity;
import noseryoung.ch.vinyl_store.Vinyl.VinylGenreEntity;
import noseryoung.ch.vinyl_store.Vinyl.VinylArtistEntity;
import noseryoung.ch.vinyl_store.Vinyl.VinylStatusEntity;

@Service
public class VinylService {

    @Autowired
    private VinylRepository vinylRepository;

    @Autowired
    private VinylArtistRepository vinylArtistRepository;

    @Autowired
    private VinylGenreRepository vinylGenreRepository;

    private String getImageAsBase64FromContainer(Long id) throws IOException {
        String baseDir = Paths.get("").toAbsolutePath().toString();
        String imagePath = baseDir + "/vinyl-store/src/main/images/" + id + ".jpg";
        Path path = Paths.get(imagePath);
        if (Files.exists(path)) {
            byte[] imageBytes = Files.readAllBytes(path);
            return Base64.getEncoder().encodeToString(imageBytes);
        } else {
            System.out.println("Image not found for vinyl ID: " + id + " at path: " + imagePath);
            throw new FileNotFoundException("Image not found for vinyl ID: " + id);
        }
    }

    private void saveBase64AsImageToContainer(String base64, VinylEntity vinyl) throws IOException {
        String directoryPath = "vinyl-store/src/main/images/";
        String imagePath = directoryPath + vinyl.getId() + ".jpg";

        Path directory = Paths.get(directoryPath);
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }

        byte[] imageBytes = Base64.getDecoder().decode(base64);
        Files.write(Paths.get(imagePath), imageBytes);
    }

    public List<VinylEntity> getAllVinyls() {
        List<VinylEntity> vinyls = vinylRepository.findAll();
        vinyls.forEach(vinyl -> {
            try {
                vinyl.setImageFilePath(getImageAsBase64FromContainer(vinyl.getId()));
            } catch (IOException ignored) {}
        });
        return vinyls;
    }

    public Optional<VinylEntity> getVinylByID(Long id) {
        return vinylRepository.findById(id).map(vinyl -> {
            try {
                vinyl.setImageFilePath(getImageAsBase64FromContainer(vinyl.getId()));
            } catch (IOException ignored) {}
            return vinyl;
        });
    }

    public List<VinylEntity> getAllVinylsSortedByStatus() {
        List<VinylEntity> vinyls = vinylRepository.findAll(
                Sort.by(Sort.Direction.DESC, "idStatus").and(Sort.by(Sort.Direction.ASC, "name"))
        );
        vinyls.forEach(vinyl -> {
            try {
                vinyl.setImageFilePath(getImageAsBase64FromContainer(vinyl.getId()));
            } catch (IOException ignored) {}
        });
        return vinyls;
    }

    public VinylEntity createNewVinyl(VinylEntity newVinyl) throws Exception {
        // Validate the input
        if (newVinyl == null) throw new IllegalArgumentException("Vinyl cannot be null");

        boolean invalid = newVinyl.getTitle().isEmpty() ||
                newVinyl.getPrice().isNaN() ||
                newVinyl.getPrice().isInfinite() ||
                newVinyl.getPrice() < 0.0 ||
                newVinyl.getGenre() == null;

        if (invalid) throw new IllegalArgumentException("Invalid vinyl data");

        newVinyl = vinylRepository.save(newVinyl);
        String imagePath = newVinyl.getImageFilePath();
        if (imagePath != null && !imagePath.startsWith("/images/")) {
            newVinyl.setImageFilePath("/images/" + imagePath);
        }

        return vinylRepository.save(newVinyl);
    }

    public VinylEntity updateExistingVinyl(Long id, VinylEntity existingVinyl) throws Exception {
        VinylEntity oldVinyl = vinylRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vinyl not found."));

        boolean invalid = existingVinyl.getTitle().isEmpty() ||
                Double.isNaN(existingVinyl.getPrice()) ||
                Double.isInfinite(existingVinyl.getPrice()) ||
                existingVinyl.getPrice() < 0.0 ||
                existingVinyl.getGenre() == null;

        if (invalid) {
            throw new IllegalArgumentException("Invalid input provided.");
        }

        oldVinyl.setTitle(existingVinyl.getTitle());
        oldVinyl.setPrice(existingVinyl.getPrice());
        oldVinyl.setReleaseYear(existingVinyl.getReleaseYear());
        oldVinyl.setStatus(existingVinyl.getStatus());
        oldVinyl.setGenre(existingVinyl.getGenre());

        String imagePath = existingVinyl.getImageFilePath();
        if (imagePath != null && !imagePath.startsWith("/images/")) {
            oldVinyl.setImageFilePath("/images/" + imagePath);
        } else {
            oldVinyl.setImageFilePath(imagePath);
        }

        return vinylRepository.save(oldVinyl);
    }

    public void deleteVinyl(Long id) {
        VinylEntity vinyl = vinylRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vinyl not found with ID: " + id));
        vinylRepository.delete(vinyl);
    }
}
