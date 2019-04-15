package com.my.myshop.product.controller;

import com.alibaba.fastjson.JSON;
import com.my.myshop.product.entity.Category;
import com.my.myshop.product.entity.PropertyOption;
import com.my.myshop.product.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CategoryController
 * @Description TODO 分类controller
 * @Auther QingBin Ding
 * @Date 2019/4/4 14:54
 * @Version 1.0
 **/
@Controller
@RequestMapping(value = "category")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @RequestMapping(value = "list")
    public ModelAndView categoryList(){
        return new ModelAndView("categoryView");
    }

    @RequestMapping(value = "getList")
    @ResponseBody
    public List<Category> getList(){
        List<Category> result = categoryService.selectCategoryList();
        return result;
    }



    @RequestMapping(value = "getPropertyByCategoryId")
    @ResponseBody
    public List<PropertyOption> getPropertyByCategoryId(Long id, String name){
        List<PropertyOption> result = new ArrayList<>();
        if(id!=null){
            result= categoryService.selectPropertyByCategoryId(id);
            log.info(JSON.toJSONString(result));
        }
        return result;
    }
}
