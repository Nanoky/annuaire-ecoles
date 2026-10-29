package com.bakangoa.annuaire.ecoles.business;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EcoleBusiness {

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

        // create ecole

        // save ecole

        return null;
    }
}
