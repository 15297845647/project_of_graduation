package com.ysh.garbageRecyle.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GarbageCountDto {
    //类别Id
    private int categoryId;
    //类别名称
    private String categoryName;
    //该类垃圾数量
    private int garbageNumbers;
    //该类垃圾的查询次数
    private int categoryQueryTime;
}
