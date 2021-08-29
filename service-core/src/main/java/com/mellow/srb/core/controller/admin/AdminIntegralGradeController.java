package com.mellow.srb.core.controller.admin;


import com.mellow.common.exception.Assert;
import com.mellow.common.exception.BusinessException;
import com.mellow.common.result.R;
import com.mellow.common.result.ResponseEnum;
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
@CrossOrigin
@RestController
@RequestMapping("/admin/core/integralGrade")
public class AdminIntegralGradeController {

    @Resource
    private IntegralGradeService integralGradeService;

    /**
     * 查找所有积分等级
     *
     * @return
     */
    @ApiOperation("admin端积分等级列表")
    @GetMapping("/list")
    public R listAll() {
        List<IntegralGrade> integralGradeList = integralGradeService.list();
        System.out.println(integralGradeList);
        // 统一返回结果
        return R.ok().data("list", integralGradeList).Message("积分列表查询成功");
    }

    /**
     * 根据id删除积分等级
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据Id删除", notes = "根据id删除具体的积分项")
    @DeleteMapping("/remove/{id}")
    public R removeById(
            @ApiParam(value = "参数id", required = true, example = "100")
            @PathVariable long id) {
        boolean bResult = integralGradeService.removeById(id);
        if (bResult) {
            return R.ok().Message("根据id删除成功");
        } else {
            return R.ok().Message("根据id删除失败");
        }
    }

    /**
     * 新增积分等级
     *
     * @param integralGrade
     * @return
     */
    @ApiOperation("新增积分等级")
    @PostMapping("/save")
    public R save(
            @ApiParam(value = "新增实体类参数", required = true)
            @RequestBody IntegralGrade integralGrade) {

        /*if (integralGrade.getBorrowAmount() == null) {
            throw new BusinessException(ResponseEnum.BORROW_AMOUNT_NULL_ERROR);
        }*/

        Assert.notNull(integralGrade.getBorrowAmount(), ResponseEnum.BORROW_AMOUNT_NULL_ERROR);

        boolean saveResult = integralGradeService.save(integralGrade);
        if (saveResult) {
            return R.ok().Message("新增积分等级成功");
        } else {
            return R.error().Message("新增积分等级失败");
        }
    }

    /**
     * 根据id获取积分等级
     *
     * @param id
     * @return
     */
    @ApiOperation("根据id获取积分等级")
    @GetMapping("/get/{id}")
    public R getById(
            @ApiParam(value = "查找参数id", required = true, example = "1")
            @PathVariable Long id) {
        IntegralGrade integralGrade = integralGradeService.getById(id);
        if (integralGrade != null) {
            return R.ok().data("integralGrade", integralGrade);
        } else {
            return R.error().Message("integralGrade数据不存在");
        }
    }

    /**
     * 根据id修改积分等级
     *
     * @param integralGrade
     * @return
     */
    @ApiOperation("根据id修改积分等级")
    @PutMapping("/update")
    public R updateById(
            @RequestBody IntegralGrade integralGrade) {
        boolean updateResult = integralGradeService.updateById(integralGrade);
        if (updateResult) {
            return R.ok().Message("修改成功");
        } else {
            return R.error().Message("修改失败");
        }
    }

}

