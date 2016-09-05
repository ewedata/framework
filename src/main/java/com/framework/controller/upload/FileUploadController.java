package com.framework.controller.upload;

import javax.servlet.ServletContext;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.ServletContextAware;

/**
 * @author matrix
 * @since 2016年8月20日 下午5:42:05
 */
@Controller
public class FileUploadController implements ServletContextAware {

    private ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    public String handleUploadData() {


        return "";
    }

    public ServletContext getServletContext() {
        return servletContext;
    }


}
