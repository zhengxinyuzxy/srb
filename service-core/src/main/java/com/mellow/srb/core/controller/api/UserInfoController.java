package com.mellow.srb.core.controller.api;


import com.mellow.common.exception.Assert;
import com.mellow.common.result.R;
import com.mellow.common.result.ResponseEnum;
import com.mellow.common.util.RegexValidateUtils;
import com.mellow.srb.base.util.JwtUtils;
import com.mellow.srb.core.pojo.vo.LoginVO;
import com.mellow.srb.core.pojo.vo.RegisterVO;
import com.mellow.srb.core.pojo.vo.UserInfoVO;
import com.mellow.srb.core.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户基本信息 前端控制器
 * </p>
 *
 * @author zhengxinyu
 * @since 2021-08-28
 */
@Api(tags = "会员接口")
@RestController
@RequestMapping("/api/core/userInfo")
@Slf4j
@CrossOrigin
public class UserInfoController {
    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private UserInfoService userInfoService;

    @ApiOperation("会员注册")
    @PostMapping("/register")
    public R register(
            @ApiParam
            @RequestBody RegisterVO registerVO) {

        String mobile = registerVO.getMobile();
        String code = registerVO.getCode();
        String password = registerVO.getPassword();
        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        Assert.isTrue(RegexValidateUtils.checkCellphone(mobile),ResponseEnum.MOBILE_ERROR);
        Assert.notEmpty(code,ResponseEnum.CODE_NULL_ERROR);
        Assert.notEmpty(password, ResponseEnum.PASSWORD_NULL_ERROR);

        // 校验验证码
        String codeGen = (String) redisTemplate.opsForValue().get("srb:sms:code" + mobile);
        Assert.equals(code,codeGen, ResponseEnum.CODE_ERROR);

        // 注册
        userInfoService.register(registerVO);

        return R.ok().message("注册成功");
    }

    @ApiOperation("会员登录")
    @PostMapping("/login")
    public R login(@RequestBody LoginVO loginVO, HttpServletRequest httpServletRequest) {
        String mobile = loginVO.getMobile();
        String password = loginVO.getPassword();
        // 校验用户名和密码不能为空
        Assert.notEmpty(mobile, ResponseEnum.MOBILE_NULL_ERROR);
        Assert.notEmpty(password, ResponseEnum.PASSWORD_NULL_ERROR);

        String ip = httpServletRequest.getRemoteAddr();
        UserInfoVO userInfoVO = userInfoService.login(loginVO, ip);
        return R.ok().data("userInfo", userInfoVO);

    }

    @ApiOperation("校验令牌")
    @GetMapping("/checkToken")
    public R checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        boolean result = JwtUtils.checkToken(token);
        if (result) {
            return R.ok();
        }else {
            return R.setResult(ResponseEnum.LOGIN_AUTH_ERROR);
        }
    }

    @ApiOperation("校验手机号是否注册")
    @GetMapping("/checkMobile/{mobile}")
    public R checkMobile(
            @PathVariable("mobile") String mobile) {
        boolean result = userInfoService.checkMobile(mobile);
        return R.ok().data("isExist", result);
    }

}

