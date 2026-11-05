package com.powernode.domain;


import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ProdComm {
    @Schema(description="ID")
    private Long prodCommId;
    @Schema(description="商品ID")
    private Long prodId;
    @Schema(description="商品的名字")
    private String prodName;
    @Schema(description="订单项ID")
    private Long orderItemId;
    @Schema(description="评论用户ID")
    private String openId;
    @Schema(description="评论内容")
    private String content;
    @Schema(description="掌柜回复")
    private String replyContent;
    @Schema(description="记录时间")
    private Date createTime;
    @Schema(description="回复时间")
    private Date replyTime;
    @Schema(description="是否回复 0:未回复  1:已回复")
    private Integer replySts;
    @Schema(description="IP来源")
    private String postip;
    @Schema(description="得分，0-5分")
    private Integer score;
    @Schema(description="有用的计数")
    private Integer usefulCounts;
    @Schema(description="晒图的json字符串")
    private String pics;
    @Schema(description="是否匿名(1:是  0:否)")
    private Integer isAnonymous;
    @Schema(description="是否显示，1:为显示，0:待审核， -1：不通过审核，不显示。 如果需要审核评论，则是0,，否则1")
    private Integer status;
    @Schema(description="评价(0好评 1中评 2差评)")
    private Integer evaluate;
    @Schema(description="店铺")
    private Long shopId;
}
