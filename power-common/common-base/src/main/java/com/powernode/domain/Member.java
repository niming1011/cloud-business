package com.powernode.domain;


import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Member {
    @Schema(description="会员表的主键")
    private Integer id;
    @Schema(description="ID")
    private String openId;
    @Schema(description="用户昵称")
    private String nickName;
    @Schema(description="真实姓名")
    private String realName;
    @Schema(description="用户邮箱")
    private String userMail;
    @Schema(description="支付密码")
    private String payPassword;
    @Schema(description="手机号码")
    private String userMobile;
    @Schema(description="修改时间")
    private Date updateTime;
    @Schema(description="注册时间")
    private Date createTime;
    @Schema(description="注册IP")
    private String userRegip;
    @Schema(description="最后登录时间")
    private Date userLasttime;
    @Schema(description="最后登录IP")
    private String userLastip;
    @Schema(description="M(男) or F(女)")
    private String sex;
    @Schema(description="例如：2009-11-27")
    private String birthDate;
    @Schema(description="头像图片路径")
    private String pic;
    @Schema(description="状态 1 正常 0 无效")
    private Integer status;
    @Schema(description="用户积分")
    private Integer score;
}
