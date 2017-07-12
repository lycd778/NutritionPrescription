package com.easyhealth365.nutritionprescription.beans;
/**
 * Created by lingxiao-Ching on 2017/7/3.
 */

public class PlanID extends BaseBean{

    private static final long serialVersionUID = 6479899371738745406L;
    /**
     * nourishmentPlanId : 64b9e307-bdb3-46f8-9c57-8af5ac1d2ae3
     * checkTime : 2017-07-04T15:30:00
     * foodExchange : 111
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

    @Override
    public String toString() {
        return "PlanList{" +
                "nourishmentPlanId='" + nourishmentPlanId + '\'' +
                ", checkTime='" + checkTime + '\'' +
                ", foodExchange='" + foodExchange + '\'' +
                ", realName='" + realName + '\'' +
                '}';
    }
}
