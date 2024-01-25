package com.javarush.dokhrimchuk.simplewebquest.http.filter;

import com.javarush.dokhrimchuk.simplewebquest.service.UserService;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/quest")
public class QuestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        if(!UserService.isLoggedIn(httpRequest)) {
            httpResponse.sendRedirect("./login");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
