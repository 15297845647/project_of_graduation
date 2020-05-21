package com.ysh.garbageRecyle.mapper;

import java.util.List;
import java.util.Map;

import com.ysh.garbageRecyle.dto.QuestionCategoryCountDto;
import com.ysh.garbageRecyle.dto.QuestionCountDto;
import com.ysh.garbageRecyle.dto.QuestionDto;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.ysh.garbageRecyle.entity.QuestionEntity;

@Mapper
@Repository
public interface QuestionMapper extends BaseMapper<QuestionEntity>  {
	
	//从数据库中随机取n条数据
    public List<QuestionEntity> getByRand(int dataNum);

    @Select("SELECT\n" +
            "\tq.answers_time AS questionAnswerTimes ,\n" +
            "\tq.answers_wrong AS questionAnswerWrongTimes,\n" +
            "\tq.question_id AS questionId,\n" +
            "\t( SELECT gc.category_name FROM garbage_category gc WHERE gc.category_id = q.question_category ) AS questionGarbageCategory\n" +
            "FROM\n" +
            "\tquestion q \n" +
            "GROUP BY\n" +
            "\tquestion_id")
    @Results({@Result(property="questionId",column="questionId"),@Result(property="questionAnswerTimes",column="questionAnswerTimes"),@Result(property="questionAnswerWrongTimes",column="questionAnswerWrongTimes"),@Result(property="questionGarbageCategory",column="questionGarbageCategory")})
    public List<QuestionCountDto> getCountQuestion();

    //统计总次数
    @Select("SELECT sum(q.answers_time) As sumAnswerTimes,sum(q.answers_wrong) AS sumAnswerWrongTimes FROM question q ")
    @Results({@Result(property="sumAnswerTimes",column="sumAnswerTimes"),@Result(property="sumAnswerWrongTimes",column="sumAnswerWrongTimes")})
    public QuestionCountDto getQuestionCountSum();

    //统计垃圾类别有多少题目

    @Select("SELECT\n" +
            "\tq.question_category AS categoryId,\n" +
            "\t(select gc.category_name from garbage_category gc where gc.category_id=q.question_category) AS categoryName,\n" +
            "\tsum(q.answers_time) AS categoryQuestionSumTimes,\n" +
            "\tsum(q.answers_wrong) AS categoryQuestionWrongTimes,\n" +
            "\t count(*) AS questionNumbers\n" +
            "FROM\n" +
            "\tquestion q\n" +
            "GROUP BY categoryId")
    @Results({@Result(column = "categoryId",property = "categoryId"),@Result(column = "categoryName",property = "categoryName"),@Result(column = "categoryQuestionSumTimes",property = "categoryQuestionSumTimes"),@Result(column = "categoryQuestionWrongTimes",property = "categoryQuestionWrongTimes"),@Result(column = "questionNumbers",property = "questionNumbers")})
    public List<QuestionCategoryCountDto> getCategoryQuestionCount();
}
