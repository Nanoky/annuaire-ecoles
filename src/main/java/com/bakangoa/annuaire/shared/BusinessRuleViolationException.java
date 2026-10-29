package com.bakangoa.annuaire.shared;

public class BusinessRuleViolationException extends DomainException {
    public BusinessRuleViolationException(String message) {
        super(message);
    }
}
