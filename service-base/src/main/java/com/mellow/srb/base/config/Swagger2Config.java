package com.mellow.srb.base.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket adminConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminApiInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/admin/.*")))
                .build();
    }

    @Bean
    public ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("尚融宝admin端积分等级列表")
                .description("本文档描述了尚融宝后台管理系统接口")
                .version("admin-1.0.0")
                .contact(new Contact("admin-zhengxinyu", "https://www.mellow.cn", "mellowzz@163.com"))
                .build();
    }

    @Bean
    public Docket apiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .paths(Predicates.and(PathSelectors.regex("/api/.*")))
                .build();
    }

    @Bean
    public ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("尚融宝web端积分等级列表")
                .description("本文档描述了尚融宝前台管理系统接口")
                .version("web-1.0.0")
                .contact(new Contact("web-zhengxinyu", "https://www.mellow.cn", "mellowzz@163.com"))
                .build();
    }
}