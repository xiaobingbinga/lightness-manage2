package com.xuetang9.qingying.service.impl;

import com.xuetang9.qingying.domain.Brand;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/16 10:46
 * @copyright 老九学堂
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class BrandServiceImplTest {

    @Autowired
    private BrandServiceImpl brandService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listByPageAndFilter() {
        // 构建查询的条件
        String filterWords = "H";
        List<Brand> brands = brandService.listByPageAndFilter(1, 10, filterWords);
        Assert.assertNotEquals(0,brands.size());
    }
}