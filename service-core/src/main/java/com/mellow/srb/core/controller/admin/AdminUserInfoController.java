package com.mellow.srb.core.controller.admin;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mellow.common.result.R;
import com.mellow.srb.core.pojo.entity.UserInfo;
import com.mellow.srb.core.pojo.query.UserInfoQuery;
import com.mellow.srb.core.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 用户基本信息 前端控制器
 * </p>
 *
 * @author zhengxinyu
 * @since 2021-08-28
 */
@Api(tags = "会员管理")
@RestController
@RequestMapping("/admin/core/userInfo")
@Slf4j
@CrossOrigin
public class AdminUserInfoController {

    @Resource
    private UserInfoService userInfoService;


    @ApiOperation("获取会员分页列表")
    @GetMapping("/listPage/{page}/{limit}")
    public R listPage(
            @ApiParam(value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(value = "查询对象", required = false)
                    UserInfoQuery userInfoQuery) {

        Page<UserInfo> pageParam = new Page<>(page, limit);
        IPage<UserInfo> pageModel = userInfoService.listPage(pageParam, userInfoQuery);
        return R.ok().data("pageModel", pageModel);
    }

    @ApiOperation("解锁和锁定")
    @PutMapping("/lock/{id}/{status}")
    public R lock(
            @ApiParam(value = "用户id", required = true)
            @PathVariable("id") Long id,
            @ApiParam(value = "状态（0：锁定 1：正常）", required = true)
            @PathVariable("status") Integer status) {
        userInfoService.lock(id, status);
        return R.ok().message(status == 1 ? "解锁成功" : "锁定成功");
    }
}

