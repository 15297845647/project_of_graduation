package com.ysh.garbageRecyle.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuestionCategoryCountDto {

    //垃圾类别ID
    private int categoryId;

    //该垃圾类别题目答错总次数
    private int categoryQuestionWrongTimes;

    //该垃圾类别题目答题总次数
    private int categoryQuestionSumTimes;

    //题目关联垃圾类别
    private String categoryName;

    //该垃圾类别关联的题目的数量
    private int questionNumbers;

}
