package com.powernode.result;

import com.powernode.constant.BusinessEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "项目统一响应结果对象")
public class Result implements Serializable {
    @Schema(description = "状态码")
    private Integer code;
    @Schema(description = "消息")
    private String msg;
    @Schema(description = "数据")
    private Object data;
    public static Result success(Object data){
        return Result.builder().code(200).msg("成功").data(data).build();
    }
    public static Result fail(Integer code,String msg){
        return Result.builder().code(code).msg(msg).build();
    }
    public static Result fail(BusinessEnum businessEnum){
        return  Result.builder().code(businessEnum.getCode()).msg(businessEnum.getDesc()).build();
    }

    public static Result handle(boolean save) {
        return save?Result.success(null):Result.fail(BusinessEnum.OPERATION_FAIL);
    }
}
