package com.easyhealth365.nutritionprescription.beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lingxiao-Ching on 2017/7/12.
 */

public class NormalResult extends BaseBean {


    private static final long serialVersionUID = -5557338762905348856L;
    /**
     * status : 104
     * message : 该手机号码可以注册
     */

    private int status;
    private String message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "NormalResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
