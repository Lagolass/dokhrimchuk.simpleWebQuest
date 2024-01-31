package com.javarush.dokhrimchuk.simplewebquest.model;

import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter @Setter @ToString
public class Question {
    protected Integer id = 0;
    protected String text;
    protected Boolean isStart = false;
    protected Boolean isFinal = false;
}
