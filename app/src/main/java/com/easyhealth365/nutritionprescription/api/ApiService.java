package com.easyhealth365.nutritionprescription.api;

import com.easyhealth365.nutritionprescription.beans.NormalResult;
import com.easyhealth365.nutritionprescription.beans.Plan;
import com.easyhealth365.nutritionprescription.beans.PlanID;
import com.easyhealth365.nutritionprescription.beans.PreRecord;
import com.easyhealth365.nutritionprescription.beans.Record;
import com.easyhealth365.nutritionprescription.beans.RegisterUser;
import com.easyhealth365.nutritionprescription.beans.UpdatePasswordResult;
import com.easyhealth365.nutritionprescription.beans.UpdateRecordResult;
import com.easyhealth365.nutritionprescription.beans.User;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lingxiao-Ching on 2017/6/29.
 */

public class ApiService {
    public static final String URL_HOST = "http://123.57.143.76:8090/";//服务器端口
    /**
     * 基础地址
     * 初始化 retroft
     */
//    public static Retrofit mRetrofit;
//
//    public static Retrofit retrofit() {
//        if (mRetrofit == null) {
//            OkHttpClient.Builder builder = new OkHttpClient.Builder();
//            if (BuildConfig.DEBUG) {
//                // Log信息拦截器
//                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//                //设置 Debug Log 模式
//                builder.addInterceptor(loggingInterceptor);
//            }
//            OkHttpClient okHttpClient = builder
//                    .connectTimeout(20, TimeUnit.SECONDS)
//                    .readTimeout(20, TimeUnit.SECONDS)
//                    .build();
//            mRetrofit = new Retrofit.Builder()
//                    .baseUrl(URL_HOST)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                    .client(okHttpClient)
//                    .build();
//        }
//        return mRetrofit;
//    }

    private static final Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl(URL_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 使用RxJava作为回调适配器
            .build();
    //    private static final ApiStores apiStores = retrofit().create(ApiStores.class);
    private static final ApiStores apiStores = mRetrofit.create(ApiStores.class);

    /**
     * 登录,返回,用的是json格式的post
     */
    public static Flowable<User> userLogin(String username, String password) {
        return apiStores.userLogin(username, password);
    }

    /**
     * 获取处方列表,返回,用的是json格式的get
     */
    public static Flowable<List<PlanID>> getPlanList(String patientId, String access_token, String hospital_url) {
        String url = hospital_url + "api/nourishmentPlan/GetList/" + patientId + "?";
        return apiStores.getPlanID(url, access_token);
    }

    /**
     * 获取处方列表,返回,用的是json格式的get
     */
    public static Flowable<Plan> getPlan(String nid, String access_token, String hospital_url) {
        String url = hospital_url + "api/nourishmentPlan/Get/" + nid + "?";
        return apiStores.getPlan(url, access_token);
    }

    /**
     * 验证手机号,返回,用的是json格式的get
     */
    public static Flowable<NormalResult> checkPhone(String phoneNum, String password) {
        return apiStores.checkPhone(phoneNum, password, "xxxx", "true");
    }

    /**
     * 注册用户,返回,用的是json格式的post
     */
    public static Flowable<NormalResult> register(RegisterUser registerUser) {
        return apiStores.register(registerUser);
    }

    /**
     * 上传处方执行记录,返回,用的是json格式的post
     */
    public static Flowable<UpdateRecordResult> updateRecord(Record record, String patientId, String access_token, String hospital_url) {
        String url = hospital_url + "/api/nourishmentPlanFeedback/Add/" + patientId + "?";
        return apiStores.updateRecord(url, access_token, record);
    }

    /**
     * 获取往期处方执行记录,返回,用的是json格式的get
     */
    public static Flowable<PreRecord> getPreRecord(String checkTime, String patientId, String access_token, String hospital_url) {
        String url = hospital_url + "/api/nourishmentPlanFeedback/Get/" + patientId + "/"+checkTime+"?";
        return apiStores.getPreRecord(url, access_token);
    }

    /**
     * 重置密码用户,返回,用的是json格式的post
     */
    public static Flowable<UpdatePasswordResult> resetPassword(String userid, String oldpassword, String newpassword) {
        return apiStores.resetPassword(userid,oldpassword,newpassword);
    }
    /**
     * 找回密码用户,返回,用的是json格式的post
     */
    public static Flowable<UpdatePasswordResult> findPassword(String mbtelephone, int type, String telephone, String password, boolean istelephonecheck) {
        return apiStores.findPassword(mbtelephone,type,telephone,password,istelephonecheck);
    }

}
