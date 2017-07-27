package com.easyhealth365.nutritionprescription.ui.register;

import com.easyhealth365.nutritionprescription.api.ApiService;
import com.easyhealth365.nutritionprescription.beans.NormalResult;
import com.easyhealth365.nutritionprescription.beans.RegisterUser;
import com.easyhealth365.nutritionprescription.utils.TLog;
import com.easyhealth365.nutritionprescription.utils.ToastUtil;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.io.IOException;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * Created by lingxiao-Ching on 2017/7/12.
 */

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View registerView;

    public RegisterPresenter(RegisterContract.View registerView) {
        this.registerView = registerView;
    }

    @Override
    public void checkPhone(String telephone, String password) {
        registerView.showProgress();
        Flowable<NormalResult> checkPhoneFlowable = ApiService.checkPhone(telephone, password);
        checkPhoneFlowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NormalResult>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(NormalResult checkPhone) {
                        TLog.log("status: " + checkPhone.getStatus() + " message: " + checkPhone.getMessage());
                        if (103 == checkPhone.getStatus()) {
                            registerView.checkPhoneResult(checkPhone.getMessage());
                        } else if (104 == checkPhone.getStatus()) {
                            registerView.updateReUser();
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        registerView.hideProgress();
                        registerView.showError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        registerView.hideProgress();
                    }
                });
    }

    @Override
    public void register(RegisterUser reUser) {
        registerView.showProgress();
        Flowable<NormalResult> registerFlowable = ApiService.register(reUser);
        registerFlowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NormalResult>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(NormalResult normalResult) {
                        TLog.log("register_result: " + normalResult.toString());
                        if (100==normalResult.getStatus()){
                            registerView.navigateToLogin(normalResult.getMessage());
                        }else {
                            registerView.registerResult(normalResult.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        registerView.hideProgress();
                        TLog.log("error: " + t.getMessage());
                        registerView.showError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        registerView.hideProgress();
                    }
                });
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {
        TLog.log("-->RegisterPresenter  onDestroy");
    }
}
