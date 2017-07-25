package com.easyhealth365.nutritionprescription.beans;

/**
 * Created by lingxiao-Ching on 2017/7/25.
 */

public class UpdatePasswordResult extends BaseBean {

    private static final long serialVersionUID = 5137434340212650905L;
    /**
     * status : 100
     */

    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UpdatePasswordResult{" +
                "status=" + status +
                '}';
    }
}
