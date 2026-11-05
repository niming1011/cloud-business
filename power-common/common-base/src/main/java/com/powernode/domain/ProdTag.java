package com.powernode.domain;


import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProdTag {
    @Schema(description="分组标签id")
    private Long id;
    @Schema(description="分组标题")
    private String title;
    @Schema(description="状态(1为正常,0为删除)")
    private Integer status;
    @Schema(description="列表样式(0:一列一个,1:一列两个,2:一列三个)")
    private Integer style;
    @Schema(description="排序")
    private Integer seq;
    @Schema(description="创建时间")
    private Date createTime;
    @Schema(description="修改时间")
    private Date updateTime;
}
