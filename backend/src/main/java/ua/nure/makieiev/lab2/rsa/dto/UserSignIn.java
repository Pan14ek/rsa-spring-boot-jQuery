package ua.nure.makieiev.lab2.rsa.dto;

import javax.validation.constraints.NotNull;

public class UserSignIn {

    @NotNull
    private final String login;

    @NotNull
    private final String password;

    public UserSignIn(String login, String password) {
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