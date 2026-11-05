package com.powernode.domain;


import java.math.BigDecimal;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OrderItem {
    @Schema(description="订单项ID")
    private Long orderItemId;
    @Schema(description="店铺id")
    private Long shopId;
    @Schema(description="订单order_number")
    private String orderNumber;
    @Schema(description="产品ID")
    private Long prodId;
    @Schema(description="产品SkuID")
    private Long skuId;
    @Schema(description="购物车产品个数")
    private Integer prodCount;
    @Schema(description="产品名称")
    private String prodName;
    @Schema(description="sku名称")
    private String skuName;
    @Schema(description="产品主图片路径")
    private String pic;
    @Schema(description="产品价格")
    private BigDecimal price;
    @Schema(description="商品总金额")
    private BigDecimal productTotalAmount;
    @Schema(description="购物时间")
    private Date createTime;
    @Schema(description="评论状态： 0 未评价  1 已评价")
    private Integer commSts;
}
