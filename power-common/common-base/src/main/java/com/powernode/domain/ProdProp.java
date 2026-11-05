package com.powernode.domain;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProdProp {
    @Schema(description="属性id")
    private Long propId;
    @Schema(description="属性名称")
    private String propName;
    @Schema(description="ProdPropRule 1:销售属性(规格); 2:参数属性;")
    private Integer rule;
    @Schema(description="店铺id")
    private Long shopId;
}
