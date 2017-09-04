package com.zhouao.springtest.ioc;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration
        ({"/spring/app*.xml","/spring/service/app*.xml"})
public class SayHelloTest {
    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void say() throws Exception {
    }

}