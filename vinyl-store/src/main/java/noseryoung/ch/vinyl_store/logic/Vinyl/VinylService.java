package noseryoung.ch.vinyl_store.logic.Vinyl;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import noseryoung.ch.vinyl_store.Vinyl.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

        // Ensure the directory exists
        Path directory = Paths.get(directoryPath);
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }

        byte[] imageBytes = Base64.getDecoder().decode(base64);
        Files.write(Paths.get(imagePath), imageBytes);
    }


    protected List<VinylEntity> getAllVinyls() throws Exception {
        List<VinylEntity> vinyls = vinylRepository.findAll(
                Sort.by(Sort.Direction.ASC, "idGenre")
                        .and(Sort.by(Sort.Direction.ASC, "name")));
        for (VinylEntity vinyl : vinyls) {
            try {
                vinyl.setImageFilePath(getImageAsBase64FromContainer(vinyl.getId()));
            } catch (FileNotFoundException e) {
                // Log the error and proceed without setting the image path
                System.err.println("Error: " + e.getMessage());
            }
        }
        return vinyls;
    }


    protected Optional<VinylEntity> getVinylByID(Long id) throws Exception {
        Optional<VinylEntity> vinyl = vinylRepository.findById(id);
        if (vinyl.isPresent()) {
            try {
                vinyl.get().setImageFilePath(getImageAsBase64FromContainer(vinyl.get().getId()));
            } catch (FileNotFoundException e) {
                // Log the error and proceed without setting the image path
                System.err.println("Error: " + e.getMessage());
            }
        }
        return vinyl;
    }


    protected List<VinylEntity> getAllVinylsSortedByStatus() throws Exception {
        List<VinylEntity> vinyls = vinylRepository.findAll(
                Sort.by(Sort.Direction.DESC, "idStatus")
                        .and(Sort.by(Sort.Direction.ASC, "name")));
        for (VinylEntity vinyl : vinyls) {
            try {
                vinyl.setImageFilePath(getImageAsBase64FromContainer(vinyl.getId()));
            } catch (FileNotFoundException ignored) {}
        }
        return vinyls;
    }

    protected VinylEntity createNewVinyl(VinylEntity newVinyl) throws Exception {
        // Validate the input
        if (newVinyl == null) throw new IllegalArgumentException("Vinyl cannot be null");

        boolean invalid = newVinyl.getTitle().isEmpty() ||
                newVinyl.getPrice().isNaN() ||
                newVinyl.getPrice().isInfinite() ||
                newVinyl.getPrice() < 0.0 ||
                newVinyl.getGenre() == null;

        if (invalid) throw new IllegalArgumentException("Invalid vinyl data");

        // Save the vinyl and obtain its ID
        newVinyl = vinylRepository.save(newVinyl);

        // Ensure the image path is relative to the static folder and store it in the database
        String imagePath = newVinyl.getImageFilePath();
        if (imagePath != null && !imagePath.startsWith("/images/")) {
            newVinyl.setImageFilePath("/images/" + imagePath);
        }

        return vinylRepository.save(newVinyl);
    }

    protected VinylEntity updateExistingVinyl(Long id, VinylEntity existingVinyl) throws Exception {
        Optional<VinylEntity> oldVinylOpt = vinylRepository.findById(id);

        if (!oldVinylOpt.isPresent()) {
            throw new EntityNotFoundException("Vinyl not found.");
        }

        VinylEntity oldVinyl = oldVinylOpt.get();

        // Validate the input
        boolean invalid = existingVinyl.getTitle().isEmpty() ||
                Double.isNaN(existingVinyl.getPrice()) ||
                Double.isInfinite(existingVinyl.getPrice()) ||
                existingVinyl.getPrice() < 0.0 ||
                existingVinyl.getGenre() == null;

        if (invalid) {
            throw new IllegalArgumentException("Invalid input provided.");
        }

        // Update text and number variables
        oldVinyl.setTitle(existingVinyl.getTitle());
        oldVinyl.setPrice(existingVinyl.getPrice());
        oldVinyl.setReleaseYear(existingVinyl.getReleaseYear());
        oldVinyl.setStatus(existingVinyl.getStatus());
        oldVinyl.setGenre(existingVinyl.getGenre());

        // Update image path if provided
        String imagePath = existingVinyl.getImageFilePath();
        if (imagePath != null && !imagePath.startsWith("/images/")) {
            oldVinyl.setImageFilePath("/images/" + imagePath);
        } else {
            oldVinyl.setImageFilePath(imagePath);
        }

        return vinylRepository.save(oldVinyl);
    }

    public void deleteVinyl(Long id) throws Exception {
        Optional<VinylEntity> vinyl = vinylRepository.findById(id);

        if (vinyl.isPresent()) {
            vinylRepository.delete(vinyl.get());
        } else {
            throw new EntityNotFoundException("Vinyl not found with ID: " + id);
        }
    }


}
