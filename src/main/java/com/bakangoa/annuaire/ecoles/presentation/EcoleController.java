package com.bakangoa.annuaire.ecoles.presentation;
import com.bakangoa.annuaire.ecoles.business.EcoleBusiness;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class EcoleController {
    private final EcoleBusiness business;
    public EcoleController(EcoleBusiness business) {
        this.business = business;
    }

    @PostMapping(path = "/ecoles")
    public ResponseEntity<UUID> create(
            @RequestBody String name,
            @RequestBody String sigle,
            @RequestBody String statut,
            @RequestBody UUID villeId,
            @RequestBody List<String> filieres,
            @RequestBody List<String> diplomes,
            @RequestBody String description,
            @RequestBody String address,
            @RequestBody String phone,
            @RequestBody String email,
            @RequestBody String website
    ) {
        var res = business.create(
                name,
                sigle,
                statut,
                villeId,
                filieres,
                diplomes,
                description,
                address,
                phone,
                email,
                website
        );

        return ResponseEntity.ok(res);
    }

    @PutMapping(path = "/ecoles/{id}")
    public ResponseEntity<Void> update(
            @PathVariable("id") String id,
            @RequestBody String name,
            @RequestBody String sigle,
            @RequestBody String statut,
            @RequestBody UUID villeId,
            @RequestBody List<String> filieres,
            @RequestBody List<String> diplomes,
            @RequestBody String description,
            @RequestBody String address,
            @RequestBody String phone,
            @RequestBody String email,
            @RequestBody String website
    ) {
        business.update(
                UUID.fromString(id),
                name,
                sigle,
                statut,
                villeId,
                filieres,
                diplomes,
                description,
                address,
                phone,
                email,
                website
        );

        return ResponseEntity.ok().build();
    }
}
