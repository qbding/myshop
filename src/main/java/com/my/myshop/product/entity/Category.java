package com.my.myshop.product.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Category {
    private Long id;

    private Long parentId;

    private String name;

    private Boolean status;

    private Integer sortOrder;

    private Date createTime;

    private Date updateTime;

    private List<Category> children;

    private Property property;


}