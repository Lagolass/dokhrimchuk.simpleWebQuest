package com.javarush.dokhrimchuk.simplewebquest.repository;

import com.javarush.dokhrimchuk.simplewebquest.DatabaseApplication;
import com.javarush.dokhrimchuk.simplewebquest.exception.AnswerNotFoundException;
import com.javarush.dokhrimchuk.simplewebquest.model.Answer;

import com.javarush.dokhrimchuk.simplewebquest.model.Question;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AnswerRepository implements Repository<Answer> {
    private final List<Answer> DB;

    public AnswerRepository() {
        DB = DatabaseApplication.importAnswers();
    }
    public AnswerRepository(List<Answer> DB) {
        this.DB = DB;
    }

    public Optional<Answer> getById(Integer id) {
        return DB.stream().filter(answer -> { return answer.getId().equals(id); }).findFirst();
    }

    public List<Answer> getByQuestion(Integer questionId) {
        return DB.stream().filter(answer -> { return answer.getQuestionId().equals(questionId); })
                .collect(Collectors.toList());
    }

    public List<Answer> getAll() {
        return Collections.unmodifiableList(DB);
    }
}
