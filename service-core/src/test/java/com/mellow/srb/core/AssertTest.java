package com.mellow.srb.core;


import org.junit.Test;
import org.springframework.util.Assert;

public class AssertTest {

    @Test
    public void assert01() {
        Object o = null;
        Assert.notNull(o,"assert01参数为空");
    }

    @Test
    public void assert02() {
        Object o = null;
        if (o == null) {
            throw new IllegalArgumentException("assert02参数异常");
        }
    }
}
