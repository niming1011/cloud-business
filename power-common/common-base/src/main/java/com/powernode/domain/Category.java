package com.powernode.domain;


import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Category {
    @Schema(description="类目ID")
    private Long categoryId;
    @Schema(description="父节点")
    private Long parentId;
    @Schema(description="产品类目名称")
    private String categoryName;
    @Schema(description="类目图标")
    private String icon;
    @Schema(description="类目的显示图片")
    private String pic;
    @Schema(description="排序")
    private Integer seq;
    @Schema(description="默认是1，表示正常状态,0为下线状态")
    private Integer status;
    @Schema(description="记录时间")
    private Date createTime;
    @Schema(description="更新时间")
    private Date updateTime;
}
