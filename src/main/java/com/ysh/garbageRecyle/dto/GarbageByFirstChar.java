package com.ysh.garbageRecyle.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;


@Getter
@Setter
@ToString
public class GarbageByFirstChar {
    private String firstChar;
    private List<GarbageFirstCharDto> garbageFirstCharDtoList;
}
