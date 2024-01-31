package com.javarush.dokhrimchuk.simplewebquest.model;

import java.util.Objects;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User myObject = (User) obj;
        return Objects.equals(id, myObject.id)
                && Objects.equals(name, myObject.name)
                && Objects.equals(email, myObject.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email);
    }
}
