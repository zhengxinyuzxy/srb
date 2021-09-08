package com.mellow.srb.sms.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import com.mellow.common.exception.Assert;
import com.mellow.common.exception.BusinessException;
import com.mellow.common.result.ResponseEnum;
import com.mellow.srb.sms.service.SmsService;
import com.mellow.srb.sms.util.SmsProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Override
    public void send(String mobile, String templateCode, Map<String, Object> param) {
        DefaultProfile profile = DefaultProfile.getProfile(SmsProperties.REGION_Id, SmsProperties.KEY_ID, SmsProperties.KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);

        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");

        request.putQueryParameter("RegionId", SmsProperties.REGION_Id);
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", SmsProperties.SIGN_NAME);
        request.putQueryParameter("TemplateCode", templateCode);

        Gson gson = new Gson();
        String paramJson = gson.toJson(param);
        request.putQueryParameter("TemplateParam", paramJson);

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());

            boolean success = response.getHttpResponse().isSuccess();
            Assert.isTrue(success, ResponseEnum.ALIYUN_RESPONSE_ERROR);

            String data = response.getData();
            HashMap<String, String> resultMap = gson.fromJson(data, HashMap.class);
            String code = resultMap.get("Code");
            String message = resultMap.get("Message");
            log.info("code: " + code + ", message: " + message);

            Assert.notEquals("isv.BUSINESS_LIMIT_CONTROL", code, ResponseEnum.ALIYUN_SMS_LIMIT_CONTROL_ERROR);
            Assert.equals("OK", code, ResponseEnum.ALIYUN_SMS_ERROR);

        } catch (ServerException e) {
            log.error("阿里云短信发送SDK调用失败：" + "ErrCode: " + e.getErrCode() + "ErrMsg: " + e.getErrMsg());
            throw new BusinessException(ResponseEnum.ALIYUN_SMS_ERROR, e);
        } catch (ClientException e) {
            log.error("阿里云短信发送SDK调用失败：" + "ErrCode: " + e.getErrCode() + "ErrMsg: " + e.getErrMsg());
            throw new BusinessException(ResponseEnum.ALIYUN_SMS_ERROR, e);
        }
    }
}
