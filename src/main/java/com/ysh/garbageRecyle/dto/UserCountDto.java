package com.ysh.garbageRecyle.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserCountDto {
    //角色ID
    private int roleId;
    //该角色数量
    private int roleCount;
    //角色名称
    private String roleName;
    //总用户数
    private int sumUser;
}
