package com.demo.wechatpay.service;

import com.demo.wechatpay.common.Constant;
import com.demo.wechatpay.common.ServerConfig;
import com.demo.wechatpay.entity.pay.TestPayVO;
import com.demo.wechatpay.entity.pay.TestUnitorderVO;
import com.demo.wechatpay.util.BeanToMapUtils;
import com.demo.wechatpay.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * TestUnionPayService
 *
 * @author WuYuxiang
 * @date 2017-06-16
 */
@Service
public class TestUnionPayService {
    @Autowired
    ServerConfig serverConfig;


    //调用测试接口
    public TestUnitorderVO getUnitorder(TestPayVO testPayVO) {
        Map<String, String> params = new HashMap<String, String>();
        params = BeanToMapUtils.toMap(testPayVO);
        String str = HttpUtil.post(Constant.UNIONPAY_URL_TEST,params);
        System.out.println(str);
        return HttpUtil.postForEntity(Constant.UNIONPAY_URL_TEST, params, TestUnitorderVO.class);
    }
}
