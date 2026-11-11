package com.powernode.constant;

public interface ResourceConstants {
    String[] RESOURCE_ALLOW_URLS = {
            // 1. OpenAPI 3 核心路径（必须放行，否则 UI 拿不到接口数据）
            "/v3/api-docs/**",          // 规范文档 JSON/YAML
            "/v3/api-docs.yaml",        // YAML 格式（可选）
            "/v3/api-docs/swagger-config", // UI 的配置文件（关键！漏了页面会转圈）

            // 2. Swagger UI 静态资源（必须放行，否则页面样式、JS 会 403）
            "/swagger-ui/**",
            "/swagger-ui.html",         // 入口页面（会被重定向到 index.html）
            "/webjars/**",              // 前端依赖的静态资源

            // 3. 其他需要放行的（你原有的）
            "/druid/**",
            "/actuator/**"
    };
}
