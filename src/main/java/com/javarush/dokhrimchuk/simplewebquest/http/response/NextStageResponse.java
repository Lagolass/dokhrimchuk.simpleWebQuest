package com.javarush.dokhrimchuk.simplewebquest.http.response;

import com.javarush.dokhrimchuk.simplewebquest.model.Question;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class NextStageResponse {
    private Question question;
    private String htmlAnswers;
    private String error;
}
