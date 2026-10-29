package com.bakangoa.annuaire.ecoles.business;

import com.bakangoa.annuaire.shared.BusinessRuleViolationException;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class Ecole {
    private UUID id;
    private String name;
    private String sigle;
    private Ville ville;
    private StatutEcole statut;
    private List<String> filieres;
    private List<String> diplomes;
    private String description;
    private String address;
    private Phone phone;
    private String email;
    private String website;

    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Ecole(
            UUID id,
            String name,
            String sigle,
            Ville ville,
            StatutEcole statut,
            List<String> filieres,
            List<String> diplomes,
            String description,
            String address,
            Phone phone,
            String email,
            String website,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        this.id = id;
        this.name = name;
        this.sigle = sigle;
        this.ville = ville;
        this.statut = statut;
        this.filieres = List.copyOf(filieres);
        this.diplomes = List.copyOf(diplomes);
        this.description = description;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deletedAt = deletedAt;
    }

    public static Ecole create(
            String name,
            String sigle,
            Ville ville,
            String statut,
            List<String> filieres,
            List<String> diplomes,
            String description,
            String address,
            String phone,
            String email,
            String website
    ) {
        if (name == null || name.isBlank()) throw new BusinessRuleViolationException("Le nom d'une école ne peut pas être null ou vide");
        if (sigle == null || sigle.isBlank()) throw new BusinessRuleViolationException("Le sigle d'une école ne peut pas être null ou vide");
        if (ville == null) throw new BusinessRuleViolationException("une école doit obligatoirement être liée à une ville");
        var finalStatut = statut == null ? StatutEcole.PUBLIC : StatutEcole.of(statut);
        if (filieres.isEmpty()) throw new BusinessRuleViolationException("Une école doit avoir au moins 1 filière");
        if (diplomes.isEmpty()) throw new BusinessRuleViolationException("une école doit délivrer au moins 1 diplôme");
        return new Ecole(
                UUID.randomUUID(),
                name,
                sigle,
                ville,
                finalStatut,
                filieres,
                diplomes,
                description,
                address,
                Phone.of(phone),
                email,
                website,
                Instant.now(),
                null,
                null
        );
    }

    public static Ecole reconstitute(
            UUID id,
            String name,
            String sigle,
            Ville ville,
            StatutEcole statut,
            List<String> filieres,
            List<String> diplomes,
            String description,
            String address,
            String phone,
            String email,
            String website,
            Instant createdAt,
            Instant updatedAt,
            Instant deletedAt
    ) {
        return new Ecole(
                id,
                name,
                sigle,
                ville,
                statut,
                filieres,
                diplomes,
                description,
                address,
                Phone.of(phone),
                email,
                website,
                createdAt,
                updatedAt,
                deletedAt
        );
    }
}
