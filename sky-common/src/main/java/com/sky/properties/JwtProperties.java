package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
// 表示当前类是一个配置属性类 封装文件的一些配置项
// 配置项是sky.jwt - 在application.yml文件中
@ConfigurationProperties(prefix = "sky.jwt")
@Data

// 把这些配置项封装为一个java对象，再被其他文件注入使用
public class JwtProperties {

    /**
     * 管理端员工生成jwt令牌相关配置
     */
    private String adminSecretKey;
    private long adminTtl;
    private String adminTokenName;

    /**
     * 用户端微信用户生成jwt令牌相关配置
     */
    private String userSecretKey;
    private long userTtl;
    private String userTokenName;

}
