package com.powernode.domain;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProdPropValue {
    @Schema(description="属性值ID")
    private Long valueId;
    @Schema(description="属性值名称")
    private String propValue;
    @Schema(description="属性ID")
    private Long propId;
}
