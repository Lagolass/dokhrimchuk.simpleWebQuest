package com.javarush.dokhrimchuk.simplewebquest.repository;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    public Optional<T> getById(Integer id);

    public List<T> getAll();
}
