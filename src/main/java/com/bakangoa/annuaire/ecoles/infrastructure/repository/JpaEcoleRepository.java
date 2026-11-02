package com.bakangoa.annuaire.ecoles.infrastructure.repository;

import com.bakangoa.annuaire.ecoles.infrastructure.dao.EcoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaEcoleRepository extends CrudRepository<EcoleEntity, UUID> {
    public Optional<EcoleEntity> findByIdAndDeletedAtNull(UUID id);
}
