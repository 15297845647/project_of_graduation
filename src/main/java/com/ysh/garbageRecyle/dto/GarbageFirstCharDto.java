package com.ysh.garbageRecyle.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GarbageFirstCharDto {
    //首字母
    private String firstChar;

    //垃圾ID
    private int garbageId;

    //垃圾名称
    private String garbageName;

    //垃圾类别代码
    private int garbageCategoryCode;
}
