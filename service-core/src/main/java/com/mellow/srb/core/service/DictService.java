package com.mellow.srb.core.service;

import com.mellow.srb.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;

/**
 * <p>
 * 数据字典 服务类
 * </p>
 *
 * @author zhengxinyu
 * @since 2021-08-28
 */
public interface DictService extends IService<Dict> {
    void importData(InputStream inputStream);

}
