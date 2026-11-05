package com.powernode.domain;


import java.math.BigDecimal;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Sku {
    @Schema(description="单品ID")
    private Long skuId;
    @Schema(description="商品ID")
    private Long prodId;
    @Schema(description="销售属性组合字符串 格式是p1:v1;p2:v2")
    private String properties;
    @Schema(description="原价")
    private BigDecimal oriPrice;
    @Schema(description="价格")
    private BigDecimal price;
    @Schema(description="商品在付款减库存的状态下，该sku上未付款的订单数量")
    private Integer stocks;
    @Schema(description="实际库存")
    private Integer actualStocks;
    @Schema(description="修改时间")
    private Date updateTime;
    @Schema(description="记录时间")
    private Date createTime;
    @Schema(description="商品条形码")
    private String modelId;
    @Schema(description="sku图片")
    private String pic;
    @Schema(description="sku名称")
    private String skuName;
    @Schema(description="商品名称")
    private String prodName;
    @Schema(description="版本号")
    private Integer version;
    @Schema(description="商品重量")
    private Double weight;
    @Schema(description="商品体积")
    private Double volume;
    @Schema(description="0 禁用 1 启用")
    private Integer status;
}
