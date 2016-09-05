package com.framework;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(locations = {"classpath:spring/spring_root.xml"})
@RunWith(JUnit4ClassRunner.class)
public class BaseTest {

    @Before
    public void init() {}

}
