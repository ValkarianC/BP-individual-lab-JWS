package org.example.bpindividuallabjws.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ACCEPTED)
public class BlankFieldException extends RuntimeException {

    private String object;
    private String field;
    private String identifier;
    private Object identifierValue;
    public BlankFieldException(String object, String field, String identifier, Object identifierValue) {
        super(String.format("The %s you have created has an empty '%s' field. The %s has been created with %s: %s", object, field, object, identifier, identifierValue));

        this.object = object;
        this.field = field;
        this.identifier = identifier;
        this.identifierValue = identifierValue;
    }

    public String getObject() {
        return object;
    }

    public String getField() {
        return field;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Object getIdentifierValue() {
        return identifierValue;
    }
}
