package com.my.myshop.product.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Property {
    private Long id;

    private Long categoryId;

    private String name;

    private Date createTime;

    private Date updateTime;

    private List<PropertyOption> propertyOptionList;

}