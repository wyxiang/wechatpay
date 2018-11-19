package com.demo.wechatpay.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * ServerConfig
 *
 * @author WuYuxiang
 * @date 2017-06-16
 */
@Configuration
@PropertySource("classpath:config.properties")
public class ServerConfig {

    //微信验证token
    @Value("${server.unionpay.cusid}")
    public String UNIONPAY_CUSID;

    //微信验证token
    @Value("${server.unionpay.appid}")
    public String UNIONPAY_APPID;

    //微信验证token
    @Value("${server.unionpay.key}")
    public String UNIONPAY_KEY;
}
