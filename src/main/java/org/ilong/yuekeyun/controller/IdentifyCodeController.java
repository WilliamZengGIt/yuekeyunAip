package org.ilong.yuekeyun.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import org.ilong.yuekeyun.service.impl.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * 验证码生成器
 *
 * @author long
 * @date 2020-12-01 18:41
 */
@RestController
@RequestMapping("/tools/identiry")
@Api(tags = "验证码相关接口")
public class IdentifyCodeController {
    @Autowired
    private DefaultKaptcha producer;
    @Autowired
    private CaptchaService captchaService;

    @PostMapping("/captcha")
    public Map<String, Object> captcha(HttpServletResponse response)
            throws ServletException, IOException {
        // 生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        ByteArrayOutputStream outputStream = null;
        BufferedImage image = producer.createImage(text);
        outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        // 生成captcha的token
        Map<String, Object> map =captchaService.createToken(text);
        map.put("img", encoder.encode(outputStream.toByteArray()));
        return map;
    }
}
