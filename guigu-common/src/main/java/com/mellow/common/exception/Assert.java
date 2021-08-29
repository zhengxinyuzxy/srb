package com.mellow.common.exception;

import com.mellow.common.result.ResponseEnum;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Assert {

    public static void notNull(Object object, ResponseEnum responseEnum) {
        if (object == null) {
            log.info("assert in object is null");
            throw new BusinessException(responseEnum);
        }
    }
}
