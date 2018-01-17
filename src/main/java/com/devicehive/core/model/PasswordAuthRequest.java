package com.devicehive.core.model;

public class PasswordAuthRequest {

    private String login;
    private String password;

    public PasswordAuthRequest(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
