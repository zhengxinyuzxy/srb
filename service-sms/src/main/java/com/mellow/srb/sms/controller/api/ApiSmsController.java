package com.mellow.srb.sms.controller.api;

import com.mellow.common.exception.Assert;
import com.mellow.common.result.R;
import com.mellow.common.result.ResponseEnum;
import com.mellow.common.util.RandomUtils;
import com.mellow.common.util.RegexValidateUtils;
import com.mellow.srb.sms.service.SmsService;
import com.mellow.srb.sms.util.SmsProperties;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Api(tags = "短信管理")
@CrossOrigin
@RestController
@RequestMapping("/api/sms")
@Slf4j
public class ApiSmsController {

    @Resource
    private SmsService smsService;

    @Resource
    private RedisTemplate redisTemplate;

    @ApiOperation("获取验证码")
    @GetMapping("/send/{mobile}")
    public R send(
            @ApiParam(value = "手机号码", required = true)
            @PathVariable("mobile") String mobile) {

        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        // 手机号验证
        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile), ResponseEnum.MOBILE_ERROR);

        String code = RandomUtils.getSixBitRandom();

        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        smsService.send(mobile, SmsProperties.TEMPLATE_CODE, map);

        redisTemplate.opsForValue().set("srb:sms:code" + mobile, code, 5, TimeUnit.MINUTES);

        return R.ok().message("短信发送成功");
    }
}
