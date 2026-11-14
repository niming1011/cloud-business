package com.powernode.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName prod_prop
 */
@TableName(value ="prod_prop")
@Data
public class ProdProp implements Serializable {
    /**
     * 属性id
     */
    @TableId(type = IdType.AUTO)
    private Long propId;

    /**
     * 属性名称
     */
    private String propName;

    /**
     * ProdPropRule 1:销售属性(规格); 2:参数属性;
     */
    private Integer rule;

    /**
     * 店铺id
     */
    private Long shopId;

    @TableField(exist = false)
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
        ProdProp other = (ProdProp) that;
        return (this.getPropId() == null ? other.getPropId() == null : this.getPropId().equals(other.getPropId()))
            && (this.getPropName() == null ? other.getPropName() == null : this.getPropName().equals(other.getPropName()))
            && (this.getRule() == null ? other.getRule() == null : this.getRule().equals(other.getRule()))
            && (this.getShopId() == null ? other.getShopId() == null : this.getShopId().equals(other.getShopId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPropId() == null) ? 0 : getPropId().hashCode());
        result = prime * result + ((getPropName() == null) ? 0 : getPropName().hashCode());
        result = prime * result + ((getRule() == null) ? 0 : getRule().hashCode());
        result = prime * result + ((getShopId() == null) ? 0 : getShopId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", propId=").append(propId);
        sb.append(", propName=").append(propName);
        sb.append(", rule=").append(rule);
        sb.append(", shopId=").append(shopId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}