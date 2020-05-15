package com.ysh.garbageRecyle.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;

/**
 * question
 * @author 
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class QuestionEntity implements Serializable {
    /**
     * 题目编号
     */
    private Integer questionId;

    /**
     * 题目内容（存json格式的选项）
     */
    private String questionContent;

    /**
     * 题目选项数
     */
    private Integer answerNumber;

    /**
     * 题目类型(单选或多选)
     */
    private Integer questionType;

    /**
     * 正确答案
     */
    private String rightAnswer;

    /**
     * 题目问题
     */
    private String questionTitle;

    /**
     * 题目状态（0表示未启用，1表示启用）
     */
    private Integer questionStatus;

    /**
     * 总答题次数
     */
    private Integer answersTime;

    /**
     * 答错次数
     */
    private Integer answersWrong;
    /*
    *垃圾类别
    */
    private Integer questionCategory;

    /*
    * 垃圾类别名称
    */
    @Transient
    private String categoryName;

    private static final long serialVersionUID = 1L;

}