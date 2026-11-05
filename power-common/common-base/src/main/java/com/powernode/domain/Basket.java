package com.powernode.domain;


import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Basket {
    @Schema(description="主键")
    private Long basketId;
    @Schema(description="店铺ID")
    private Long shopId;
    @Schema(description="产品ID")
    private Long prodId;
    @Schema(description="SkuID")
    private Long skuId;
    @Schema(description="用户ID")
    private String openId;
    @Schema(description="购物车产品个数")
    private Integer prodCount;
    @Schema(description="购物时间")
    private Date createTime;
}
