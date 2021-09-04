package com.mellow.srb.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.mellow.srb.core.mapper.DictMapper;
import com.mellow.srb.core.pojo.dto.ExcelDictDTO;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Slf4j
public class ExcelDictDTOListener extends AnalysisEventListener<ExcelDictDTO> {

    private DictMapper dictMapper;

    private List<ExcelDictDTO> list = new ArrayList<>();

    private static final int BATCH_COUNT = 5;

    public ExcelDictDTOListener(DictMapper dictMapper) {
        this.dictMapper = dictMapper;
    }


    @Override
    public void invoke(ExcelDictDTO excelDictDTO, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", excelDictDTO);
        list.add(excelDictDTO);
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完成！");
        saveData();
    }

    private void saveData() {
        log.info("{}条数据被存储到数据库", list.size());
        dictMapper.insertBatch(list);
        log.info("{}条数据被存储到数据库成功", list.size());

    }

}
