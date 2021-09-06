package com.mellow.srb.core.service;

import com.mellow.srb.core.pojo.dto.ExcelDictDTO;
import com.mellow.srb.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.InputStream;
import java.util.List;

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

    List<ExcelDictDTO> listExportData();

    List<Dict> listByParentId(Long parentId);
}
