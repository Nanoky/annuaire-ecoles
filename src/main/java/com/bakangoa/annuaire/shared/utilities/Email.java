package com.bakangoa.annuaire.shared.utilities;

import org.apache.commons.validator.routines.EmailValidator;

public class Email {
    public static boolean isValid(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
