package com.bakangoa.annuaire.ecoles.infrastructure.dao;

import com.bakangoa.annuaire.ecoles.business.StatutEcole;
import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

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
    List<String> filieres;
    @Column(nullable = false)
    List<String> diplomes;
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
}
