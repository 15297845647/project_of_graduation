package com.ysh.garbageRecyle.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ChooseDto implements Serializable {
    //A选项
    private String a;
    //B选项
    private String b;
    //C选项
    private String c;
    //D选项
    private String d;
}
