package com.bakangoa.annuaire.ecoles.presentation;

import com.bakangoa.annuaire.ecoles.business.VilleBusiness;
import com.bakangoa.annuaire.ecoles.business.VilleSnapshot;
import com.bakangoa.annuaire.shared.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class VilleController {
    private final VilleBusiness business;

    public VilleController(VilleBusiness business) {
        this.business = business;
    }

    @PostMapping("/villes")
    public ResponseEntity<UUID> create(
            @RequestBody String name
    ) {
        var res = this.business.create(name);
        return ResponseEntity.ok(res);
    }

    @PutMapping("/villes/:id")
    public ResponseEntity<Void> update(
            @RequestBody String name,
            @PathVariable(name = "id") String id
    ) {
        this.business.update(UUID.fromString(id), name);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/villes/:id")
    public ResponseEntity<Void> delete(
            @PathVariable(name = "id") String id
    ) {
        this.business.delete(UUID.fromString(id));
        return ResponseEntity.ok(null);
    }

    @GetMapping("/villes")
    public ResponseEntity<Page<VilleSnapshot>> search(
            @RequestParam(name = "q") String query,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(this.business.search(query, page, size));
    }
}
