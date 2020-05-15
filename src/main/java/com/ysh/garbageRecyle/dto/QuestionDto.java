package com.ysh.garbageRecyle.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QuestionDto {

    //题目id
    private int questionId;

    //题目
    private String questionTitle;

    //题目选项
    private ChooseDto chooseDto;

    //题目正确答案
    private String rightAnswer;

    //用户的选择
    private String usersAnser;

    //关联的垃圾类别
    private int gabageCategoryId;

    //垃圾类别名称
    private String garbageCategoryName;

    //被答题总次数
    private int answerTimes;

    //答错总次数
    private int answeWrongTimes;

    //题目状态
    private int status;

    //题目类型
    private int topicType;

}
