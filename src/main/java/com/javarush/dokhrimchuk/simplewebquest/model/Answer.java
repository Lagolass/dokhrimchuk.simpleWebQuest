package com.javarush.dokhrimchuk.simplewebquest.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Answer {
    protected Integer id = 0;
    protected Integer questionId = 0;
    protected Integer nextQuestionId = 0;
    protected String text;
}
