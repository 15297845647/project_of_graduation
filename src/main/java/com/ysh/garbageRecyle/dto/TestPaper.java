package com.ysh.garbageRecyle.dto;

import com.ysh.garbageRecyle.entity.QuestionEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
public class TestPaper implements Serializable {

    //试卷编号
    private String testPaperId;

    //题目list
    private List<QuestionDto> questionList;

    //试卷限定时间
    private String limitTime;

    //题目数量
    private int questionNumber;

    //总分
    private int sumScore;

    //用户得分
    private int useScore;

}
