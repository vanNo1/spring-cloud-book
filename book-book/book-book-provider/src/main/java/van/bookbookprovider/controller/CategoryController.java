package van.bookbookprovider.controller;


import base.ServerResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import van.bookbookprovider.service.serviceImpl.CategoryServiceImpl;

import javax.annotation.Resource;

/**
 * @author Van
 * @date 2020/3/16 - 12:37
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Resource
    private CategoryServiceImpl categoryService;

    @RequestMapping("/list.do")
    public ServerResponse list() {
        return categoryService.list();
    }
}
