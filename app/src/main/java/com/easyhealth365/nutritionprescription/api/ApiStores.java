package com.easyhealth365.nutritionprescription.api;

import com.easyhealth365.nutritionprescription.beans.CheckPhone;
import com.easyhealth365.nutritionprescription.beans.Plan;
import com.easyhealth365.nutritionprescription.beans.PlanID;
import com.easyhealth365.nutritionprescription.beans.PreRecord;
import com.easyhealth365.nutritionprescription.beans.Record;
import com.easyhealth365.nutritionprescription.beans.RegisterUser;
import com.easyhealth365.nutritionprescription.beans.UpdateRecordResult;
import com.easyhealth365.nutritionprescription.beans.User;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by lingxiao-Ching on 2017/6/29.
 */

interface ApiStores {

    /**
     * 登录返回(json post)
     */
    @FormUrlEncoded
    @POST("api/user/login")
    Flowable<User> userLogin(@Field("username") String username, @Field("password") String password);


    /**
     * 获取处方列表(json get)
     */
    @GET
    Flowable<List<PlanID>> getPlanID(@Url String url, @Query("access_token") String access_token);

    /**
     * 获取处方(json get)
     */
    @GET
    Flowable<Plan> getPlan(@Url String url, @Query("access_token") String access_token);

    /**
     * 验证手机是否存在
     */
    @GET("api/user/exist")
    Flowable<CheckPhone> checkPhone(@Query("telephone") String telephone,
                                    @Query("password") String password,
                                    @Query("realname") String realname,
                                    @Query("gender") String gender);

    /**
     * 注册返回(json post)
     */
    @POST("api/user/reg")
    Flowable<ResponseBody> register(@Body RegisterUser registerUser);

    /**
     * 上传处方执行记录返回(json post)
     */
    @POST()
    Flowable<UpdateRecordResult> updateRecord(@Url String url, @Query("access_token") String access_token, @Body Record record);
    /**
     * 获取往期处方执行记录返回(json get)
     */
    @GET()
    Flowable<PreRecord> getPreRecord(@Url String url, @Query("access_token") String access_token);

    /**
     * 重置密码返回(json post)
     */
    @POST("api/user/Updatep")
    Flowable<ResponseBody> resetPassword(@Query("userid") String userid,
                                         @Query("oldpassword") String oldpassword,
                                         @Query("newpassword") String newpassword);
}






