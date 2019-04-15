package com.my.myshop.product.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName PropertyOption
 * @Description TODO
 * @Auther QingBin Ding
 * @Date 2019/4/8 15:36
 * @Version 1.0
 **/
@Data
public class PropertyOption {
    private Long id = 0L;

    private Long propertyId;

    private String name;

    private Date createTime;

    private Date updateTime;

    private List<PropertyOption> children;
}
