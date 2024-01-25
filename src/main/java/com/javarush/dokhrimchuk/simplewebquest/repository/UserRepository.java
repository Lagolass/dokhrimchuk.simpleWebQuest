package com.javarush.dokhrimchuk.simplewebquest.repository;

import com.javarush.dokhrimchuk.simplewebquest.DatabaseApplication;
import com.javarush.dokhrimchuk.simplewebquest.model.Answer;
import com.javarush.dokhrimchuk.simplewebquest.model.Question;
import com.javarush.dokhrimchuk.simplewebquest.model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserRepository implements Repository<User> {
    private final List<User> DB;

    public UserRepository() {
        DB = DatabaseApplication.importUsers();
    }
    public UserRepository(List<User> DB) {
        this.DB = DB;
    }

    public Optional<User> getByName(String name) {
        return DB.stream().filter(user -> user.getName().equals(name)).findFirst();
    }

    public Optional<User> getByEmail(String email) {
        return DB.stream().filter(user -> user.getEmail().equals(email)).findFirst();
    }

    @Override
    public Optional<User> getById(Integer id) {
        return DB.stream().filter(o -> o.getId().equals(id)).findFirst();
    }

    @Override
    public List<User> getAll() {
        return Collections.unmodifiableList(DB);
    }
}
