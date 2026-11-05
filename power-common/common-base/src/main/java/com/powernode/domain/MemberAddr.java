package com.powernode.domain;


import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MemberAddr {
    @Schema(description="ID")
    private Long addrId;
    @Schema(description="用户ID")
    private String openId;
    @Schema(description="收货人")
    private String receiver;
    @Schema(description="省ID")
    private Long provinceId;
    @Schema(description="省")
    private String province;
    @Schema(description="城市")
    private String city;
    @Schema(description="城市ID")
    private Long cityId;
    @Schema(description="区")
    private String area;
    @Schema(description="区ID")
    private Long areaId;
    @Schema(description="邮编")
    private String postCode;
    @Schema(description="地址")
    private String addr;
    @Schema(description="手机")
    private String mobile;
    @Schema(description="状态,1正常，0无效")
    private Integer status;
    @Schema(description="是否默认地址 1是")
    private Integer commonAddr;
    @Schema(description="建立时间")
    private Date createTime;
    @Schema(description="更新时间")
    private Date updateTime;
}
