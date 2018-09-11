package com.imooc.o2o;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 用来配置spring和junit整合，junit启动时加载springIoC容器
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junitspring文件位置在哪
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class BaseTest  {

}
