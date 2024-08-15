package noseryoung.ch.vinyl_store.logic.Vinyl;

import noseryoung.ch.vinyl_store.Vinyl.VinylStatusEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/statuses")
public class VinylStatusController {

    @Autowired
    private VinylStatusService statusService;

    @GetMapping
    public ResponseEntity<List<VinylStatusEntity>> getAllStatuses() {
        return ResponseEntity.ok().body(statusService.getAllStatuses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<VinylStatusEntity>> getStatusById(@PathVariable Long id) {
        return ResponseEntity.ok().body(statusService.getStatusById(id));
    }

    @PostMapping
    public ResponseEntity<VinylStatusEntity> createNewStatus(@RequestBody VinylStatusEntity status) {
        return ResponseEntity.ok().body(statusService.createNewStatus(status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VinylStatusEntity> updateStatus(@PathVariable Long id, @RequestBody VinylStatusEntity status) {
        return ResponseEntity.ok().body(statusService.updateStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStatus(@PathVariable Long id) {
        statusService.deleteStatus(id);
        return ResponseEntity.noContent().build();
    }
}
