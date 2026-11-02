package com.bakangoa.annuaire.ecoles.business;

import com.bakangoa.annuaire.shared.BusinessRuleViolationException;
import com.bakangoa.annuaire.shared.utilities.Email;

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
        if (description == null || description.isBlank()) throw new BusinessRuleViolationException("Une école ne peut avoir de description null ou vide");
        if (address == null || address.isBlank()) throw new BusinessRuleViolationException("Une école ne peut avoir d'adresse null ou vide");
        if (email == null || !Email.isValid(email)) throw new BusinessRuleViolationException("Une école ne peut avoir d'email null ou invalide");

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

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSigle() {
        return sigle;
    }

    public Ville getVille() {
        return ville;
    }

    public StatutEcole getStatut() {
        return statut;
    }

    public List<String> getFilieres() {
        return List.copyOf(filieres);
    }

    public List<String> getDiplomes() {
        return List.copyOf(diplomes);
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public Phone getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public void update(
            String name,
            String sigle,
            String statut,
            Ville ville,
            List<String> filieres,
            List<String> diplomes,
            String description,
            String address,
            String phone,
            String email,
            String website
    ) {
        if (name != null) {
            if (name.isBlank()) throw new BusinessRuleViolationException("Le nom d'une école ne peut être vide");
            this.name = name;
            this.updatedAt = Instant.now();
        }

        if (sigle != null) {
            if (sigle.isBlank()) throw new BusinessRuleViolationException("Le sigle d'une école ne peut être vide");
            this.sigle = sigle;
            this.updatedAt = Instant.now();
        }

        if (statut != null) {
            this.statut = StatutEcole.of(statut);
            this.updatedAt = Instant.now();
        }

        if (ville != null) {
            this.ville = ville;
            this.updatedAt = Instant.now();
        }

        if (filieres != null) {
            this.filieres = List.copyOf(filieres);
            this.updatedAt = Instant.now();
        }

        if (diplomes != null) {
            this.diplomes = List.copyOf(diplomes);
            this.updatedAt = Instant.now();
        }

        if (description != null) {
            this.description = description;
            this.updatedAt = Instant.now();
        }

        if (address != null) {
            if (address.isBlank())  throw new BusinessRuleViolationException("Une adresse d'ecole ne peut être vide");
            this.address = address;
            this.updatedAt = Instant.now();
        }

        if (phone != null) {
            if (phone.isBlank()) throw new BusinessRuleViolationException("Le numéro de téléphone d'une école ne peut être vide");
            this.phone = Phone.of(phone);
            this.updatedAt = Instant.now();
        }

        if (email != null) {
            if (!Email.isValid(email)) throw new BusinessRuleViolationException("L'email d'une école doit être valide");
            this.email = email;
            this.updatedAt = Instant.now();
        }

        if (website != null) {
            this.website = website;
            this.updatedAt = Instant.now();
        }
    }
}
