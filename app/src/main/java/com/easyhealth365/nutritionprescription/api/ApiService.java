package com.easyhealth365.nutritionprescription.api;
import com.easyhealth365.nutritionprescription.BuildConfig;
import com.easyhealth365.nutritionprescription.beans.PlanList;
import com.easyhealth365.nutritionprescription.beans.User;

import io.reactivex.Flowable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by lingxiao-Ching on 2017/6/29.
 */

public class ApiService {
    public static final String URL_HOST ="http://123.57.143.76:8090/" ;//服务器端口
    /**
     * 基础地址
     * 初始化 retroft
     */
    public static Retrofit mRetrofit;

    public static Retrofit retrofit(String url) {
        if (mRetrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            if (BuildConfig.DEBUG) {
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置 Debug Log 模式
                builder.addInterceptor(loggingInterceptor);
            }
            OkHttpClient okHttpClient = builder.build();
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }

    /**
     * 登录,返回,用的是json格式的post
     *
     *
     */
    public static Flowable<User> userLogin(String username, String password) {
        ApiStores apiStores = retrofit(URL_HOST).create(ApiStores.class);
        Flowable<User> userFlo=apiStores.userLogin(username,password);
        return  userFlo;
    }

    /**
     * 获取处方列表,返回,用的是json格式的get
     *
     *
     */
    public static Flowable<PlanList> getPlanList(String patientId, String access_token,String hospital_url) {
        ApiStores apiStores = retrofit(hospital_url).create(ApiStores.class);
        Flowable<PlanList> planlistFlo=apiStores.getPlanList(patientId,access_token);
        return  planlistFlo;
    }

}
