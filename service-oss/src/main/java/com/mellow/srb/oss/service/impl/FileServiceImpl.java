package com.mellow.srb.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.mellow.srb.oss.service.FileService;
import com.mellow.srb.oss.util.OssProperties;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(InputStream inputStream, String module, String fileName) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(OssProperties.ENDPOINT, OssProperties.KEY_ID, OssProperties.KEY_SECRET);

        if (!ossClient.doesBucketExist(OssProperties.BUCKET_NAME)) {
            ossClient.createBucket(OssProperties.BUCKET_NAME);
            ossClient.setBucketAcl(OssProperties.BUCKET_NAME, CannedAccessControlList.PublicRead);
        }

        fileName = UUID.randomUUID().toString() + fileName.substring(fileName.lastIndexOf("."));
        String timeFolder = new DateTime().toString("/yyyy/MM/dd/");
        String keyFileName = module + timeFolder + fileName;

        // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
        ossClient.putObject(OssProperties.BUCKET_NAME, keyFileName, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        // https://srb-file-20210908.oss-cn-shenzhen.aliyuncs.com/mao.jpg
        return "https://" + OssProperties.BUCKET_NAME + "." + OssProperties.ENDPOINT + "/" + keyFileName;
    }
}
