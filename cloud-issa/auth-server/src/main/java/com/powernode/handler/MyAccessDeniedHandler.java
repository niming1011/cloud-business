package com.powernode.handler;

import cn.hutool.json.JSONUtil;
import com.powernode.constant.BusinessEnum;
import com.powernode.result.Result;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        Result fail = Result.fail(BusinessEnum.ACCESS_DENY_FAIL);
        String jsonStr = JSONUtil.toJsonStr(fail);
        PrintWriter writer = response.getWriter();
        writer.write(jsonStr);
        writer.flush();
        writer.close();
    }
}
