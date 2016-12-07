package com.framework.controller.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.framework.controller.BaseController;
import com.framework.service.book.IBookService;
import com.framework.web.IUrlDef;

/**
 * @author LIUAOZ
 * @since 2016年12月7日 下午3:18:53
 * @version 1.0
 */
@RequestMapping(IUrlDef.book)
@Controller
public class BookController extends BaseController {

    @Autowired
    IBookService bookService;


    @RequestMapping("/list")
    public ModelAndView list(@RequestParam Long id) {
        ModelAndView mv = new ModelAndView("/");
        mv.addObject(bookService.queryBookById(id));
        return mv;
    }

}
