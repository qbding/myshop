package com.my.myshop.exception;


import com.my.myshop.common.constants.Constants;


public class MyShopException extends RuntimeException{

    private int statusCode = Constants.RESP_STATUS_INTERNAL_ERROR;

    public MyShopException(int statusCode,String message) {
        super(message);
        this.statusCode = statusCode;
    }
    public MyShopException(String message) {
        super(message);
    }

    public int getStatusCode() {
        return statusCode;
    }
}
