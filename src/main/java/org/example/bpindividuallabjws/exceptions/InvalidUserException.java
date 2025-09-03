package org.example.bpindividuallabjws.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUserException extends RuntimeException {

    private String username;
    private String task;
    private String object;

    public InvalidUserException(String username, String task, String object) {
        super(String.format("User with username '%s' is not authorised to '%s' a '%s' created by another user", username, task, object));

        this.username = username;
        this.task = task;
        this.object = object;

    }

    public String getUsername() {
        return username;
    }

    public String getTask() {
        return task;
    }

    public String getObject() {
        return object;
    }
}
