package com.ysh.garbageRecyle.dto;

import com.ysh.garbageRecyle.entity.UsersEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class RegisterDto implements Serializable {
    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 角色编号
     */

    private Integer roleId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 账号
     */
    private String accountNumber;

    /**
     * 密码
     */
    private String password;

    /**
     * 电话号码
     */
    private String phoneNumber;

    /**
     * 地址
     */
    private String address;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    private String code;

}
