package com.ysh.garbageRecyle.entity;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * garbage_category
 * @author 
 */
@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class GarbageCategoryEntity implements Serializable {
    /**
     * 垃圾类别id
     */
    private Integer categoryId;

    /**
     * 垃圾类别code
     */
    private Integer categoryCode;

    /**
     * 垃圾类别名称
     */
    private String categoryName;

    /**
     * 垃圾类别解释
     */
    private String categoryExplain;

    /**
     * 注意事项
     */
    private String categoryRequire;

    /**
     * 常见物品
     */
    private String categoryCommon;
    /**
     * 垃圾查询次数
     */
    private Integer queryTimes;

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
        GarbageCategoryEntity other = (GarbageCategoryEntity) that;
        return (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getCategoryCode() == null ? other.getCategoryCode() == null : this.getCategoryCode().equals(other.getCategoryCode()))
            && (this.getCategoryName() == null ? other.getCategoryName() == null : this.getCategoryName().equals(other.getCategoryName()))
            && (this.getCategoryExplain() == null ? other.getCategoryExplain() == null : this.getCategoryExplain().equals(other.getCategoryExplain()))
            && (this.getCategoryRequire() == null ? other.getCategoryRequire() == null : this.getCategoryRequire().equals(other.getCategoryRequire()))
            && (this.getCategoryCommon() == null ? other.getCategoryCommon() == null : this.getCategoryCommon().equals(other.getCategoryCommon()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getCategoryCode() == null) ? 0 : getCategoryCode().hashCode());
        result = prime * result + ((getCategoryName() == null) ? 0 : getCategoryName().hashCode());
        result = prime * result + ((getCategoryExplain() == null) ? 0 : getCategoryExplain().hashCode());
        result = prime * result + ((getCategoryRequire() == null) ? 0 : getCategoryRequire().hashCode());
        result = prime * result + ((getCategoryCommon() == null) ? 0 : getCategoryCommon().hashCode());
        return result;
    }

}