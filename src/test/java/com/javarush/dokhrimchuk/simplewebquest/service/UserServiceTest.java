package com.javarush.dokhrimchuk.simplewebquest.service;

import com.javarush.dokhrimchuk.simplewebquest.DatabaseApplication;
import com.javarush.dokhrimchuk.simplewebquest.model.User;
import com.javarush.dokhrimchuk.simplewebquest.repository.UserRepository;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    UserService service;
    HttpServletRequest mockRequest;
    HttpSession mockSession;

    @BeforeEach
    public void init() {
        DatabaseApplication<User> db = new DatabaseApplication<>();
        List<User> users = db.importData("json/testDataUser.json", User.class);
        UserRepository userRepository = new UserRepository(users);
        service = new UserService(userRepository);

        mockRequest = Mockito.mock(HttpServletRequest.class);
        mockSession = Mockito.mock(HttpSession.class);
    }
    @Test
    void validationLoginReturnTrue() {
        when(mockRequest.getParameter("email")).thenReturn("test@example.com");
        when(mockRequest.getParameter("password")).thenReturn("11111111");
        when(mockRequest.getSession()).thenReturn(Mockito.mock(HttpSession.class));

        Assertions.assertTrue(service.validation(mockRequest));
    }
    @Test
    void validationLoginReturnFalse() {
        when(mockRequest.getParameter("email")).thenReturn("test@not.found");
        when(mockRequest.getParameter("password")).thenReturn("wrongPassword");

        Assertions.assertFalse(service.validation(mockRequest));
    }

    @Test
    void isLoggedInReturnTrue() {
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockSession.getAttribute(UserService.SESSION_ATTRIBUTE_NAME_AUTH)).thenReturn(new User());

        Assertions.assertTrue(UserService.isLoggedIn(mockRequest));
    }
    @Test
    void isLoggedInReturnFalse() {
        when(mockRequest.getSession()).thenReturn(mockSession);

        Assertions.assertFalse(UserService.isLoggedIn(mockRequest));
    }

    @Test
    void getAuthUser() {
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockSession.getAttribute(UserService.SESSION_ATTRIBUTE_NAME_AUTH)).thenReturn(new User());

        Assertions.assertEquals(UserService.getAuthUser(mockRequest), new User());
    }

}