package com.powernode.domain;


import java.math.BigDecimal;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Prod {
    @Schema(description="产品ID")
    private Long prodId;
    @Schema(description="商品名称")
    private String prodName;
    @Schema(description="店铺id")
    private Long shopId;
    @Schema(description="原价")
    private BigDecimal oriPrice;
    @Schema(description="现价")
    private BigDecimal price;
    @Schema(description="简要描述,卖点等")
    private String brief;
    @Schema(description="详细描述")
    private String content;
    @Schema(description="商品主图")
    private String pic;
    @Schema(description="商品图片，以,分割")
    private String imgs;
    @Schema(description="默认是1，表示正常状态, -1表示删除, 0下架")
    private Integer status;
    @Schema(description="商品分类")
    private Long categoryId;
    @Schema(description="销量")
    private Integer soldNum;
    @Schema(description="总库存")
    private Integer totalStocks;
    @Schema(description="配送方式json见TransportModeVO")
    private Object deliveryMode;
    @Schema(description="运费模板id")
    private Long deliveryTemplateId;
    @Schema(description="录入时间")
    private Date createTime;
    @Schema(description="修改时间")
    private Date updateTime;
    @Schema(description="上架时间")
    private Date putawayTime;
    @Schema(description="版本 乐观锁")
    private Integer version;
}
