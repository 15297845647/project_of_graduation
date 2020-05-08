package com.ysh.garbageRecyle.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * garbage_law
 * @author 
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GarbageLawEntity implements Serializable {
    /**
     * 法律法规编号
     */
    private Integer lawId;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 附件编号
     */
    private Integer annexId;

    /**
     * 法律法规标题
     */
    private String lawTitle;

    /**
     * 法律法规内容文件名称
     */
    private String lawFileName;

    /**
     * 发布时间
     */
    private Date lawPubDate;

    /**
     * 城市
     */
    private String lawLocation;

    /**
     * 查看次数
     */
    private Integer seeTimes;

    /*
    *法律法规代码
    */
    private String lawCode;
    private static final long serialVersionUID = 1L;

}