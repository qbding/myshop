package com.my.myshop.product.dao;

import com.my.myshop.product.entity.Category;
import com.my.myshop.product.entity.PropertyOption;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    List<Category> selectAllCategoryList();

    List<PropertyOption> selectPropertyByCategoryId(@Param("id") Long id);
}