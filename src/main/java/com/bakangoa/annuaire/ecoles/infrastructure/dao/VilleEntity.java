package com.bakangoa.annuaire.ecoles.infrastructure.dao;

import com.bakangoa.annuaire.ecoles.business.Ville;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(name = "villes")
public class VilleEntity {
    @Id
    public UUID id;
    @Column(name = "name", unique = true)
    public String name;
    @Column(name = "created_at", nullable = false)
    public Timestamp createdAt;
    @Column(name = "updated_at", nullable = true)
    public Timestamp updatedAt;
    @Column(name = "deleted_at", nullable = true)
    public Timestamp deletedAt;

    public VilleEntity() {}
    private VilleEntity(UUID id, String name, Timestamp createdAt, Timestamp updatedAt, Timestamp deletedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static VilleEntity fromDomain(Ville ville) {
        return new VilleEntity(
                ville.getId(),
                ville.getName(),
                Timestamp.from(ville.getCreatedAt()),
                ville.getUpdatedAt() != null ? Timestamp.from(ville.getUpdatedAt()) : null,
                ville.getDeletedAt() != null ? Timestamp.from(ville.getDeletedAt()) : null
        );
    }

    public Ville toDomain() {
        return Ville.reconstitute(
                this.id,
                this.name,
                this.createdAt.toInstant(),
                this.updatedAt != null ? this.updatedAt.toInstant() : null,
                this.deletedAt != null ? this.deletedAt.toInstant() : null
        );
    }
}
