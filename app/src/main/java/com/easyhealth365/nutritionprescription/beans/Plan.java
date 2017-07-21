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

public class Plan extends BaseBean{

    private static final long serialVersionUID = 1353203288970544275L;
    /**
     * nourishmentPlanId : 64b9e307-bdb3-46f8-9c57-8af5ac1d2ae3
     * TargetL : 短期目标
     * Target : 戒烟
     * targetWeight : 200
     * strengthFactor : 50
     * calorie : 10000.0
     * foodExchange : 111
     * CHO : 30
     * PR : 30
     * fat : 40
     * vegetable : 10
     * fruit : 10
     * bread : 13
     * bean : 15
     * milk : 15
     * meat : 18
     * oil : 15
     * nut : 15
     * assignment : 1:1:1
     * breakfast_plan : 20
     * breakfast_addition_plan : 20
     * lunch_plan : 20
     * lunch_addition_plan : 20
     * dinner_plan : 20
     * dinner_addition_plan : 11
     * breakfast_vegetable : 1
     * breakfast_addition_vegetable : 1
     * lunch_vegetable : 2
     * lunch_addition_vegetable : 1
     * dinner_vegetable : 3
     * dinner_addition_vegetable : 2
     * breakfast_fruit : 1
     * breakfast_addition_fruit : 3
     * lunch_fruit : 2
     * lunch_addition_fruit : 1
     * dinner_fruit : 2
     * dinner_addition_fruit : 1
     * breakfast_bread : 4
     * breakfast_addition_bread : 3
     * lunch_bread : 2
     * lunch_addition_bread : 1
     * dinner_bread : 2
     * dinner_addition_bread : 1
     * breakfast_bean : 2
     * breakfast_addition_bean : 2
     * lunch_bean : 5
     * lunch_addition_bean : 3
     * dinner_bean : 2
     * dinner_addition_bean : 1
     * breakfast_milk : 5
     * breakfast_addition_milk : 2
     * lunch_milk : 2
     * lunch_addition_milk : 2
     * dinner_milk : 2
     * dinner_addition_milk : 2
     * breakfast_meat : 2
     * breakfast_addition_meat : 4
     * lunch_meat : 2
     * lunch_addition_meat : 4
     * dinner_meat : 4
     * dinner_addition_meat : 2
     * breakfast_oil : 2
     * breakfast_addition_oil : 3
     * lunch_oil : 3
     * lunch_addition_oil : 3
     * dinner_oil : 3
     * dinner_addition_oil : 1
     * breakfast_nut : 3
     * breakfast_addition_nut : 2
     * lunch_nut : 2
     * lunch_addition_nut : 5
     * dinner_nut : 2
     * dinner_addition_nut : 1
     * foodProhibited : 韭菜
     * remark : 白菜
     * checkTime : 2017-07-04T15:30:00
     * foodRecommend : 青菜
     * template_id : 00000000-0000-0000-0000-000000000000
     * food_addition : 0
     * auto_dinner : false
     * need_print : false
     */

    private String nourishmentPlanId;
    private String TargetL;
    private String Target;
    private String targetWeight;
    private String strengthFactor;
    private String calorie;
    private String foodExchange;
    private String height ;//身高
    private String weight ;//体重
    private String bmi;// DMI
    private int CHO;
    private int PR;
    private int fat;
    private int vegetable;
    private int fruit;
    private int bread;
    private int bean;
    private int milk;
    private int meat;
    private int oil;
    private int nut;
    private String assignment;
    private int breakfast_plan;
    private int breakfast_addition_plan;
    private int lunch_plan;
    private int lunch_addition_plan;
    private int dinner_plan;
    private int dinner_addition_plan;
    private int breakfast_vegetable;
    private int breakfast_addition_vegetable;
    private int lunch_vegetable;
    private int lunch_addition_vegetable;
    private int dinner_vegetable;
    private int dinner_addition_vegetable;
    private int breakfast_fruit;
    private int breakfast_addition_fruit;
    private int lunch_fruit;
    private int lunch_addition_fruit;
    private int dinner_fruit;
    private int dinner_addition_fruit;
    private int breakfast_bread;
    private int breakfast_addition_bread;
    private int lunch_bread;
    private int lunch_addition_bread;
    private int dinner_bread;
    private int dinner_addition_bread;
    private int breakfast_bean;
    private int breakfast_addition_bean;
    private int lunch_bean;
    private int lunch_addition_bean;
    private int dinner_bean;
    private int dinner_addition_bean;
    private int breakfast_milk;
    private int breakfast_addition_milk;
    private int lunch_milk;
    private int lunch_addition_milk;
    private int dinner_milk;
    private int dinner_addition_milk;
    private int breakfast_meat;
    private int breakfast_addition_meat;
    private int lunch_meat;
    private int lunch_addition_meat;
    private int dinner_meat;
    private int dinner_addition_meat;
    private int breakfast_oil;
    private int breakfast_addition_oil;
    private int lunch_oil;
    private int lunch_addition_oil;
    private int dinner_oil;
    private int dinner_addition_oil;
    private int breakfast_nut;
    private int breakfast_addition_nut;
    private int lunch_nut;
    private int lunch_addition_nut;
    private int dinner_nut;
    private int dinner_addition_nut;
    private String foodProhibited;
    private String remark;
    private String checkTime;
    private String foodRecommend;
    private String template_id;
    private int food_addition;
    private boolean auto_dinner;
    private boolean need_print;

    public String getNourishmentPlanId() {
        return nourishmentPlanId;
    }

    public void setNourishmentPlanId(String nourishmentPlanId) {
        this.nourishmentPlanId = nourishmentPlanId;
    }

    public String getTargetL() {
        return TargetL;
    }

    public void setTargetL(String TargetL) {
        this.TargetL = TargetL;
    }

    public String getTarget() {
        return Target;
    }

    public void setTarget(String Target) {
        this.Target = Target;
    }

    public String getTargetWeight() {
        return targetWeight;
    }

    public void setTargetWeight(String targetWeight) {
        this.targetWeight = targetWeight;
    }

    public String getStrengthFactor() {
        return strengthFactor;
    }

    public void setStrengthFactor(String strengthFactor) {
        this.strengthFactor = strengthFactor;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public String getFoodExchange() {
        return foodExchange;
    }

    public void setFoodExchange(String foodExchange) {
        this.foodExchange = foodExchange;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public int getCHO() {
        return CHO;
    }

    public void setCHO(int CHO) {
        this.CHO = CHO;
    }

    public int getPR() {
        return PR;
    }

    public void setPR(int PR) {
        this.PR = PR;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getVegetable() {
        return vegetable;
    }

    public void setVegetable(int vegetable) {
        this.vegetable = vegetable;
    }

    public int getFruit() {
        return fruit;
    }

    public void setFruit(int fruit) {
        this.fruit = fruit;
    }

    public int getBread() {
        return bread;
    }

    public void setBread(int bread) {
        this.bread = bread;
    }

    public int getBean() {
        return bean;
    }

    public void setBean(int bean) {
        this.bean = bean;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public int getMeat() {
        return meat;
    }

    public void setMeat(int meat) {
        this.meat = meat;
    }

    public int getOil() {
        return oil;
    }

    public void setOil(int oil) {
        this.oil = oil;
    }

    public int getNut() {
        return nut;
    }

    public void setNut(int nut) {
        this.nut = nut;
    }

    public String getAssignment() {
        return assignment;
    }

    public void setAssignment(String assignment) {
        this.assignment = assignment;
    }

    public int getBreakfast_plan() {
        return breakfast_plan;
    }

    public void setBreakfast_plan(int breakfast_plan) {
        this.breakfast_plan = breakfast_plan;
    }

    public int getBreakfast_addition_plan() {
        return breakfast_addition_plan;
    }

    public void setBreakfast_addition_plan(int breakfast_addition_plan) {
        this.breakfast_addition_plan = breakfast_addition_plan;
    }

    public int getLunch_plan() {
        return lunch_plan;
    }

    public void setLunch_plan(int lunch_plan) {
        this.lunch_plan = lunch_plan;
    }

    public int getLunch_addition_plan() {
        return lunch_addition_plan;
    }

    public void setLunch_addition_plan(int lunch_addition_plan) {
        this.lunch_addition_plan = lunch_addition_plan;
    }

    public int getDinner_plan() {
        return dinner_plan;
    }

    public void setDinner_plan(int dinner_plan) {
        this.dinner_plan = dinner_plan;
    }

    public int getDinner_addition_plan() {
        return dinner_addition_plan;
    }

    public void setDinner_addition_plan(int dinner_addition_plan) {
        this.dinner_addition_plan = dinner_addition_plan;
    }

    public int getBreakfast_vegetable() {
        return breakfast_vegetable;
    }

    public void setBreakfast_vegetable(int breakfast_vegetable) {
        this.breakfast_vegetable = breakfast_vegetable;
    }

    public int getBreakfast_addition_vegetable() {
        return breakfast_addition_vegetable;
    }

    public void setBreakfast_addition_vegetable(int breakfast_addition_vegetable) {
        this.breakfast_addition_vegetable = breakfast_addition_vegetable;
    }

    public int getLunch_vegetable() {
        return lunch_vegetable;
    }

    public void setLunch_vegetable(int lunch_vegetable) {
        this.lunch_vegetable = lunch_vegetable;
    }

    public int getLunch_addition_vegetable() {
        return lunch_addition_vegetable;
    }

    public void setLunch_addition_vegetable(int lunch_addition_vegetable) {
        this.lunch_addition_vegetable = lunch_addition_vegetable;
    }

    public int getDinner_vegetable() {
        return dinner_vegetable;
    }

    public void setDinner_vegetable(int dinner_vegetable) {
        this.dinner_vegetable = dinner_vegetable;
    }

    public int getDinner_addition_vegetable() {
        return dinner_addition_vegetable;
    }

    public void setDinner_addition_vegetable(int dinner_addition_vegetable) {
        this.dinner_addition_vegetable = dinner_addition_vegetable;
    }

    public int getBreakfast_fruit() {
        return breakfast_fruit;
    }

    public void setBreakfast_fruit(int breakfast_fruit) {
        this.breakfast_fruit = breakfast_fruit;
    }

    public int getBreakfast_addition_fruit() {
        return breakfast_addition_fruit;
    }

    public void setBreakfast_addition_fruit(int breakfast_addition_fruit) {
        this.breakfast_addition_fruit = breakfast_addition_fruit;
    }

    public int getLunch_fruit() {
        return lunch_fruit;
    }

    public void setLunch_fruit(int lunch_fruit) {
        this.lunch_fruit = lunch_fruit;
    }

    public int getLunch_addition_fruit() {
        return lunch_addition_fruit;
    }

    public void setLunch_addition_fruit(int lunch_addition_fruit) {
        this.lunch_addition_fruit = lunch_addition_fruit;
    }

    public int getDinner_fruit() {
        return dinner_fruit;
    }

    public void setDinner_fruit(int dinner_fruit) {
        this.dinner_fruit = dinner_fruit;
    }

    public int getDinner_addition_fruit() {
        return dinner_addition_fruit;
    }

    public void setDinner_addition_fruit(int dinner_addition_fruit) {
        this.dinner_addition_fruit = dinner_addition_fruit;
    }

    public int getBreakfast_bread() {
        return breakfast_bread;
    }

    public void setBreakfast_bread(int breakfast_bread) {
        this.breakfast_bread = breakfast_bread;
    }

    public int getBreakfast_addition_bread() {
        return breakfast_addition_bread;
    }

    public void setBreakfast_addition_bread(int breakfast_addition_bread) {
        this.breakfast_addition_bread = breakfast_addition_bread;
    }

    public int getLunch_bread() {
        return lunch_bread;
    }

    public void setLunch_bread(int lunch_bread) {
        this.lunch_bread = lunch_bread;
    }

    public int getLunch_addition_bread() {
        return lunch_addition_bread;
    }

    public void setLunch_addition_bread(int lunch_addition_bread) {
        this.lunch_addition_bread = lunch_addition_bread;
    }

    public int getDinner_bread() {
        return dinner_bread;
    }

    public void setDinner_bread(int dinner_bread) {
        this.dinner_bread = dinner_bread;
    }

    public int getDinner_addition_bread() {
        return dinner_addition_bread;
    }

    public void setDinner_addition_bread(int dinner_addition_bread) {
        this.dinner_addition_bread = dinner_addition_bread;
    }

    public int getBreakfast_bean() {
        return breakfast_bean;
    }

    public void setBreakfast_bean(int breakfast_bean) {
        this.breakfast_bean = breakfast_bean;
    }

    public int getBreakfast_addition_bean() {
        return breakfast_addition_bean;
    }

    public void setBreakfast_addition_bean(int breakfast_addition_bean) {
        this.breakfast_addition_bean = breakfast_addition_bean;
    }

    public int getLunch_bean() {
        return lunch_bean;
    }

    public void setLunch_bean(int lunch_bean) {
        this.lunch_bean = lunch_bean;
    }

    public int getLunch_addition_bean() {
        return lunch_addition_bean;
    }

    public void setLunch_addition_bean(int lunch_addition_bean) {
        this.lunch_addition_bean = lunch_addition_bean;
    }

    public int getDinner_bean() {
        return dinner_bean;
    }

    public void setDinner_bean(int dinner_bean) {
        this.dinner_bean = dinner_bean;
    }

    public int getDinner_addition_bean() {
        return dinner_addition_bean;
    }

    public void setDinner_addition_bean(int dinner_addition_bean) {
        this.dinner_addition_bean = dinner_addition_bean;
    }

    public int getBreakfast_milk() {
        return breakfast_milk;
    }

    public void setBreakfast_milk(int breakfast_milk) {
        this.breakfast_milk = breakfast_milk;
    }

    public int getBreakfast_addition_milk() {
        return breakfast_addition_milk;
    }

    public void setBreakfast_addition_milk(int breakfast_addition_milk) {
        this.breakfast_addition_milk = breakfast_addition_milk;
    }

    public int getLunch_milk() {
        return lunch_milk;
    }

    public void setLunch_milk(int lunch_milk) {
        this.lunch_milk = lunch_milk;
    }

    public int getLunch_addition_milk() {
        return lunch_addition_milk;
    }

    public void setLunch_addition_milk(int lunch_addition_milk) {
        this.lunch_addition_milk = lunch_addition_milk;
    }

    public int getDinner_milk() {
        return dinner_milk;
    }

    public void setDinner_milk(int dinner_milk) {
        this.dinner_milk = dinner_milk;
    }

    public int getDinner_addition_milk() {
        return dinner_addition_milk;
    }

    public void setDinner_addition_milk(int dinner_addition_milk) {
        this.dinner_addition_milk = dinner_addition_milk;
    }

    public int getBreakfast_meat() {
        return breakfast_meat;
    }

    public void setBreakfast_meat(int breakfast_meat) {
        this.breakfast_meat = breakfast_meat;
    }

    public int getBreakfast_addition_meat() {
        return breakfast_addition_meat;
    }

    public void setBreakfast_addition_meat(int breakfast_addition_meat) {
        this.breakfast_addition_meat = breakfast_addition_meat;
    }

    public int getLunch_meat() {
        return lunch_meat;
    }

    public void setLunch_meat(int lunch_meat) {
        this.lunch_meat = lunch_meat;
    }

    public int getLunch_addition_meat() {
        return lunch_addition_meat;
    }

    public void setLunch_addition_meat(int lunch_addition_meat) {
        this.lunch_addition_meat = lunch_addition_meat;
    }

    public int getDinner_meat() {
        return dinner_meat;
    }

    public void setDinner_meat(int dinner_meat) {
        this.dinner_meat = dinner_meat;
    }

    public int getDinner_addition_meat() {
        return dinner_addition_meat;
    }

    public void setDinner_addition_meat(int dinner_addition_meat) {
        this.dinner_addition_meat = dinner_addition_meat;
    }

    public int getBreakfast_oil() {
        return breakfast_oil;
    }

    public void setBreakfast_oil(int breakfast_oil) {
        this.breakfast_oil = breakfast_oil;
    }

    public int getBreakfast_addition_oil() {
        return breakfast_addition_oil;
    }

    public void setBreakfast_addition_oil(int breakfast_addition_oil) {
        this.breakfast_addition_oil = breakfast_addition_oil;
    }

    public int getLunch_oil() {
        return lunch_oil;
    }

    public void setLunch_oil(int lunch_oil) {
        this.lunch_oil = lunch_oil;
    }

    public int getLunch_addition_oil() {
        return lunch_addition_oil;
    }

    public void setLunch_addition_oil(int lunch_addition_oil) {
        this.lunch_addition_oil = lunch_addition_oil;
    }

    public int getDinner_oil() {
        return dinner_oil;
    }

    public void setDinner_oil(int dinner_oil) {
        this.dinner_oil = dinner_oil;
    }

    public int getDinner_addition_oil() {
        return dinner_addition_oil;
    }

    public void setDinner_addition_oil(int dinner_addition_oil) {
        this.dinner_addition_oil = dinner_addition_oil;
    }

    public int getBreakfast_nut() {
        return breakfast_nut;
    }

    public void setBreakfast_nut(int breakfast_nut) {
        this.breakfast_nut = breakfast_nut;
    }

    public int getBreakfast_addition_nut() {
        return breakfast_addition_nut;
    }

    public void setBreakfast_addition_nut(int breakfast_addition_nut) {
        this.breakfast_addition_nut = breakfast_addition_nut;
    }

    public int getLunch_nut() {
        return lunch_nut;
    }

    public void setLunch_nut(int lunch_nut) {
        this.lunch_nut = lunch_nut;
    }

    public int getLunch_addition_nut() {
        return lunch_addition_nut;
    }

    public void setLunch_addition_nut(int lunch_addition_nut) {
        this.lunch_addition_nut = lunch_addition_nut;
    }

    public int getDinner_nut() {
        return dinner_nut;
    }

    public void setDinner_nut(int dinner_nut) {
        this.dinner_nut = dinner_nut;
    }

    public int getDinner_addition_nut() {
        return dinner_addition_nut;
    }

    public void setDinner_addition_nut(int dinner_addition_nut) {
        this.dinner_addition_nut = dinner_addition_nut;
    }

    public String getFoodProhibited() {
        return foodProhibited;
    }

    public void setFoodProhibited(String foodProhibited) {
        this.foodProhibited = foodProhibited;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(String checkTime) {
        this.checkTime = checkTime;
    }

    public String getFoodRecommend() {
        return foodRecommend;
    }

    public void setFoodRecommend(String foodRecommend) {
        this.foodRecommend = foodRecommend;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public int getFood_addition() {
        return food_addition;
    }

    public void setFood_addition(int food_addition) {
        this.food_addition = food_addition;
    }

    public boolean isAuto_dinner() {
        return auto_dinner;
    }

    public void setAuto_dinner(boolean auto_dinner) {
        this.auto_dinner = auto_dinner;
    }

    public boolean isNeed_print() {
        return need_print;
    }

    public void setNeed_print(boolean need_print) {
        this.need_print = need_print;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "nourishmentPlanId='" + nourishmentPlanId + '\'' +
                ", TargetL='" + TargetL + '\'' +
                ", Target='" + Target + '\'' +
                ", targetWeight='" + targetWeight + '\'' +
                ", strengthFactor='" + strengthFactor + '\'' +
                ", calorie='" + calorie + '\'' +
                ", foodExchange='" + foodExchange + '\'' +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", bmi='" + bmi + '\'' +
                ", CHO=" + CHO +
                ", PR=" + PR +
                ", fat=" + fat +
                ", vegetable=" + vegetable +
                ", fruit=" + fruit +
                ", bread=" + bread +
                ", bean=" + bean +
                ", milk=" + milk +
                ", meat=" + meat +
                ", oil=" + oil +
                ", nut=" + nut +
                ", assignment='" + assignment + '\'' +
                ", breakfast_plan=" + breakfast_plan +
                ", breakfast_addition_plan=" + breakfast_addition_plan +
                ", lunch_plan=" + lunch_plan +
                ", lunch_addition_plan=" + lunch_addition_plan +
                ", dinner_plan=" + dinner_plan +
                ", dinner_addition_plan=" + dinner_addition_plan +
                ", breakfast_vegetable=" + breakfast_vegetable +
                ", breakfast_addition_vegetable=" + breakfast_addition_vegetable +
                ", lunch_vegetable=" + lunch_vegetable +
                ", lunch_addition_vegetable=" + lunch_addition_vegetable +
                ", dinner_vegetable=" + dinner_vegetable +
                ", dinner_addition_vegetable=" + dinner_addition_vegetable +
                ", breakfast_fruit=" + breakfast_fruit +
                ", breakfast_addition_fruit=" + breakfast_addition_fruit +
                ", lunch_fruit=" + lunch_fruit +
                ", lunch_addition_fruit=" + lunch_addition_fruit +
                ", dinner_fruit=" + dinner_fruit +
                ", dinner_addition_fruit=" + dinner_addition_fruit +
                ", breakfast_bread=" + breakfast_bread +
                ", breakfast_addition_bread=" + breakfast_addition_bread +
                ", lunch_bread=" + lunch_bread +
                ", lunch_addition_bread=" + lunch_addition_bread +
                ", dinner_bread=" + dinner_bread +
                ", dinner_addition_bread=" + dinner_addition_bread +
                ", breakfast_bean=" + breakfast_bean +
                ", breakfast_addition_bean=" + breakfast_addition_bean +
                ", lunch_bean=" + lunch_bean +
                ", lunch_addition_bean=" + lunch_addition_bean +
                ", dinner_bean=" + dinner_bean +
                ", dinner_addition_bean=" + dinner_addition_bean +
                ", breakfast_milk=" + breakfast_milk +
                ", breakfast_addition_milk=" + breakfast_addition_milk +
                ", lunch_milk=" + lunch_milk +
                ", lunch_addition_milk=" + lunch_addition_milk +
                ", dinner_milk=" + dinner_milk +
                ", dinner_addition_milk=" + dinner_addition_milk +
                ", breakfast_meat=" + breakfast_meat +
                ", breakfast_addition_meat=" + breakfast_addition_meat +
                ", lunch_meat=" + lunch_meat +
                ", lunch_addition_meat=" + lunch_addition_meat +
                ", dinner_meat=" + dinner_meat +
                ", dinner_addition_meat=" + dinner_addition_meat +
                ", breakfast_oil=" + breakfast_oil +
                ", breakfast_addition_oil=" + breakfast_addition_oil +
                ", lunch_oil=" + lunch_oil +
                ", lunch_addition_oil=" + lunch_addition_oil +
                ", dinner_oil=" + dinner_oil +
                ", dinner_addition_oil=" + dinner_addition_oil +
                ", breakfast_nut=" + breakfast_nut +
                ", breakfast_addition_nut=" + breakfast_addition_nut +
                ", lunch_nut=" + lunch_nut +
                ", lunch_addition_nut=" + lunch_addition_nut +
                ", dinner_nut=" + dinner_nut +
                ", dinner_addition_nut=" + dinner_addition_nut +
                ", foodProhibited='" + foodProhibited + '\'' +
                ", remark='" + remark + '\'' +
                ", checkTime='" + checkTime + '\'' +
                ", foodRecommend='" + foodRecommend + '\'' +
                ", template_id='" + template_id + '\'' +
                ", food_addition=" + food_addition +
                ", auto_dinner=" + auto_dinner +
                ", need_print=" + need_print +
                '}';
    }
}
