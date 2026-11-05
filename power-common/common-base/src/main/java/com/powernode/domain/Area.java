package com.powernode.domain;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Area {
    @Schema(description="")
    private Long areaId;
    @Schema(description="")
    private String areaName;
    @Schema(description="")
    private Long parentId;
    @Schema(description="")
    private Integer level;
}
