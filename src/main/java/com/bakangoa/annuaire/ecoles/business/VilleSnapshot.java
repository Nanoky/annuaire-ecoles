package com.bakangoa.annuaire.ecoles.business;

import java.time.Instant;
import java.util.UUID;

public record VilleSnapshot(
        UUID id,
        String name,
        Instant createdAt,
        Instant updatedAt
) {
}
