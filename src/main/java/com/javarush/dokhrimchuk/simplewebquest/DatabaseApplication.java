package com.javarush.dokhrimchuk.simplewebquest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.javarush.dokhrimchuk.simplewebquest.model.Answer;
import com.javarush.dokhrimchuk.simplewebquest.model.Question;
import com.javarush.dokhrimchuk.simplewebquest.model.User;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class DatabaseApplication <T> {
    private final Gson gson = new Gson();
    private static final String jsonQuestion = "json/dataQuestion.json";
    private static final String jsonAnswer = "json/dataAnswer.json";
    private static final String jsonUser = "json/dataUser.json";

    public static List<Question> importQuestions() {
        DatabaseApplication<Question> data = new DatabaseApplication<>();
        return data.importData(jsonQuestion, Question.class);
    }

    public static List<Answer> importAnswers() {
        DatabaseApplication<Answer> data = new DatabaseApplication<>();
        return data.importData(jsonAnswer, Answer.class);
    }

    public static List<User> importUsers() {
        DatabaseApplication<User> data = new DatabaseApplication<>();

        return data.importData(jsonUser, User.class);
    }

    public List<T> importData(String pathToJsonFile, Class<T> valueType) {
        List<T> dataObjects = new ArrayList<>();

        try {
            String data = Files.readString(Path.of(getFileFromResource(pathToJsonFile).getPath()));

            Type type = TypeToken.getParameterized(List.class, valueType).getType();

            dataObjects = gson.fromJson(data, type);

        } catch (IOException ignored) {

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return dataObjects;
    }

    private File getFileFromResource(String fileName) throws URISyntaxException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return new File(resource.toURI());
        }
    }
}
