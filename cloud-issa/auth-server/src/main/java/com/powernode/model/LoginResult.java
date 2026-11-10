package com.powernode.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "登录统一结果对象")
public class LoginResult {
    @Schema(description = "令牌token")
    private String accessToken;
    @Schema(description = "有效时长")
    private Long expireIn;
}
