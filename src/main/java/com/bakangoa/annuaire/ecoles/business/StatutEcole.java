package com.bakangoa.annuaire.ecoles.business;

import com.bakangoa.annuaire.shared.BusinessRuleViolationException;

public enum StatutEcole {
    PRIVE,
    PUBLIC;

    public static StatutEcole of(String value) {
        if (value.equals("public")) return StatutEcole.PUBLIC;
        if (value.equals("private")) return StatutEcole.PRIVE;
        throw new BusinessRuleViolationException("Le statut d'une école ne être que public ou private");
    }
}
