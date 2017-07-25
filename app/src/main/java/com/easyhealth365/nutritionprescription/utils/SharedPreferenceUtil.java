package com.easyhealth365.nutritionprescription.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.easyhealth365.nutritionprescription.base.BaseApplication;
import com.easyhealth365.nutritionprescription.beans.Plan;
import com.easyhealth365.nutritionprescription.beans.Record;
import com.easyhealth365.nutritionprescription.beans.RegisterUser;
import com.easyhealth365.nutritionprescription.beans.User;

import java.io.IOException;
import java.io.StreamCorruptedException;


public class SharedPreferenceUtil {

    // 用户名key
    public final static String USER = "USER";
    public final static String RE_USER = "RE_USER";
    public final static String PLAN = "PLAN";
    public final static String RECORD = "RECORD";
    public final static String WEIGHT = "WEIGHT";
    public final static String KEY_Remeber = "KEY_Remeber";
    public final static String KEY_LOGIN = "KEY_LOGIN";
    public final static String KEY_LEVEL = "KEY_LEVEL";
    public final static String KEY_DELIVERY = "KEY_DELIVERY";
    public final static String HAVE_PLAN = "HAVE_PLAN";
    private static SharedPreferenceUtil spUtils;
    private static User spUser = null;
    private static RegisterUser spReUser = null;
    private static Plan spPlan = null;
    private static Record spRecord = null;
    private SharedPreferences sp;


    //

    /**
     * 初始化，一般在应用启动之后就要初始化
     *
     * @param context 此处的context要用application的全局上下文,
     *                避免static强类型一直持有activity的引用,造成内存泄露
     */
    public static synchronized void initSharedPreference(Context context) {
        if (spUtils == null) {
            spUtils = new SharedPreferenceUtil(context);
        }
    }


    /**
     * 获取唯一的instance
     *
     * @return
     */

    public static synchronized SharedPreferenceUtil getInstance() {
        if (spUtils == null) {
            spUtils = new SharedPreferenceUtil(BaseApplication.getInstance());
        }

        return spUtils;
    }

    public SharedPreferenceUtil(Context context) {
        sp = context.getSharedPreferences("SharedPreferenceUtil", Context.MODE_PRIVATE);
    }

    public SharedPreferences getSharedPref() {
        return sp;
    }

    public synchronized void setIsRemeber(Boolean isRemeber) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY_Remeber, isRemeber);
        editor.commit();
    }

    public synchronized Boolean getIsRemeber() {
        Boolean flag = sp.getBoolean(KEY_Remeber, false);
        return flag;
    }
    public synchronized void setHavePlan(Boolean havePlan) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(HAVE_PLAN, havePlan);
        editor.commit();
    }

    public synchronized Boolean getHavePlan() {
        Boolean flag = sp.getBoolean(HAVE_PLAN, false);
        return flag;
    }

    public synchronized void setIsLogin(Boolean AutoLogin) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(KEY_LOGIN, AutoLogin);
        editor.commit();
    }

    public synchronized Boolean getIsLogin() {
        Boolean flag = sp.getBoolean(KEY_LOGIN, false);
        return flag;
    }

    //记录用户名
    public void setUsername(String username) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("pre_username", username);
        editor.apply();
    }

    //读取用户名
    public String getUsername() {
        return sp.getString("pre_username", "");
    }

    //记录现体重
    public void setWeight(int weight) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(WEIGHT, weight);
        editor.apply();
    }

    //读取现体重
    public int getWeight() {
        return sp.getInt(WEIGHT,0);
    }

    //记录密码
    public void setPassword(String password) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("pre_password", password);
        editor.apply();
    }
    //读取密码
    public String getPassword() {
        return sp.getString("pre_password", "");
    }


    public synchronized void putUser(User user) {
        SharedPreferences.Editor editor = sp.edit();
        String str = "";
        try {
            str = SerializableUtil.objToStr(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.putString(USER, str);
        editor.commit();
        spUser = user;
    }

    public synchronized User getUser() {
        String str = sp.getString(SharedPreferenceUtil.USER, "");
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (spUser == null) {
            spUser = new User();
            //获取序列化的数据
            try {
                Object obj = SerializableUtil.strToObj(str);
                if (obj != null) {
                    spUser = (User) obj;
                    TLog.log("USER", "getuser" + spUser.toString());
                }
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return spUser;
    }


    public synchronized void DeleteUser() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(USER, "");
        editor.commit();
        spUser = null;
    }

    public synchronized void putReUser(RegisterUser reUser) {
        SharedPreferences.Editor editor = sp.edit();
        String str = "";
        try {
            str = SerializableUtil.objToStr(reUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.putString(RE_USER, str);
        editor.commit();
        spReUser = reUser;
    }

    public synchronized RegisterUser getReUser() {
        String str = sp.getString(SharedPreferenceUtil.RE_USER, "");
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (spReUser == null) {
            spReUser = new RegisterUser();
            //获取序列化的数据
            try {
                Object obj = SerializableUtil.strToObj(str);
                if (obj != null) {
                    spReUser = (RegisterUser) obj;
                    TLog.log("RegisterUser", "getReUser" + spReUser.toString());
                }
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return spReUser;
    }
    public synchronized void DeleteReUser() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(RE_USER, "");
        editor.commit();
        spReUser = null;
    }




    public synchronized void putPlan(Plan plan) {
        SharedPreferences.Editor editor = sp.edit();
        String str = "";
        try {
            str = SerializableUtil.objToStr(plan);
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.putString(PLAN, str);
        editor.commit();
        spPlan = plan;
    }

    public synchronized Plan getPlan() {
        String str = sp.getString(SharedPreferenceUtil.PLAN, "");
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (spPlan == null) {
            spPlan = new Plan();
            //获取序列化的数据
            try {
                Object obj = SerializableUtil.strToObj(str);
                if (obj != null) {
                    spPlan = (Plan) obj;
                    TLog.log("Plan", "getspPlan" + spPlan.toString());
                }
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return spPlan;
    }
    public synchronized void DeletePlan() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(PLAN, "");
        editor.commit();
        spPlan = null;
    }


    public synchronized void putRecord(Record recored) {
        SharedPreferences.Editor editor = sp.edit();
        String str = "";
        try {
            str = SerializableUtil.objToStr(recored);
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.putString(RECORD, str);
        editor.commit();
        spRecord = recored;
    }

    public synchronized Record getRecord() {
        String str = sp.getString(SharedPreferenceUtil.RECORD, "");
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (spRecord == null) {
            spRecord = new Record();
            //获取序列化的数据
            try {
                Object obj = SerializableUtil.strToObj(str);
                if (obj != null) {
                    spRecord = (Record) obj;
                    TLog.log("Record", "getspRecord" + spRecord.toString());
                }
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return spRecord;
    }
    public synchronized void DeleteRecord() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(RECORD, "");
        editor.commit();
        spRecord = null;
    }
//
//    public synchronized DeliveryMessage getDeliveryMessage() {
//        DeliveryMessage deliveryMessage = new DeliveryMessage();
//        //获取序列化的数据
//        String str = sp.getString(SharedPreferenceUtil.KEY_DELIVERY, "");
//        if (TextUtils.isEmpty(str)) {
//            return null;
//        }
//        try {
//            Object obj = SerializableUtil.strToObj(str);
//            if (obj != null) {
//                deliveryMessage = (DeliveryMessage) obj;
//            }
//        } catch (StreamCorruptedException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return deliveryMessage;
//
//    }
//
//    public synchronized void putDeliveryMessage(DeliveryMessage deliveryMessage) {
//        SharedPreferences.Editor editor = sp.edit();
//        String str = "";
//        try {
//            str = SerializableUtil.objToStr(deliveryMessage);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        editor.putString(KEY_DELIVERY, str);
//        editor.commit();
//    }


}
