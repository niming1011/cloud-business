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
 * 订单表
 * @TableName order
 */
@TableName(value ="order")
@Data
public class Order implements Serializable {
    /**
     * 订单ID
     */
    @TableId(type = IdType.AUTO)
    private Long orderId;

    /**
     * 订购用户ID
     */
    private String openId;

    /**
     * 订购流水号
     */
    private String orderNumber;

    /**
     * 总值
     */
    private BigDecimal totalMoney;

    /**
     * 实际总值
     */
    private BigDecimal actualTotal;

    /**
     * 订单备注
     */
    private String remarks;

    /**
     * 订单状态 1:待付款 2:待发货 3:待收货 4:待评价 5:成功 6:失败
     */
    private Integer status;

    /**
     * 配送类型
     */
    private String dvyType;

    /**
     * 配送方式ID
     */
    private Long dvyId;

    /**
     * 物流单号
     */
    private String dvyFlowId;

    /**
     * 订单运费
     */
    private BigDecimal freightAmount;

    /**
     * 用户订单地址Id
     */
    private Long addrOrderId;

    /**
     * 订单商品总数
     */
    private Integer productNums;

    /**
     * 订购时间
     */
    private Date createTime;

    /**
     * 订单更新时间
     */
    private Date updateTime;

    /**
     * 付款时间
     */
    private Date payTime;

    /**
     * 发货时间
     */
    private Date dvyTime;

    /**
     * 完成时间
     */
    private Date finallyTime;

    /**
     * 取消时间
     */
    private Date cancelTime;

    /**
     * 是否已经支付，1：已经支付过，0：，没有支付过
     */
    private Integer isPayed;

    /**
     * 用户订单删除状态，0：没有删除， 1：回收站， 2：永久删除
     */
    private Integer deleteStatus;

    /**
     * 0:默认,1:在处理,2:处理完成
     */
    private Integer refundSts;

    /**
     * 优惠总额
     */
    private BigDecimal reduceAmount;

    /**
     * 订单关闭原因 1-超时未支付 2-退款关闭 4-买家取消 15-已通过货到付款交易
     */
    private Integer closeType;

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
        Order other = (Order) that;
        return (this.getOrderId() == null ? other.getOrderId() == null : this.getOrderId().equals(other.getOrderId()))
            && (this.getOpenId() == null ? other.getOpenId() == null : this.getOpenId().equals(other.getOpenId()))
            && (this.getOrderNumber() == null ? other.getOrderNumber() == null : this.getOrderNumber().equals(other.getOrderNumber()))
            && (this.getTotalMoney() == null ? other.getTotalMoney() == null : this.getTotalMoney().equals(other.getTotalMoney()))
            && (this.getActualTotal() == null ? other.getActualTotal() == null : this.getActualTotal().equals(other.getActualTotal()))
            && (this.getRemarks() == null ? other.getRemarks() == null : this.getRemarks().equals(other.getRemarks()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getDvyType() == null ? other.getDvyType() == null : this.getDvyType().equals(other.getDvyType()))
            && (this.getDvyId() == null ? other.getDvyId() == null : this.getDvyId().equals(other.getDvyId()))
            && (this.getDvyFlowId() == null ? other.getDvyFlowId() == null : this.getDvyFlowId().equals(other.getDvyFlowId()))
            && (this.getFreightAmount() == null ? other.getFreightAmount() == null : this.getFreightAmount().equals(other.getFreightAmount()))
            && (this.getAddrOrderId() == null ? other.getAddrOrderId() == null : this.getAddrOrderId().equals(other.getAddrOrderId()))
            && (this.getProductNums() == null ? other.getProductNums() == null : this.getProductNums().equals(other.getProductNums()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getPayTime() == null ? other.getPayTime() == null : this.getPayTime().equals(other.getPayTime()))
            && (this.getDvyTime() == null ? other.getDvyTime() == null : this.getDvyTime().equals(other.getDvyTime()))
            && (this.getFinallyTime() == null ? other.getFinallyTime() == null : this.getFinallyTime().equals(other.getFinallyTime()))
            && (this.getCancelTime() == null ? other.getCancelTime() == null : this.getCancelTime().equals(other.getCancelTime()))
            && (this.getIsPayed() == null ? other.getIsPayed() == null : this.getIsPayed().equals(other.getIsPayed()))
            && (this.getDeleteStatus() == null ? other.getDeleteStatus() == null : this.getDeleteStatus().equals(other.getDeleteStatus()))
            && (this.getRefundSts() == null ? other.getRefundSts() == null : this.getRefundSts().equals(other.getRefundSts()))
            && (this.getReduceAmount() == null ? other.getReduceAmount() == null : this.getReduceAmount().equals(other.getReduceAmount()))
            && (this.getCloseType() == null ? other.getCloseType() == null : this.getCloseType().equals(other.getCloseType()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOrderId() == null) ? 0 : getOrderId().hashCode());
        result = prime * result + ((getOpenId() == null) ? 0 : getOpenId().hashCode());
        result = prime * result + ((getOrderNumber() == null) ? 0 : getOrderNumber().hashCode());
        result = prime * result + ((getTotalMoney() == null) ? 0 : getTotalMoney().hashCode());
        result = prime * result + ((getActualTotal() == null) ? 0 : getActualTotal().hashCode());
        result = prime * result + ((getRemarks() == null) ? 0 : getRemarks().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getDvyType() == null) ? 0 : getDvyType().hashCode());
        result = prime * result + ((getDvyId() == null) ? 0 : getDvyId().hashCode());
        result = prime * result + ((getDvyFlowId() == null) ? 0 : getDvyFlowId().hashCode());
        result = prime * result + ((getFreightAmount() == null) ? 0 : getFreightAmount().hashCode());
        result = prime * result + ((getAddrOrderId() == null) ? 0 : getAddrOrderId().hashCode());
        result = prime * result + ((getProductNums() == null) ? 0 : getProductNums().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getPayTime() == null) ? 0 : getPayTime().hashCode());
        result = prime * result + ((getDvyTime() == null) ? 0 : getDvyTime().hashCode());
        result = prime * result + ((getFinallyTime() == null) ? 0 : getFinallyTime().hashCode());
        result = prime * result + ((getCancelTime() == null) ? 0 : getCancelTime().hashCode());
        result = prime * result + ((getIsPayed() == null) ? 0 : getIsPayed().hashCode());
        result = prime * result + ((getDeleteStatus() == null) ? 0 : getDeleteStatus().hashCode());
        result = prime * result + ((getRefundSts() == null) ? 0 : getRefundSts().hashCode());
        result = prime * result + ((getReduceAmount() == null) ? 0 : getReduceAmount().hashCode());
        result = prime * result + ((getCloseType() == null) ? 0 : getCloseType().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderId=").append(orderId);
        sb.append(", openId=").append(openId);
        sb.append(", orderNumber=").append(orderNumber);
        sb.append(", totalMoney=").append(totalMoney);
        sb.append(", actualTotal=").append(actualTotal);
        sb.append(", remarks=").append(remarks);
        sb.append(", status=").append(status);
        sb.append(", dvyType=").append(dvyType);
        sb.append(", dvyId=").append(dvyId);
        sb.append(", dvyFlowId=").append(dvyFlowId);
        sb.append(", freightAmount=").append(freightAmount);
        sb.append(", addrOrderId=").append(addrOrderId);
        sb.append(", productNums=").append(productNums);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", payTime=").append(payTime);
        sb.append(", dvyTime=").append(dvyTime);
        sb.append(", finallyTime=").append(finallyTime);
        sb.append(", cancelTime=").append(cancelTime);
        sb.append(", isPayed=").append(isPayed);
        sb.append(", deleteStatus=").append(deleteStatus);
        sb.append(", refundSts=").append(refundSts);
        sb.append(", reduceAmount=").append(reduceAmount);
        sb.append(", closeType=").append(closeType);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}