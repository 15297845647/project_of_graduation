package com.ysh.garbageRecyle.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TestResultDto {
    //题目总数
    private int questionNumber;
    //答错题目数
    private int answerWrongNumber;
    //得分
    private int getScore;
}
