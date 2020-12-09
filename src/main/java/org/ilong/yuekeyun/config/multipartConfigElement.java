package org.ilong.yuekeyun.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * 上传资源配置
 *
 * @author long
 * @date 2020-12-06 20:33
 */
/*
@Configuration
public class multipartConfigElement {
    @Bean
    public MultipartConfigElement multipartConfigElement() {

        MultipartConfigFactory factory = new MultipartConfigFactory();
        //允许上传的文件最大值
        factory.setMaxFileSize(DataSize.parse("100MB")); //KB,MB
        //设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.parse("1000MB"));
        return factory.createMultipartConfig();}
}
*/
