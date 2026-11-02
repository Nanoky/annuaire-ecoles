package com.bakangoa.annuaire.ecoles.business;

import com.bakangoa.annuaire.ecoles.infrastructure.repository.PostgresEcoleRepository;
import com.bakangoa.annuaire.ecoles.infrastructure.repository.PostgresVilleRepository;
import com.bakangoa.annuaire.shared.BusinessRuleViolationException;
import com.bakangoa.annuaire.shared.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EcoleBusiness {
    private final PostgresVilleRepository villeRepository;
    private final PostgresEcoleRepository repository;

    public EcoleBusiness(
            PostgresVilleRepository villeRepository,
            PostgresEcoleRepository repository
    ) {
        this.villeRepository = villeRepository;
        this.repository = repository;
    }

    public UUID create(
            String name,
            String sigle,
            String statut,
            UUID villeId,
            List<String> filieres,
            List<String> diplomes,
            String description,
            String address,
            String phone,
            String email,
            String website
    ) {
        // check if ville exist and get
        if (villeId == null) throw new BusinessRuleViolationException("VillId est requis pour créer une école");
        var optionalVille = this.villeRepository.findById(villeId);
        if (optionalVille.isEmpty()) throw new EntityNotFoundException("Aucune ville a pour Id " + villeId.toString());
        var ville = optionalVille.get();

        // create ecole
        var ecole = Ecole.create(
                name,
                sigle,
                ville,
                statut,
                filieres,
                diplomes,
                description,
                address,
                phone,
                email,
                website
        );

        // save ecole
        this.repository.save(ecole);

        return ecole.getId();
    }

    public void update(
            UUID id,
            String name,
            String sigle,
            String statut,
            UUID villeId,
            List<String> filieres,
            List<String> diplomes,
            String description,
            String address,
            String phone,
            String email,
            String website
    ) {
        if (id == null) throw new BusinessRuleViolationException("Impossible de mettre à jour une ecole avec id null");
        var optionalEcole = this.repository.findById(id);
        if (optionalEcole.isEmpty()) throw new EntityNotFoundException("Aucune ecole a pour id " + id.toString());
        var ecole = optionalEcole.get();

        Ville ville = null;
        if (villeId != null) {
            var optionalVille = this.villeRepository.findById(villeId);
            if (optionalVille.isEmpty()) throw new EntityNotFoundException("Aucune ville ne correspond à l'id " + villeId.toString());
            ville = optionalVille.get();
        }

        ecole.update(
                name,
                sigle,
                statut,
                ville,
                filieres,
                diplomes,
                description,
                address,
                phone,
                email,
                website
        );

        this.repository.save(ecole);
    }

    public void delete(UUID id) {
        var optionalEcole = this.repository.findById(id);
        if (optionalEcole.isEmpty()) throw new EntityNotFoundException("Aucune école ne correspond à l'id " + id.toString());
        var ecole = optionalEcole.get();

        ecole.delete();

        repository.save(ecole);
    }
}
