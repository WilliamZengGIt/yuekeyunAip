package org.ilong.yuekeyun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan(basePackages = "org.ilong.yuekeyun.mapper")
public class YuekeyunApplication {

    public static void main(String[] args) {
        SpringApplication.run(YuekeyunApplication.class, args);
    }

}
