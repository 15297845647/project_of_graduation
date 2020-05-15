package com.ysh.garbageRecyle.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * wrong_question
 * @author 
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class WrongQuestionEntity implements Serializable {
    /**
     * 错题编号
     */
    private Integer wqId;

    /**
     * 题目编号
     */
    private Integer questionId;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 用户选择的答案
     */
    private String userAnswer;

    private static final long serialVersionUID = 1L;

    public Integer getWqId() {
        return wqId;
    }

    public void setWqId(Integer wqId) {
        this.wqId = wqId;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

}