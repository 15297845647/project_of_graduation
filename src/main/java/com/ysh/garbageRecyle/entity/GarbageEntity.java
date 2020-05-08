package com.ysh.garbageRecyle.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Transient;

/**
 * garbage
 * @author 
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GarbageEntity implements Serializable {
    /**
     * 垃圾id
     */
    private Integer garbageId;

    /**
     * 垃圾名称
     */
    private String garbageName;

    /**
     * 垃圾类别代码
     */
    private Integer garbageCategoryCode;
    /**
     * 垃圾查询次数
     */
    private Integer queryTimes;
    /*
    *类别名称
    */
    @Transient
    private String categoryName;
    /**
     * 类别解释
     */
    @Transient
    private String categoryExplain;

    /**
     *
     * 放置要求
     */
    @Transient
    private String categoryRequire;
    /**
     * 常见物品
     */
    @Transient
    private String categoryCommon;

    private static final long serialVersionUID = 1L;



    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        GarbageEntity other = (GarbageEntity) that;
        return (this.getGarbageId() == null ? other.getGarbageId() == null : this.getGarbageId().equals(other.getGarbageId()))
            && (this.getGarbageName() == null ? other.getGarbageName() == null : this.getGarbageName().equals(other.getGarbageName()))
            && (this.getGarbageCategoryCode() == null ? other.getGarbageCategoryCode() == null : this.getGarbageCategoryCode().equals(other.getGarbageCategoryCode()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getGarbageId() == null) ? 0 : getGarbageId().hashCode());
        result = prime * result + ((getGarbageName() == null) ? 0 : getGarbageName().hashCode());
        result = prime * result + ((getGarbageCategoryCode() == null) ? 0 : getGarbageCategoryCode().hashCode());
        return result;
    }

}