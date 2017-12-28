package com.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement// 启动注解事务管理，等同于xml配置方式的 <tx:annotation-driven />
@ComponentScan(basePackages = {"com.boot"})
@MapperScan("com.boot.*.mapper")
public class SbwebApplication {

    public static void main(String[] args) throws Exception {
        // 启动Spring Boot项目的唯一入口
        new SpringApplication(SbwebApplication.class).run(args);
    }

}
