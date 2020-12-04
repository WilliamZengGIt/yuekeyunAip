package org.ilong.yuekeyun.service.impl;

import org.ilong.yuekeyun.Enum.RedisKeys;
import org.ilong.yuekeyun.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * TOOD
 *
 * @author long
 * @date 2020-12-02 11:08
 */
@Service
public class CaptchaService {

    @Autowired
    RedisUtil redisUtil;

    public Map<String,Object> createToken(String captcha){
        //生成一个token
        String cToken = UUID.randomUUID().toString();
        //生成验证码对应的token  以token为key  验证码为value存在redis中
      /*  StringRedisTemplate template = StringRedisUtil.getStringRedisTemplate();
        ValueOperations<String, String> valueOperations = template.opsForValue();
        String key = String.format(RedisKeys.CLIENT_TOKEN, cToken);
        valueOperations.set(key, captcha);
        template.expire(key, timeout, TimeUnit.MINUTES);*/
        System.out.println("cToken:"+cToken);
        redisUtil.set(cToken, captcha,300);
        Map<String, Object> map = new HashMap<>();
        map.put("cToken", cToken);
        map.put("time", 300);
        return map;
    }
}
