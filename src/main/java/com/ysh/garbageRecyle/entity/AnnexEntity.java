package com.ysh.garbageRecyle.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * annex
 * @author 
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AnnexEntity implements Serializable {
    /**
     * 附件编号
     */
    private Integer annexId;

    /**
     * 附件名称
     */
    private String annexName;

    /**
     * 附件链接
     */
    private String annexLink;

    private static final long serialVersionUID = 1L;

    public Integer getAnnexId() {
        return annexId;
    }

    public void setAnnexId(Integer annexId) {
        this.annexId = annexId;
    }

    public String getAnnexName() {
        return annexName;
    }

    public void setAnnexName(String annexName) {
        this.annexName = annexName;
    }

    public String getAnnexLink() {
        return annexLink;
    }

    public void setAnnexLink(String annexLink) {
        this.annexLink = annexLink;
    }



}