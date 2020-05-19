package com.ysh.garbageRecyle.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestResultDto {

    private int questionNumber;

    private int answerWrongNumber;

    private int getScore;
}
