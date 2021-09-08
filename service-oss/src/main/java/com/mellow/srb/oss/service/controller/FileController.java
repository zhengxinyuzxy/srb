package com.mellow.srb.oss.service.controller;

import com.mellow.common.exception.BusinessException;
import com.mellow.common.result.R;
import com.mellow.common.result.ResponseEnum;
import com.mellow.srb.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

@Api(tags = "阿里云文件管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/api/oss/file")
public class FileController {

    @Resource
    private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public R upload(
            @ApiParam(value = "文件", required = true)
            @RequestParam("file") MultipartFile multipartFile,

            @ApiParam(value = "模块", required = true)
            @RequestParam("module") String module
    ) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            String originalFilename = multipartFile.getOriginalFilename();
            String url = fileService.upload(inputStream, module, originalFilename);

            return R.ok().message("文件上传成功").data("url", url);
        } catch (IOException e) {
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR, e);
        }
    }
}
