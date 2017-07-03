package com.easyhealth365.nutritionprescription.beans;

/**
 * Created by lingxiao-Ching on 2017/7/3.
 */

public class PlanList {

    /**
     * nourishmentPlanId : 8d317c21-64e9-464b-9cfe-6c3e5dca4cec
     * checkTime : 2017-07-03T11:09:00
     * foodExchange : 19
     * realName : 开发霄Test
     */

    private String nourishmentPlanId;
    private String checkTime;
    private String foodExchange;
    private String realName;

    public String getNourishmentPlanId() {
        return nourishmentPlanId;
    }

    public void setNourishmentPlanId(String nourishmentPlanId) {
        this.nourishmentPlanId = nourishmentPlanId;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getFoodExchange() {
        return foodExchange;
    }

    public void setFoodExchange(String foodExchange) {
        this.foodExchange = foodExchange;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
