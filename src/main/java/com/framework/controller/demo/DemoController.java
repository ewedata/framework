package com.framework.controller.demo;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.entry.DemoEntry;
import com.framework.service.demo.IDemoService;
import com.framework.web.IUrlDef;

/**
 * demo controller
 * 
 * @author matrix
 * @since 2016年8月19日 下午5:07:01
 */
@Controller
public class DemoController {

    @Resource(name = "IDemoService")
    private IDemoService iDemoService;

    @RequestMapping(IUrlDef.demo_add)
    @ResponseBody
    public Object add(DemoEntry entry) {
        iDemoService.add(entry);
        return entry;
    }

    @RequestMapping(value = "/test")
    public String test(Model m) {
        System.out.println("==== test ...");
        return "test";
    }

}
