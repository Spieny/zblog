package com.ziahh.zblog.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Title: AppProperties
 * @Author Ziahh
 * @Package com.ziahh.properties
 * @Date 2024/5/8 下午3:19
 * @description: 主配置文件
 */
@Component
@ConfigurationProperties(prefix = "spring.profiles")
@Data
public class AppProperties {

    private String active;
}
