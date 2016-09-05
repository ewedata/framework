package com.framework.controller;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.framework.core.common.HttpStatus;
import com.framework.core.common.JsonRet;



public abstract class BaseController {

    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected static final String JSON_SUCCESS = "success";
    protected static final String JSON_FAILED = "failed";


    /**
     * 使用模板渲染页面
     *
     * @param data 要渲染的数据
     * @param template 模板名
     * @return
     */
    public static ModelAndView httpResponse(Map<String, Object> data, String template) {
        ModelAndView mv = new ModelAndView(template, data);
        return mv;
    }

    /**
     * 重定向页面到url
     *
     * @param url
     * @return
     */
    public static ModelAndView httpResponseRedirect(String url) {
        return new ModelAndView(new RedirectView(url));
    }

    /**
     * 返回相应的HTTP状态码
     *
     * @param status
     * @param content
     * @return
     */
    public static HttpStatus httpError(int status, String content) {
        return new HttpStatus(status, content);
    }


    /**
     * 以框架特定的json格式返回数据
     *
     * @param data 数据域
     * @param code 返回码
     * @param msg 用户可读的消息
     * @return
     */
    public static JsonRet jsonResponse(Object data, int code, String msg) {
        JsonRet ret = new JsonRet();
        ret.setData(data);
        ret.setCode(code);
        ret.setMsg(msg);

        return ret;
    }

    /**
     * 将数据对象转换为json格式返回
     *
     * @param data
     * @return
     */
    public static Object jsonDataResponse(Object data) {
        return data;
    }

    public static void streamOutput(HttpServletResponse response, Object object, String contentType,
            boolean cacheable) throws IOException {
        // 禁止图像缓存
        if (!cacheable) {
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
        }

        response.setContentType(contentType);

        // 将对象输出到servlet输出流中
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();

            if (contentType.equals("image/jpeg")) {
                ImageIO.write((RenderedImage) object, "jpg", out);
            }
        } catch (IOException e) {
            logger.error(e.getLocalizedMessage(), e);
            throw e;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    logger.error(e.getLocalizedMessage(), e);
                    throw e;
                }
            }
        }
    }


}
