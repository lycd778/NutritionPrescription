package com.easyhealth365.nutritionprescription.api;
import com.easyhealth365.nutritionprescription.beans.PlanList;
import com.easyhealth365.nutritionprescription.beans.User;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by lingxiao-Ching on 2017/6/29.
 */

interface ApiStores {

/**
 * 登录返回(json post)
 * */
    @FormUrlEncoded
    @POST("api/user/login")
    Flowable<User> userLogin(@Field("username") String username, @Field("password") String password);


/**
 * 获取处方列表(json get)
 * */
    @GET("/api/nourishmentPlan/GetList/{patientId}?access_token={access_token}")
    Flowable<PlanList> getPlanList(@Path("patientId") String patientId,@Path("access_token") String access_token);
}




