package com.mellow.srb.core.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mellow.srb.core.listener.ExcelDictDTOListener;
import com.mellow.srb.core.pojo.dto.ExcelDictDTO;
import com.mellow.srb.core.pojo.entity.Dict;
import com.mellow.srb.core.mapper.DictMapper;
import com.mellow.srb.core.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author zhengxinyu
 * @since 2021-08-28
 */
@Slf4j
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {


    @Transactional(rollbackFor = Exception.class)
    @Override
    public void importData(InputStream inputStream) {
        EasyExcel.read(inputStream, ExcelDictDTO.class, new ExcelDictDTOListener(baseMapper)).sheet().doRead();
        log.info("excel导入成功");
    }

    @Override
    public List<ExcelDictDTO> listExportData() {
        List<Dict> dicts = baseMapper.selectList(null);
        List<ExcelDictDTO> excelDictDTOlist = new ArrayList<>(dicts.size());
        dicts.forEach(dict -> {
            ExcelDictDTO excelDictDTO = new ExcelDictDTO();
            BeanUtils.copyProperties(dict,excelDictDTO);
            excelDictDTOlist.add(excelDictDTO);
        });
        return excelDictDTOlist;
    }

    @Override
    public List<Dict> listByParentId(Long parentId) {

        QueryWrapper<Dict> dictQueryWrapper = new QueryWrapper<>();
        dictQueryWrapper.eq("parent_id", parentId);
        List<Dict> dictList = baseMapper.selectList(dictQueryWrapper);
        dictList.forEach(dict -> {
            boolean hasChildren = this.hasChildren(dict.getId());
            dict.setHasChildren(hasChildren);
        });
        return dictList;
    }

    public boolean hasChildren(Long id) {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<Dict>().eq("parent_id", id);
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count.intValue() > 0) {
            return true;
        }
        return false;

    }


}
