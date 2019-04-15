package com.my.myshop.product.service;

import com.my.myshop.product.dao.CategoryMapper;
import com.my.myshop.product.entity.Category;
import com.my.myshop.product.entity.PropertyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CategoryServiceImpl
 * @Description TODO
 * @Auther QingBin Ding
 * @Date 2019/4/4 14:55
 * @Version 1.0
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public  List<Category> selectCategoryList() {
        Map<String, Object> result  = new HashMap<>();
        List<Category> list =  categoryMapper.selectAllCategoryList();
        result.put("list",list);
        return list;
    }

    @Override
    public List<PropertyOption> selectPropertyByCategoryId(Long id) {
        return categoryMapper.selectPropertyByCategoryId(id);
    }
}
