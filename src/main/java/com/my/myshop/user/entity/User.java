package com.my.myshop.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.my.myshop.common.constants.RegexConstants;
import lombok.Data;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class User implements Serializable {

    private Long id;

    @Pattern(regexp = RegexConstants.REGEX_MOBILE, message = "不是手机号")
    protected String phone;

    private String name;

    private String password;

    private String headImg;

    private Date addTime;

    private Date updateTime;
}