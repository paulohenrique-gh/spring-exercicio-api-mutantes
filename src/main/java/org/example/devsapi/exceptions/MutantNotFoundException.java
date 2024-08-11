package org.example.devsapi.exceptions;

import java.util.UUID;

public class MutantNotFoundException extends RuntimeException {
    public MutantNotFoundException(UUID id) {
        super("Mutant with id " + id + " not found");
    }
}
