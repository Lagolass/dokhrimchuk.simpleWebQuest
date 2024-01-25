package com.javarush.dokhrimchuk.simplewebquest.service;

import com.google.gson.Gson;
import com.javarush.dokhrimchuk.simplewebquest.exception.AnswerNotFoundException;
import com.javarush.dokhrimchuk.simplewebquest.exception.QuestionNotFoundException;
import com.javarush.dokhrimchuk.simplewebquest.http.response.NextStageResponse;
import com.javarush.dokhrimchuk.simplewebquest.model.Answer;
import com.javarush.dokhrimchuk.simplewebquest.model.Question;
import com.javarush.dokhrimchuk.simplewebquest.repository.AnswerRepository;
import com.javarush.dokhrimchuk.simplewebquest.repository.QuestionRepository;
import java.util.Optional;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@Log4j2
public class QuestService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestService(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    public void startingStage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Optional<Question> question = questionRepository.getStartQuestion();

        if(question.isEmpty()) {
            String errorMessage = "starting question not found";
            log.error(errorMessage);
            throw new QuestionNotFoundException(errorMessage);
        }

        List<Answer> answers = answerRepository.getByQuestion(question.get().getId());

        if(answers.isEmpty()) {
            String errorMessage = String.format("answer for starting question (id: %s) not found", question.get().getId());
            log.error(errorMessage);
            throw new AnswerNotFoundException("answer for starting question not found");
        }

        request.setAttribute("question", question.get().getText());
        request.setAttribute("answers", answers);
        request.getRequestDispatcher("quest.jsp").forward(request, response);
    }

    public void getNextStage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        JSONObject jsonObject = readJsonRequest(request);
        NextStageResponse nextStageResponse = new NextStageResponse();
        String answerId = jsonObject.getString("answer");

        Optional<Question> question = questionRepository.getById(Integer.parseInt(answerId));

        if(question.isEmpty()) {
            String errorMessage = String.format("next question not found (for answer id: %s)", answerId);
            log.error(errorMessage);
            throw new QuestionNotFoundException("next question not found");
        }

        nextStageResponse.setQuestion(question.get());

        List<Answer> answers = answerRepository.getByQuestion(question.get().getId());
        if(!question.get().getIsFinal() && answers.isEmpty()) {
            String errorMessage = String.format("answer not found for question id: %s", question.get().getId());
            log.error(errorMessage);
            throw new AnswerNotFoundException("answer not found");
        }

        if(question.get().getIsFinal()) {
            countAttempts(request.getSession());
        } else {
            StringWriter htmlAnswers = new StringWriter();
            HttpServletResponseWrapper responseWrapper = getResponseWrapper(htmlAnswers, response);

            request.setAttribute("answers", answers);
            request.getRequestDispatcher("includes/listAnswers.jsp").include(request, responseWrapper);

            nextStageResponse.setHtmlAnswers(htmlAnswers.toString());
        }

        String jsonString = new Gson().toJson(nextStageResponse);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.print(jsonString);
        writer.close();
    }

    private void countAttempts(HttpSession session) {
        Integer countAttempts = (Integer) session.getAttribute("countAttempts");
        if(countAttempts == null) {
            countAttempts = 1;
        } else {
            countAttempts++;
        }

        session.setAttribute("countAttempts", countAttempts);
    }

    private JSONObject readJsonRequest(HttpServletRequest request) throws IOException {
        StringBuilder buffer = new StringBuilder();
        String line;

        BufferedReader reader = request.getReader();
        while ((line = reader.readLine()) != null)
            buffer.append(line);

        return new JSONObject(buffer.toString());
    }

    private HttpServletResponseWrapper getResponseWrapper(StringWriter stringWriter, HttpServletResponse response) {
        PrintWriter fragmentWriter = new PrintWriter(stringWriter);
        return new HttpServletResponseWrapper(response) {
            @Override
            public PrintWriter getWriter() {
                return fragmentWriter;
            }
        };
    }

}
