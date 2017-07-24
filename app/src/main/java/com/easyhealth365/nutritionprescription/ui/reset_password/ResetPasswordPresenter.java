package com.easyhealth365.nutritionprescription.ui.reset_password;

import com.easyhealth365.nutritionprescription.api.ApiService;
import com.easyhealth365.nutritionprescription.utils.SharedPreferenceUtil;
import com.easyhealth365.nutritionprescription.utils.TLog;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by lingxiao-Ching on 2017/7/24.
 */

public class ResetPasswordPresenter implements ResetPasswordContract.Presenter {
    private ResetPasswordContract.View resetPasswordView;
    public ResetPasswordPresenter(ResetPasswordContract.View resetPasswordView) {
        this.resetPasswordView = resetPasswordView;
    }
    SharedPreferenceUtil spUtils = SharedPreferenceUtil.getInstance();

    @Override
    public void start() {

    }

    @Override
    public void resetPassword(String username, String oldpassword, String newpassword) {
        resetPasswordView.showProgress();
        Flowable<ResponseBody> resetPasswordFlowable = ApiService.resetPassword(username,oldpassword,newpassword);
        resetPasswordFlowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {

                        try {
                            TLog.log("successs: "+responseBody.string());

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(Throwable t) {
                        resetPasswordView.hideProgress();
                        TLog.log("error: "+t.getMessage());
                        resetPasswordView.showError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        resetPasswordView.hideProgress();
                    }
                });
    }

    @Override
    public void onDestroy() {

    }
}
