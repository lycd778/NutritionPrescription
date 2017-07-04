package com.easyhealth365.nutritionprescription.beans;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lingxiao-Ching on 2017/7/3.
 */

public class PlanList extends BaseBean{

    private static final long serialVersionUID = 8956032978505646804L;
    /**
     * nourishmentPlanId : 8d317c21-64e9-464b-9cfe-6c3e5dca4cec
     * checkTime : 2017-07-04T12:09:00
     * foodExchange : 23
     * realName : 开发霄Test
     */

    private String nourishmentPlanId;
    private String checkTime;
    private String foodExchange;
    private String realName;

    public static List<PlanList> arrayPlanListFromData(String str, String key) {

        try {
            JSONObject jsonObject = new JSONObject(str);
            Type listType = new TypeToken<ArrayList<PlanList>>() {
            }.getType();

            return new Gson().fromJson(jsonObject.getString(str), listType);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new ArrayList();


    }

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
