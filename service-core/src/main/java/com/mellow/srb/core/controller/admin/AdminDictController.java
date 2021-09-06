package com.mellow.srb.core.controller.admin;


import com.alibaba.excel.EasyExcel;
import com.mellow.common.exception.BusinessException;
import com.mellow.common.result.R;
import com.mellow.common.result.ResponseEnum;
import com.mellow.srb.core.pojo.dto.ExcelDictDTO;
import com.mellow.srb.core.pojo.entity.Dict;
import com.mellow.srb.core.service.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;

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
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR, e);
        }

    }

    @ApiOperation("excel数据的导出")
    @GetMapping("/export")
    public void download(

            @ApiParam(value = "响应类型", required = true)
                    HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("测试", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), ExcelDictDTO.class).sheet("数据字典").doWrite(dictService.listExportData());
    }

    @ApiOperation("根据上级id获取子节点数据列表")
    @GetMapping("/listByParentId/{parentId}")
    public R listByParentId(
            @ApiParam(value = "上级节点id",required = true)
            @PathVariable("parentId") Long parentId) {
        List<Dict> dictList = dictService.listByParentId(parentId);
        return R.ok().data("list",dictList);
    }

}

