package com.bakangoa.annuaire.ecoles.business;

import com.bakangoa.annuaire.shared.BusinessRuleViolationException;

import java.time.Instant;
import java.util.UUID;

public class Ville {
    private UUID id;
    private String name;

    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Ville(UUID id, String name, Instant createdAt, Instant updatedAt, Instant deletedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static Ville create(String name) {
        if (name == null || name.isBlank()) throw new BusinessRuleViolationException("Le nom d'une ville ne peut être null ou vide");
        var id = UUID.randomUUID();
        return new Ville(id, name, Instant.now(), null, null);
    }

    public static Ville reconstitute(UUID id, String name, Instant createdAt, Instant updatedAt, Instant deletedAt) {
        return new Ville(id, name, createdAt, updatedAt, deletedAt);
    }

    public void updateName(String name) {
        this.name = name;
        this.updatedAt = Instant.now();
    }

    public void delete() {
        this.deletedAt = Instant.now();
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Instant getCreatedAt() {
        return this.createdAt;
    }

    public Instant getUpdatedAt() {
        return this.updatedAt;
    }

    public Instant getDeletedAt() {
        return this.deletedAt;
    }
}
