package com.ysh.garbageRecyle.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * news
 * @author 
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NewsEntity implements Serializable {
    /**
     * 新闻编号
     */
    private Integer newsId;

    /**
     * 用户编号
     */
    private Integer userId;

    /**
     * 附件编号
     */
    private Integer annexId;

    /**
     * 新闻标题
     */
    private String newsTitle;

    /**
     * 新闻链接
     */
    private String newsLink;

    /**
     * 新闻来源
     */
    private String newsSource;

    /**
     * 图片url
     */
    private String newsImgUrl;

    /**
     * 新闻正文
     */
    private String newsContent;

    /**
     * 新闻html格式
     */
    private String newsContentHtml;

    /**
     * 新闻发布时间
     */
    private Date newsPubDate;

    /**
     * 是否为置顶新闻
     */
    private Integer isTop;

    /**
     * 城市
     */
    private String city;

    /**
     * 查看次数
     */
    private Integer seeTimes;

    /**
     *
     * 新闻代码
     */
    private String newsCode;
    private static final long serialVersionUID = 1L;




}