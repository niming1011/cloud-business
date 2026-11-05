package com.powernode.domain;


import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProdTagReference {
    @Schema(description="分组引用id")
    private Long referenceId;
    @Schema(description="店铺id")
    private Long shopId;
    @Schema(description="标签id")
    private Long tagId;
    @Schema(description="商品id")
    private Long prodId;
    @Schema(description="状态(1:正常,0:删除)")
    private Integer status;
    @Schema(description="创建时间")
    private Date createTime;
}
