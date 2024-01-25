package com.javarush.dokhrimchuk.simplewebquest.service;

import com.javarush.dokhrimchuk.simplewebquest.DatabaseApplication;
import com.javarush.dokhrimchuk.simplewebquest.exception.AnswerNotFoundException;
import com.javarush.dokhrimchuk.simplewebquest.exception.QuestionNotFoundException;
import com.javarush.dokhrimchuk.simplewebquest.model.Answer;
import com.javarush.dokhrimchuk.simplewebquest.model.Question;
import com.javarush.dokhrimchuk.simplewebquest.repository.AnswerRepository;
import com.javarush.dokhrimchuk.simplewebquest.repository.QuestionRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class QuestServiceTest {
    HttpServletRequest mockRequest;
    HttpServletResponse mockResponse;
    DatabaseApplication<Question> dbQuestion;
    DatabaseApplication<Answer> dbAnswer;

    @BeforeEach
    public void init() {
        dbQuestion = new DatabaseApplication<>();
        dbAnswer = new DatabaseApplication<>();
        mockRequest = Mockito.mock(HttpServletRequest.class);
        mockResponse = Mockito.mock(HttpServletResponse.class);
    }

    @Test
    public void startingStageShouldThrowQuestionNotFoundException() {
        List<Question> questions = dbQuestion.importData("json/testDataQuestionEmpty.json", Question.class);
        List<Answer> answers = dbAnswer.importData("json/testDataAnswer.json", Answer.class);
        QuestionRepository questionRepository = new QuestionRepository(questions);
        AnswerRepository answerRepository = new AnswerRepository(answers);
        QuestService service = new QuestService(questionRepository, answerRepository);

        Assertions.assertThrows(QuestionNotFoundException.class, () -> service.startingStage(mockRequest, mockResponse));
    }

    @Test
    public void startingStageShouldThrowAnswerNotFoundException() {
        List<Question> questions = dbQuestion.importData("json/testDataQuestion.json", Question.class);
        List<Answer> answers = dbAnswer.importData("json/testDataAnswerEmpty.json", Answer.class);
        QuestionRepository questionRepository = new QuestionRepository(questions);
        AnswerRepository answerRepository = new AnswerRepository(answers);
        QuestService service = new QuestService(questionRepository, answerRepository);

        Assertions.assertThrows(AnswerNotFoundException.class, () -> service.startingStage(mockRequest, mockResponse));
    }

    @Test
    public void getNextStageShouldThrowQuestionNotFoundException() {
        List<Question> questions = dbQuestion.importData("json/testDataQuestion.json", Question.class);
        List<Answer> answers = dbAnswer.importData("json/testDataAnswer.json", Answer.class);
        QuestionRepository questionRepository = new QuestionRepository(questions);
        AnswerRepository answerRepository = new AnswerRepository(answers);

        QuestService service = new QuestService(questionRepository, answerRepository) {

            @Override
            protected JSONObject readJsonRequest(HttpServletRequest request) {
                return new JSONObject("{answer: \"0\"}");
            }
        };

        Assertions.assertThrows(QuestionNotFoundException.class, () -> service.getNextStage(mockRequest, mockResponse));
    }

    @Test
    public void getNextStageShouldThrowAnswerNotFoundException() {
        List<Question> questions = dbQuestion.importData("json/testDataQuestion.json", Question.class);
        List<Answer> answers = dbAnswer.importData("json/testDataAnswer.json", Answer.class);
        QuestionRepository questionRepository = new QuestionRepository(questions);
        AnswerRepository answerRepository = new AnswerRepository(answers);
        QuestService service = new QuestService(questionRepository, answerRepository) {

            @Override
            protected JSONObject readJsonRequest(HttpServletRequest request) {
                return new JSONObject("{answer: \"3\"}");
            }
        };

        Assertions.assertThrows(AnswerNotFoundException.class, () -> service.getNextStage(mockRequest, mockResponse));
    }

}
