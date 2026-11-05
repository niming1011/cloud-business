package com.powernode.domain;


import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Notice {
    @Schema(description="公告id")
    private Long id;
    @Schema(description="店铺id")
    private Long shopId;
    @Schema(description="公告标题")
    private String title;
    @Schema(description="公告内容")
    private String content;
    @Schema(description="状态(1:公布 0:撤回)")
    private Integer status;
    @Schema(description="是否置顶")
    private Integer isTop;
    @Schema(description="发布时间")
    private Date createTime;
    @Schema(description="更新时间")
    private Date updateTime;
}
