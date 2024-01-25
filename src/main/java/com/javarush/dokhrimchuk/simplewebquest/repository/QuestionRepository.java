package com.javarush.dokhrimchuk.simplewebquest.repository;

import com.javarush.dokhrimchuk.simplewebquest.DatabaseApplication;
import com.javarush.dokhrimchuk.simplewebquest.model.Question;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class QuestionRepository implements Repository<Question> {
    private final List<Question> DB;

    public QuestionRepository() {
        DB = DatabaseApplication.importQuestions();
    }
    public QuestionRepository(List<Question> DB) {
        this.DB = DB;
    }

    public Optional<Question> getStartQuestion() {
        return DB.stream().filter(Question::getIsStart).findFirst();
    }

    public Optional<Question> getById(Integer id) {
        return DB.stream().filter(o -> o.getId().equals(id)).findFirst();
    }

    @Override
    public List<Question> getAll() {
        return Collections.unmodifiableList(DB);
    }

}
