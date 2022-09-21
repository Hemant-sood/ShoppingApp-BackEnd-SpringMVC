package com.models;

public class AuthResponseObject {

    private String email, name, error;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }


    public AuthResponseObject(String email, String name, String error) {
        this.email = email;
        this.name = name;
        this.error = error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getError() {
        return error;
    }

}
