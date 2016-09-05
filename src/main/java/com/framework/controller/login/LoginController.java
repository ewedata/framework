package com.framework.controller.login;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.framework.controller.BaseController;

/**
 * Login controller
 * 
 * @author matrix
 * @since 2016年8月20日 下午1:55:21
 */
@Controller
public class LoginController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @ResponseBody
    @RequestMapping("/login")
    public Object login() {
        Map<String, Object> map = new HashMap<String, Object>();
        LOG.info("==login ...");
        return httpResponse(map, "/main.html");
    }

    @RequestMapping(value = {"/home", "/"})
    public String home() {
        LOG.info("==== home....");

        return "index";
    }

}
