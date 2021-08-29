package com.mellow.common.exception;

import com.mellow.common.result.R;
import com.mellow.common.result.ResponseEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class UnifiedExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public R handlerException(Exception e) {
        log.error(e.getMessage(),e);
        return R.error();
    }

    @ExceptionHandler(value = BadSqlGrammarException.class)
    public R handlerException(BadSqlGrammarException e) {
        log.error(e.getMessage(),e);
        return R.setResult(ResponseEnum.BAD_SQL_GRAMMAR_ERROR);
    }

    @ExceptionHandler(value = BusinessException.class)
    public R handlerException(BusinessException e) {
        log.error(e.getMessage(),e);
        return R.error().Message(e.getMessage()).code(e.getCode());
    }
}
