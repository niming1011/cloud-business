package com.powernode.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 商品
 * @TableName prod
 */
@TableName(value ="prod")
@Data
public class Prod implements Serializable {
    /**
     * 产品ID
     */
    @TableId(type = IdType.AUTO)
    private Long prodId;

    /**
     * 商品名称
     */
    private String prodName;

    /**
     * 店铺id
     */
    private Long shopId;

    /**
     * 原价
     */
    private BigDecimal oriPrice;

    /**
     * 现价
     */
    private BigDecimal price;

    /**
     * 简要描述,卖点等
     */
    private String brief;

    /**
     * 详细描述
     */
    private String content;

    /**
     * 商品主图
     */
    private String pic;

    /**
     * 商品图片，以,分割
     */
    private String imgs;

    /**
     * 默认是1，表示正常状态, -1表示删除, 0下架
     */
    private Integer status;

    /**
     * 商品分类
     */
    private Long categoryId;

    /**
     * 销量
     */
    private Integer soldNum;

    /**
     * 总库存
     */
    private Integer totalStocks;

    /**
     * 配送方式json见TransportModeVO
     */
    private Object deliveryMode;

    /**
     * 运费模板id
     */
    private Long deliveryTemplateId;

    /**
     * 录入时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 上架时间
     */
    private Date putawayTime;

    /**
     * 版本 乐观锁
     */
    private Integer version;

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
        Prod other = (Prod) that;
        return (this.getProdId() == null ? other.getProdId() == null : this.getProdId().equals(other.getProdId()))
            && (this.getProdName() == null ? other.getProdName() == null : this.getProdName().equals(other.getProdName()))
            && (this.getShopId() == null ? other.getShopId() == null : this.getShopId().equals(other.getShopId()))
            && (this.getOriPrice() == null ? other.getOriPrice() == null : this.getOriPrice().equals(other.getOriPrice()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getBrief() == null ? other.getBrief() == null : this.getBrief().equals(other.getBrief()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getPic() == null ? other.getPic() == null : this.getPic().equals(other.getPic()))
            && (this.getImgs() == null ? other.getImgs() == null : this.getImgs().equals(other.getImgs()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCategoryId() == null ? other.getCategoryId() == null : this.getCategoryId().equals(other.getCategoryId()))
            && (this.getSoldNum() == null ? other.getSoldNum() == null : this.getSoldNum().equals(other.getSoldNum()))
            && (this.getTotalStocks() == null ? other.getTotalStocks() == null : this.getTotalStocks().equals(other.getTotalStocks()))
            && (this.getDeliveryMode() == null ? other.getDeliveryMode() == null : this.getDeliveryMode().equals(other.getDeliveryMode()))
            && (this.getDeliveryTemplateId() == null ? other.getDeliveryTemplateId() == null : this.getDeliveryTemplateId().equals(other.getDeliveryTemplateId()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getPutawayTime() == null ? other.getPutawayTime() == null : this.getPutawayTime().equals(other.getPutawayTime()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProdId() == null) ? 0 : getProdId().hashCode());
        result = prime * result + ((getProdName() == null) ? 0 : getProdName().hashCode());
        result = prime * result + ((getShopId() == null) ? 0 : getShopId().hashCode());
        result = prime * result + ((getOriPrice() == null) ? 0 : getOriPrice().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getBrief() == null) ? 0 : getBrief().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getPic() == null) ? 0 : getPic().hashCode());
        result = prime * result + ((getImgs() == null) ? 0 : getImgs().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCategoryId() == null) ? 0 : getCategoryId().hashCode());
        result = prime * result + ((getSoldNum() == null) ? 0 : getSoldNum().hashCode());
        result = prime * result + ((getTotalStocks() == null) ? 0 : getTotalStocks().hashCode());
        result = prime * result + ((getDeliveryMode() == null) ? 0 : getDeliveryMode().hashCode());
        result = prime * result + ((getDeliveryTemplateId() == null) ? 0 : getDeliveryTemplateId().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getPutawayTime() == null) ? 0 : getPutawayTime().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", prodId=").append(prodId);
        sb.append(", prodName=").append(prodName);
        sb.append(", shopId=").append(shopId);
        sb.append(", oriPrice=").append(oriPrice);
        sb.append(", price=").append(price);
        sb.append(", brief=").append(brief);
        sb.append(", content=").append(content);
        sb.append(", pic=").append(pic);
        sb.append(", imgs=").append(imgs);
        sb.append(", status=").append(status);
        sb.append(", categoryId=").append(categoryId);
        sb.append(", soldNum=").append(soldNum);
        sb.append(", totalStocks=").append(totalStocks);
        sb.append(", deliveryMode=").append(deliveryMode);
        sb.append(", deliveryTemplateId=").append(deliveryTemplateId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", putawayTime=").append(putawayTime);
        sb.append(", version=").append(version);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}