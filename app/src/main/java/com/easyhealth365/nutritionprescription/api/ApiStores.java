package com.easyhealth365.nutritionprescription.api;
import com.easyhealth365.nutritionprescription.beans.User;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by lingxiao-Ching on 2017/6/29.
 */

public interface ApiStores {
/**
 * 登录返回(json post)
 * */
    @FormUrlEncoded
    @POST("api/user/login")
    Flowable<User> userLogin(@Field("username") String username, @Field("password") String password);
}
