package com.mellow.srb.core.controller.admin;


import com.mellow.srb.core.pojo.entity.IntegralGrade;
import com.mellow.srb.core.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 积分等级 后端控制器
 * </p>
 *
 * @author zhengxinyu
 * @since 2021-08-28
 */
@Api(tags = "admin端积分等级管理")
@RestController
@RequestMapping("/admin/core/integralGrade")
public class AdminIntegralGradeController {

    @Resource
    private IntegralGradeService integralGradeService;

    @ApiOperation("admin端积分等级列表")
    @GetMapping("/list")
    public List<IntegralGrade> listAll() {
        List<IntegralGrade> integralGradeList = integralGradeService.list();
        System.out.println(integralGradeList);
        return integralGradeList;
    }

    @ApiOperation(value = "根据Id删除",notes = "根据id删除具体的积分项")
    @DeleteMapping("/remove/{id}")
    public boolean removeById(
            @ApiParam(value = "参数id", required = true, example = "100")
            @PathVariable long id) {
        boolean bResult = integralGradeService.removeById(id);
        return bResult;
    }

}

