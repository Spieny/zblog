package com.ziahh.zblog;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@MapperScan("com.ziahh.zblog.mapper")
public class ZblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZblogApplication.class, args);
        log.info("-----------------");
        log.info(" ");
        log.info(" Z-Blog 启动成功！");
        log.info(" ");
        log.info("-----------------");
    }

}
