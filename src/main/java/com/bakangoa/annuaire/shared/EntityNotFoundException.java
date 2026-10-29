package com.bakangoa.annuaire.shared;

public class EntityNotFoundException extends DomainException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
