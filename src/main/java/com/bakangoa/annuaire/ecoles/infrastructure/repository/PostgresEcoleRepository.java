package com.bakangoa.annuaire.ecoles.infrastructure.repository;

import com.bakangoa.annuaire.ecoles.business.Ecole;
import com.bakangoa.annuaire.ecoles.infrastructure.dao.EcoleEntity;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class PostgresEcoleRepository {
    private final JpaEcoleRepository repository;
    private final EntityManager entityManager;

    public PostgresEcoleRepository(JpaEcoleRepository repository, EntityManager entityManager) {
        this.repository = repository;
        this.entityManager = entityManager;
    }

    public void save(Ecole ecole) {
        this.repository.save(EcoleEntity.fromDomain(ecole));
    }

    public Optional<Ecole> findById(UUID id) {
        return this.repository.findByIdAndDeletedAtNull(id).map(EcoleEntity::toDomain);
    }
}
