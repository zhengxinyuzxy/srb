package com.mellow.srb.core.controller.admin;


import com.mellow.common.exception.BusinessException;
import com.mellow.common.result.R;
import com.mellow.common.result.ResponseEnum;
import com.mellow.srb.core.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 数据字典 前端控制器
 * </p>
 *
 * @author zhengxinyu
 * @since 2021-08-28
 */
@Api(tags = "数据字典管理")
@CrossOrigin
@RestController
@RequestMapping("/admin/core/dict")
public class AdminDictController {

    @Resource
    private DictService dictService;

    @ApiOperation("excel数剧批量导入")
    @PostMapping("/import")
    public R batchImport(
            @ApiParam(value = "excel数据字典文件", required = true)
            @RequestParam("file") MultipartFile multipartFile) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            dictService.importData(inputStream);
            return R.ok().message("批量导入数据字典成功");
        } catch (Exception e) {
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR,e);
        }

    }

}

