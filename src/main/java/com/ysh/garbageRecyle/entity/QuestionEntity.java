package com.ysh.garbageRecyle.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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

    private static final long serialVersionUID = 1L;

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public Integer getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(Integer answerNumber) {
        this.answerNumber = answerNumber;
    }

    public Integer getQuestionType() {
        return questionType;
    }

    public void setQuestionType(Integer questionType) {
        this.questionType = questionType;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public Integer getQuestionStatus() {
        return questionStatus;
    }

    public void setQuestionStatus(Integer questionStatus) {
        this.questionStatus = questionStatus;
    }

    public Integer getAnswersTime() {
        return answersTime;
    }

    public void setAnswersTime(Integer answersTime) {
        this.answersTime = answersTime;
    }

    public Integer getAnswersWrong() {
        return answersWrong;
    }

    public void setAnswersWrong(Integer answersWrong) {
        this.answersWrong = answersWrong;
    }

}