package com.my.myshop.product.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Product {
    private Long id;

    private Long categoryId;

    private Long brandId;

    private String spuName;

    private BigDecimal price;

    private Byte status;

    private Date createTime;

    private Date updateTime;

}