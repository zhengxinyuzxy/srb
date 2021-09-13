package com.mellow.srb.core.controller.admin;


import com.mellow.common.result.R;
import com.mellow.srb.core.pojo.entity.UserLoginRecord;
import com.mellow.srb.core.service.UserLoginRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "会员登录日志接口")
@RestController
@RequestMapping("/admin/core/userLoginRecord")
@Slf4j
@CrossOrigin
public class AdminUserLoginRecordController {

    @Resource
    private UserLoginRecordService userLoginRecordService;

    @ApiOperation("获取用户登录日志")
    @GetMapping("/listTop50/{userId}")
    public R listTop50(
            @ApiParam(value = "用户id",required = true)
            @PathVariable("userId") Long id) {
        List<UserLoginRecord> userLoginRecords = userLoginRecordService.listTop50(id);
        return R.ok().data("userLoginRecords", userLoginRecords);
    }

}

