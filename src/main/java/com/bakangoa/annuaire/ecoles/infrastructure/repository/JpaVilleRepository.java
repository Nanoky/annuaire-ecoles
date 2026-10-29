package com.bakangoa.annuaire.ecoles.infrastructure.repository;

import com.bakangoa.annuaire.ecoles.infrastructure.dao.VilleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaVilleRepository extends CrudRepository<VilleEntity, UUID> {
    public Optional<VilleEntity> findByIdAndDeletedAtNull(UUID id);
}
