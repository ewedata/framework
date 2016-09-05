package com.framework.service.demo;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.framework.dao.demo.IDemoDao;
import com.framework.entry.DemoEntry;

@Service("IDemoService")
public class DemoServiceImpl implements IDemoService {

    private final Logger logger = LoggerFactory.getLogger(DemoServiceImpl.class);

    @Resource
    private IDemoDao iDemoDao;

    @Override
    public void add(DemoEntry demoEntry) {
        logger.info("==demo service - add start");
        iDemoDao.save(demoEntry);
        logger.info("==demo service - add end");
    }

}
