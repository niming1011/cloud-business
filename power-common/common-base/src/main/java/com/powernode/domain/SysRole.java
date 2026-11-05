package com.powernode.domain;


import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SysRole {
    @Schema(description="")
    private Long roleId;
    @Schema(description="角色名称")
    private String roleName;
    @Schema(description="备注")
    private String remark;
    @Schema(description="创建者ID")
    private Long createUserId;
    @Schema(description="创建时间")
    private Date createTime;
}
