package com.hoolai.baobao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = { "com.hoolai.baobao" })
//启用缓存
@EnableCaching
//启用异步
@EnableAsync
//启用自带定时任务
@EnableScheduling
@MapperScan(basePackages = { "com.hoolai.baobao.**.mapper" })
@EnableSwagger2
public class BaobaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaobaoApplication.class, args);
	}

}
