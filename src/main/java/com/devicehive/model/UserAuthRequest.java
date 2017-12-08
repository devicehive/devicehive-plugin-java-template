package com.devicehive.model;

public class UserAuthRequest {

    private String login;
    private String password;

    public UserAuthRequest(String login, String password) {
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
