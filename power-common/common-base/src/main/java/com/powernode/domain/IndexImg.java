package com.powernode.domain;


import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class IndexImg {
    @Schema(description="主键")
    private Long imgId;
    @Schema(description="店铺ID")
    private Long shopId;
    @Schema(description="图片")
    private String imgUrl;
    @Schema(description="说明文字,描述")
    private String des;
    @Schema(description="标题")
    private String title;
    @Schema(description="链接")
    private String link;
    @Schema(description="状态")
    private Integer status;
    @Schema(description="顺序")
    private Integer seq;
    @Schema(description="上传时间")
    private Date createTime;
    @Schema(description="关联")
    private Long prodId;
    @Schema(description="关联商品类型，0已关联商品,-1未关联商品")
    private Integer type;
}
