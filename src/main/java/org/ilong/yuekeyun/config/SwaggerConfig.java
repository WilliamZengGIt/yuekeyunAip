package org.ilong.yuekeyun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * TOOD
 *
 * @author long
 * @date 2020-11-29 22:21
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.ilong.yuekeyun.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder()
                .title("悦课云api文档")
                .description("悦课云后端接口文档。技术选择：Spring boot+redis+rabbitMq+FastDFS")
                .version("1.0")
                .contact(new Contact("","","xianlong1422@126.com"))
                .license("The Apache License")
                .licenseUrl("http://www.baidu.com")
                .build());

    }
}
