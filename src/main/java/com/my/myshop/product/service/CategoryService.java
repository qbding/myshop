package com.my.myshop.product.service;

import com.my.myshop.product.entity.Category;
import com.my.myshop.product.entity.PropertyOption;

import java.util.List;

public interface CategoryService {
    List<Category> selectCategoryList();

    List<PropertyOption> selectPropertyByCategoryId(Long id);
}
