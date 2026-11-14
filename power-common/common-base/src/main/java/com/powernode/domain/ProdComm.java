package com.powernode.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品评论
 * @TableName prod_comm
 */
@TableName(value ="prod_comm")
@Data
public class ProdComm implements Serializable {
    /**
     * ID
     */
    @TableId
    private Long prodCommId;

    /**
     * 商品ID
     */
    private Long prodId;

    /**
     * 商品的名字
     */
    private String prodName;

    /**
     * 订单项ID
     */
    private Long orderItemId;

    /**
     * 评论用户ID
     */
    private String openId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 掌柜回复
     */
    private String replyContent;

    /**
     * 记录时间
     */
    private Date createTime;

    /**
     * 回复时间
     */
    private Date replyTime;

    /**
     * 是否回复 0:未回复  1:已回复
     */
    private Integer replySts;

    /**
     * IP来源
     */
    private String postip;

    /**
     * 得分，0-5分
     */
    private Integer score;

    /**
     * 有用的计数
     */
    private Integer usefulCounts;

    /**
     * 晒图的json字符串
     */
    private String pics;

    /**
     * 是否匿名(1:是  0:否)
     */
    private Integer isAnonymous;

    /**
     * 是否显示，1:为显示，0:待审核， -1：不通过审核，不显示。 如果需要审核评论，则是0,，否则1
     */
    private Integer status;

    /**
     * 评价(0好评 1中评 2差评)
     */
    private Integer evaluate;

    /**
     * 店铺
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
        ProdComm other = (ProdComm) that;
        return (this.getProdCommId() == null ? other.getProdCommId() == null : this.getProdCommId().equals(other.getProdCommId()))
            && (this.getProdId() == null ? other.getProdId() == null : this.getProdId().equals(other.getProdId()))
            && (this.getProdName() == null ? other.getProdName() == null : this.getProdName().equals(other.getProdName()))
            && (this.getOrderItemId() == null ? other.getOrderItemId() == null : this.getOrderItemId().equals(other.getOrderItemId()))
            && (this.getOpenId() == null ? other.getOpenId() == null : this.getOpenId().equals(other.getOpenId()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getReplyContent() == null ? other.getReplyContent() == null : this.getReplyContent().equals(other.getReplyContent()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getReplyTime() == null ? other.getReplyTime() == null : this.getReplyTime().equals(other.getReplyTime()))
            && (this.getReplySts() == null ? other.getReplySts() == null : this.getReplySts().equals(other.getReplySts()))
            && (this.getPostip() == null ? other.getPostip() == null : this.getPostip().equals(other.getPostip()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getUsefulCounts() == null ? other.getUsefulCounts() == null : this.getUsefulCounts().equals(other.getUsefulCounts()))
            && (this.getPics() == null ? other.getPics() == null : this.getPics().equals(other.getPics()))
            && (this.getIsAnonymous() == null ? other.getIsAnonymous() == null : this.getIsAnonymous().equals(other.getIsAnonymous()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getEvaluate() == null ? other.getEvaluate() == null : this.getEvaluate().equals(other.getEvaluate()))
            && (this.getShopId() == null ? other.getShopId() == null : this.getShopId().equals(other.getShopId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getProdCommId() == null) ? 0 : getProdCommId().hashCode());
        result = prime * result + ((getProdId() == null) ? 0 : getProdId().hashCode());
        result = prime * result + ((getProdName() == null) ? 0 : getProdName().hashCode());
        result = prime * result + ((getOrderItemId() == null) ? 0 : getOrderItemId().hashCode());
        result = prime * result + ((getOpenId() == null) ? 0 : getOpenId().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getReplyContent() == null) ? 0 : getReplyContent().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getReplyTime() == null) ? 0 : getReplyTime().hashCode());
        result = prime * result + ((getReplySts() == null) ? 0 : getReplySts().hashCode());
        result = prime * result + ((getPostip() == null) ? 0 : getPostip().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getUsefulCounts() == null) ? 0 : getUsefulCounts().hashCode());
        result = prime * result + ((getPics() == null) ? 0 : getPics().hashCode());
        result = prime * result + ((getIsAnonymous() == null) ? 0 : getIsAnonymous().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getEvaluate() == null) ? 0 : getEvaluate().hashCode());
        result = prime * result + ((getShopId() == null) ? 0 : getShopId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", prodCommId=").append(prodCommId);
        sb.append(", prodId=").append(prodId);
        sb.append(", prodName=").append(prodName);
        sb.append(", orderItemId=").append(orderItemId);
        sb.append(", openId=").append(openId);
        sb.append(", content=").append(content);
        sb.append(", replyContent=").append(replyContent);
        sb.append(", createTime=").append(createTime);
        sb.append(", replyTime=").append(replyTime);
        sb.append(", replySts=").append(replySts);
        sb.append(", postip=").append(postip);
        sb.append(", score=").append(score);
        sb.append(", usefulCounts=").append(usefulCounts);
        sb.append(", pics=").append(pics);
        sb.append(", isAnonymous=").append(isAnonymous);
        sb.append(", status=").append(status);
        sb.append(", evaluate=").append(evaluate);
        sb.append(", shopId=").append(shopId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}