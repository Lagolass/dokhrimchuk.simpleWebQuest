package com.javarush.dokhrimchuk.simplewebquest.http.servlet;

import com.javarush.dokhrimchuk.simplewebquest.repository.UserRepository;
import com.javarush.dokhrimchuk.simplewebquest.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/login")
public class Login extends HttpServlet {
    private UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        userService = new UserService(new UserRepository());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("error", "");
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(userService.validation(req)) {
            resp.sendRedirect("./");
        } else {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
