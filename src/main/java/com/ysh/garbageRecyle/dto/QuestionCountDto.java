package com.ysh.garbageRecyle.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuestionCountDto {

    //题目ID
    private int questionId;

    //题目回答总次数
    private int questionAnswerTimes;

    //题目答错总次数
    private int questionAnswerWrongTimes;

    //题目关联垃圾类别名称
    private String questionGarbageCategory;

    //所有题目回答总次数
    private int sumAnswerTimes;

    //所有题目答错总次数
    private int sumAnswerWrongTimes;
}
