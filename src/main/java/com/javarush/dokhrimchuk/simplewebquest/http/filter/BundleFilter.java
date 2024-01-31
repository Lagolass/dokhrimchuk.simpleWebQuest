package com.javarush.dokhrimchuk.simplewebquest.http.filter;

import com.javarush.dokhrimchuk.simplewebquest.bundle.Text;
import java.io.IOException;
import java.util.Locale;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class BundleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        Locale locale = new Locale("uk", "ua");
        Text.setFor(httpRequest).setLocale(locale);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
