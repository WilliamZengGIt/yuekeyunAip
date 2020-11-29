package org.ilong.yuekeyun.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

/**
 * Fastdfs 配置类
 *
 * @author long
 * @date 2020-10-12 22:38
 */
@Configuration
@Import(FdfsClientConfig.class)//@Import可以导入一个带有@configuration注解的类;
// 解决jmx重复注册bean的问题
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FastdfsConfig {
// 导入依赖组件
}
