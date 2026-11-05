package com.powernode.domain;


import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SysLog {
    @Schema(description="")
    private Long id;
    @Schema(description="用户名")
    private Long userId;
    @Schema(description="用户操作")
    private String operation;
    @Schema(description="请求方法")
    private String method;
    @Schema(description="请求参数")
    private String params;
    @Schema(description="执行时长(毫秒)")
    private Long time;
    @Schema(description="IP地址")
    private String ip;
    @Schema(description="创建时间")
    private Date createDate;
}
