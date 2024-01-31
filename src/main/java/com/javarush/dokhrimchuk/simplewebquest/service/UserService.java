package com.javarush.dokhrimchuk.simplewebquest.service;

import com.javarush.dokhrimchuk.simplewebquest.model.User;
import com.javarush.dokhrimchuk.simplewebquest.repository.UserRepository;

import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpSession;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class UserService {
    private final UserRepository userRepository;
    public static final String SESSION_ATTRIBUTE_NAME_AUTH = "authUser";

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean validation(HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Optional<User> user = userRepository.getByEmail(email);
        if (user.isPresent()) {
            if (user.get().getPassword().equals(hashPassword(password))) {
                request.getSession().setAttribute(SESSION_ATTRIBUTE_NAME_AUTH, user.get());
                return true;
            }

            log.warn(String.format("invalid password (email user: %s)", email));
        }

        if (user.isEmpty()) {
            log.warn(String.format("user not found (email: %s)", email));
        }

        request.setAttribute("error", "Invalid email or password");
        return false;
    }

    public static boolean isLoggedIn(HttpServletRequest request) {
        return (request.getSession().getAttribute(SESSION_ATTRIBUTE_NAME_AUTH) != null);
    }
    public static User getAuthUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute(SESSION_ATTRIBUTE_NAME_AUTH);
    }

    public static void logOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute(SESSION_ATTRIBUTE_NAME_AUTH) != null) {
            session.removeAttribute(SESSION_ATTRIBUTE_NAME_AUTH);
        }
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
