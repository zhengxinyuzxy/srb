package com.mellow.srb.core.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.mellow.srb.core.pojo.dto.ExcelDictDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelDictDTOListener extends AnalysisEventListener<ExcelDictDTO> {
    @Override
    public void invoke(ExcelDictDTO excelDictDTO, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", excelDictDTO);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("所有数据解析完成！");
    }
}
