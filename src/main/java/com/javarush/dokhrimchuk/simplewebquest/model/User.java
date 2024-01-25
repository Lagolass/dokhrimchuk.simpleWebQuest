package com.javarush.dokhrimchuk.simplewebquest.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {
    private Integer id = 0;
    private String name;
    private String email;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
