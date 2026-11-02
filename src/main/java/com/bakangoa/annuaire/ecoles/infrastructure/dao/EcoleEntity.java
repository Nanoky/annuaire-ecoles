package com.bakangoa.annuaire.ecoles.infrastructure.dao;

import com.bakangoa.annuaire.ecoles.business.Ecole;
import com.bakangoa.annuaire.ecoles.business.StatutEcole;
import com.bakangoa.annuaire.ecoles.business.Ville;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "ecoles")
public class EcoleEntity {
    @Id
    UUID id;
    @Column(nullable = false)
    String name;
    @Column(unique = true, nullable = false)
    String sigle;
    @JoinColumn(name = "ville_id", referencedColumnName = "id")
    @ManyToOne(
            fetch = FetchType.LAZY
    )
    VilleEntity ville;
    @Column(nullable = false)
    StatutEcole statut;
    @Column(nullable = false)
    String filieres;
    @Column(nullable = false)
    String diplomes;
    @Column(nullable = false)
    String description;
    @Column(nullable = false)
    String address;
    @Column(unique = true, nullable = false)
    String phone;
    @Column(unique = true, nullable = false)
    String email;
    @Column()
    String website;
    @Column(nullable = false, name = "created_at")
    Timestamp createdAt;
    @Column(nullable = true, name = "updated_at")
    Timestamp updatedAt;
    @Column(nullable = true, name = "deleted_at")
    Timestamp deletedAt;

    public EcoleEntity() {}
    private EcoleEntity(
            UUID id,
            String name,
            String sigle,
            VilleEntity ville,
            StatutEcole statut,
            String filieres,
            String diplomes,
            String description,
            String address,
            String phone,
            String email,
            String website,
            Timestamp createdAt,
            Timestamp updatedAt,
            Timestamp deletedAt
    ) {
        this.id = id;
        this.name = name;
        this.sigle = sigle;
        this.ville = ville;
        this.statut = statut;
        this.filieres = filieres;
        this.diplomes = diplomes;
        this.description = description;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.website = website;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static EcoleEntity fromDomain(Ecole ecole) {
        return new EcoleEntity(
                ecole.getId(),
                ecole.getName(),
                ecole.getSigle(),
                VilleEntity.fromDomain(ecole.getVille()),
                ecole.getStatut(),
                String.join(";", ecole.getFilieres()),
                String.join(";", ecole.getDiplomes()),
                ecole.getDescription(),
                ecole.getAddress(),
                ecole.getPhone().value(),
                ecole.getEmail(),
                ecole.getWebsite(),
                Timestamp.from(ecole.getCreatedAt()),
                ecole.getUpdatedAt() != null ? Timestamp.from(ecole.getUpdatedAt()) : null,
                ecole.getDeletedAt() != null ? Timestamp.from(ecole.getDeletedAt()) : null
        );
    }

    public Ecole toDomain() {
        return Ecole.reconstitute(
                this.id,
                this.name,
                this.sigle,
                this.ville.toDomain(),
                this.statut,
                Arrays.stream(this.filieres.split(";")).toList(),
                Arrays.stream(this.diplomes.split(";")).toList(),
                this.description,
                this.address,
                this.phone,
                this.email,
                this.website,
                this.createdAt.toInstant(),
                this.updatedAt != null ? this.updatedAt.toInstant() : null,
                this.deletedAt != null ? this.deletedAt.toInstant() : null
        );
    }
}
