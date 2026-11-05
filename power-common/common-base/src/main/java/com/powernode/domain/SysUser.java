package com.powernode.domain;


import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SysUser {
    @Schema(description="")
    private Long userId;
    @Schema(description="用户名")
    private String username;
    @Schema(description="密码")
    private String password;
    @Schema(description="邮箱")
    private String email;
    @Schema(description="手机号")
    private String mobile;
    @Schema(description="状态  0：禁用   1：正常")
    private Integer status;
    @Schema(description="创建者ID")
    private Long createUserId;
    @Schema(description="创建时间")
    private Date createTime;
    @Schema(description="用户所在的商城Id")
    private Long shopId;
}
