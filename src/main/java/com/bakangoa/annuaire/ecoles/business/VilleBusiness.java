package com.bakangoa.annuaire.ecoles.business;

import com.bakangoa.annuaire.ecoles.infrastructure.repository.PostgresVilleRepository;
import com.bakangoa.annuaire.shared.EntityNotFoundException;
import com.bakangoa.annuaire.shared.Page;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VilleBusiness {
    private final PostgresVilleRepository repository;
    public VilleBusiness(PostgresVilleRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public UUID create(String name) {
        var ville = Ville.create(name);
        this.repository.save(ville);
        return ville.getId();
    }

    @Transactional
    public void update(UUID id, String name) {
        var optionalVille = this.repository.findById(id);
        if (optionalVille.isEmpty()) throw new EntityNotFoundException("Aucune ville trouvé pour l'id " + id.toString());

        var ville = optionalVille.get();
        ville.updateName(name);

        this.repository.save(ville);
    }

    @Transactional
    public void delete(UUID id) {
        var optionalVille = this.repository.findById(id);
        if (optionalVille.isEmpty()) throw new EntityNotFoundException("Aucune ville trouvé pour l'id " + id.toString());

        var ville = optionalVille.get();
        ville.delete();

        this.repository.save(ville);
    }

    public Page<VilleSnapshot> search(String pattern, Integer page, Integer size) {
        return this.repository.search(pattern, page, size);
    }
}
