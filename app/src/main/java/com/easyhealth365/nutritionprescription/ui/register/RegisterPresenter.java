package com.easyhealth365.nutritionprescription.ui.register;

import com.easyhealth365.nutritionprescription.api.ApiService;
import com.easyhealth365.nutritionprescription.beans.CheckPhone;
import com.easyhealth365.nutritionprescription.utils.TLog;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lingxiao-Ching on 2017/7/12.
 */

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View registerView;
    public RegisterPresenter(RegisterContract.View registerView) {
        this.registerView = registerView;
    }

    @Override
    public void register(String telephone, String password) {
        registerView.showProgress();
        Flowable<CheckPhone> checkPhoneFlowable = ApiService.checkPhone(telephone, password);
        checkPhoneFlowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CheckPhone>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(CheckPhone checkPhone) {
                        TLog.log("status: " + checkPhone.getStatus() + " message: " + checkPhone.getMessage());
                        if (103==checkPhone.getStatus()){
                            registerView.showResult(checkPhone.getMessage());
                        }else if(104==checkPhone.getStatus()){
                            registerView.navigateToUpdateUser();
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
    public void start() {

    }

    @Override
    public void onDestroy() {
        TLog.log("-->RegisterPresenter  onDestroy");
    }
}
