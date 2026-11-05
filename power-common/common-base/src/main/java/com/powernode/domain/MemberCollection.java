package com.powernode.domain;


import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MemberCollection {
    @Schema(description="收藏表")
    private Long id;
    @Schema(description="商品id")
    private Long prodId;
    @Schema(description="用户id")
    private String openId;
    @Schema(description="收藏时间")
    private Date createTime;
}
