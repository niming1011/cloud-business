package com.powernode.domain;


import java.math.BigDecimal;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Order {
    @Schema(description="订单ID")
    private Long orderId;
    @Schema(description="订购用户ID")
    private String openId;
    @Schema(description="订购流水号")
    private String orderNumber;
    @Schema(description="总值")
    private BigDecimal totalMoney;
    @Schema(description="实际总值")
    private BigDecimal actualTotal;
    @Schema(description="订单备注")
    private String remarks;
    @Schema(description="订单状态 1:待付款 2:待发货 3:待收货 4:待评价 5:成功 6:失败")
    private Integer status;
    @Schema(description="配送类型")
    private String dvyType;
    @Schema(description="配送方式ID")
    private Long dvyId;
    @Schema(description="物流单号")
    private String dvyFlowId;
    @Schema(description="订单运费")
    private BigDecimal freightAmount;
    @Schema(description="用户订单地址Id")
    private Long addrOrderId;
    @Schema(description="订单商品总数")
    private Integer productNums;
    @Schema(description="订购时间")
    private Date createTime;
    @Schema(description="订单更新时间")
    private Date updateTime;
    @Schema(description="付款时间")
    private Date payTime;
    @Schema(description="发货时间")
    private Date dvyTime;
    @Schema(description="完成时间")
    private Date finallyTime;
    @Schema(description="取消时间")
    private Date cancelTime;
    @Schema(description="是否已经支付，1：已经支付过，0：，没有支付过")
    private Integer isPayed;
    @Schema(description="用户订单删除状态，0：没有删除， 1：回收站， 2：永久删除")
    private Integer deleteStatus;
    @Schema(description="0:默认,1:在处理,2:处理完成")
    private Integer refundSts;
    @Schema(description="优惠总额")
    private BigDecimal reduceAmount;
    @Schema(description="订单关闭原因 1-超时未支付 2-退款关闭 4-买家取消 15-已通过货到付款交易")
    private Integer closeType;
}
