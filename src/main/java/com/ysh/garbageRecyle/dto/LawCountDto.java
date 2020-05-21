package com.ysh.garbageRecyle.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LawCountDto {

    //城市
    private String city;

    //此城市法律法规总条数
    private int sumLawsNumber;

    //此城市法律法规查看次数
    private int seeTimes;

    //法律法规总查看次数
    private int sumSeeTimes;
}
