package com.bakangoa.annuaire.ecoles.business;

import com.bakangoa.annuaire.shared.BusinessRuleViolationException;

public record Phone(
        String value
) {
    public static Phone of(String value) {
        if (value == null) throw new BusinessRuleViolationException("La valeur d'un numéro de téléphone ne peut être null");
        if (!value.matches("^(?:\\+225|00225)?\\s?(?:0[157]|2[157])[\\s.-]?[0-9]{2}[\\s.-]?[0-9]{2}[\\s.-]?[0-9]{2}[\\s.-]?[0-9]{2}$")) {
            throw new BusinessRuleViolationException("Le numéro de téléphone doit respecter le format en vigueru en Côte d'Ivoire");
        }
        return new Phone(value);
    }
}
