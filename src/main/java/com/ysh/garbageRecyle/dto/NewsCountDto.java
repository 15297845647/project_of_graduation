package com.ysh.garbageRecyle.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NewsCountDto {

    //城市
    private String newsCity;

    //该城市新闻条数
    private int cityNewsNumber;

    //新闻总数
    private int sumNewsNumber;

    //该城市新闻查看次数
    private int seeTimes;

    //所有新闻查看总次数
    private int sumSeeTimes;


}
