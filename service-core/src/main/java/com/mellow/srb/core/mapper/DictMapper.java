package com.mellow.srb.core.mapper;

import com.mellow.srb.core.pojo.dto.ExcelDictDTO;
import com.mellow.srb.core.pojo.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 数据字典 Mapper 接口
 * </p>
 *
 * @author zhengxinyu
 * @since 2021-08-28
 */
public interface DictMapper extends BaseMapper<Dict> {

    void insertBatch(@Param("list") List<ExcelDictDTO> list);
}
