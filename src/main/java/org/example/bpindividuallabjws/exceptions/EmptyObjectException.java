package org.example.bpindividuallabjws.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyObjectException extends RuntimeException {
   private String object;
   private String field;



    public EmptyObjectException(String object, String field) {
        super(String.format("The %s you are trying to create has no '%s' value.", object, field));
        this.object = object;
        this.field = field;
    }

  public String getObject() {
    return object;
  }

  public String getField() {
    return field;
  }
}
