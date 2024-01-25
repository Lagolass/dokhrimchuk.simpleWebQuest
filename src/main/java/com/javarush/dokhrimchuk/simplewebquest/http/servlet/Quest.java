package com.javarush.dokhrimchuk.simplewebquest.http.servlet;

import com.javarush.dokhrimchuk.simplewebquest.repository.AnswerRepository;
import com.javarush.dokhrimchuk.simplewebquest.repository.QuestionRepository;
import com.javarush.dokhrimchuk.simplewebquest.service.QuestService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet("/quest")
public class Quest extends HttpServlet {
    QuestService questService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        questService = new QuestService(new QuestionRepository(), new AnswerRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        questService.startingStage(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        questService.getNextStage(req, resp);
    }

}
