package noseryoung.ch.vinyl_store.logic.Vinyl;

import noseryoung.ch.vinyl_store.Vinyl.VinylStatusEntity;
import noseryoung.ch.vinyl_store.Vinyl.VinylStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VinylStatusService {

    @Autowired
    private VinylStatusRepository vinylStatusRepository;

    public List<VinylStatusEntity> getAllStatuses() {
        return vinylStatusRepository.findAll();
    }

    public Optional<VinylStatusEntity> getStatusById(Long id) {
        return vinylStatusRepository.findById(id);
    }

    public VinylStatusEntity createNewStatus(VinylStatusEntity status) {
        return vinylStatusRepository.save(status);
    }

    public VinylStatusEntity updateStatus(Long id, VinylStatusEntity updatedStatus) {
        Optional<VinylStatusEntity> existingStatus = vinylStatusRepository.findById(id);

        if (existingStatus.isPresent()) {
            VinylStatusEntity status = existingStatus.get();
            status.setName(updatedStatus.getName());
            return vinylStatusRepository.save(status);
        } else {
            throw new RuntimeException("Status not found");
        }
    }

    public void deleteStatus(Long id) {
        vinylStatusRepository.deleteById(id);
    }
}
