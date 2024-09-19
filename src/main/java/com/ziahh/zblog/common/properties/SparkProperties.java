package com.ziahh.zblog.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Title: SparkProperties
 * @Author Ziahh
 * @Package com.ziahh.properties
 * @Date 2024/5/8 下午8:11
 * @description: 星火大模型配置
 */
@Component
@ConfigurationProperties(prefix = "ziahh.spark")
@Data
public class SparkProperties {

    private String hostUrl;
    private String appid;
    private String apiSecret;
    private String apiKey;

}
