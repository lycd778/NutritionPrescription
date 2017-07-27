package com.easyhealth365.nutritionprescription.ui.find_password;

import com.easyhealth365.nutritionprescription.api.ApiService;
import com.easyhealth365.nutritionprescription.beans.NormalResult;
import com.easyhealth365.nutritionprescription.beans.UpdatePasswordResult;
import com.easyhealth365.nutritionprescription.utils.TLog;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by lingxiao-Ching on 2017/7/25.
 */

public class FindPasswordPresenter implements FindPasswordContract.Presenter {
    private FindPasswordContract.View findPasswordView;

    public FindPasswordPresenter(FindPasswordContract.View findPasswordView) {
        this.findPasswordView = findPasswordView;
    }


    @Override
    public void start() {

    }

    @Override
    public void findPassword(String mbtelephone, int type, String telephone, String password, boolean istelephonecheck) {
        findPasswordView.showProgress();
        Flowable<UpdatePasswordResult> findPasswordFlowable = ApiService.findPassword(mbtelephone, type, telephone, password, istelephonecheck);
        findPasswordFlowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UpdatePasswordResult>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(UpdatePasswordResult updatePasswordResult) {
                        TLog.log("status: " + updatePasswordResult.toString());
                        if (100 == updatePasswordResult.getStatus()) {
                            findPasswordView.navigateToLogin();
                        }
                    }
                    @Override
                    public void onError(Throwable t) {
                        findPasswordView.hideProgress();
                        TLog.log("error: " + t.getMessage());
                        findPasswordView.showError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        findPasswordView.hideProgress();
                    }
                });
    }

    @Override
    public void checkedPhone(String phoneNum, String password) {
        findPasswordView.showProgress();
        Flowable<NormalResult> checkPhoneFlowable = ApiService.checkPhone(phoneNum, password);
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
                        findPasswordView.SendMessage(checkPhone.getStatus());
                    }

                    @Override
                    public void onError(Throwable t) {
                        findPasswordView.hideProgress();
                        findPasswordView.showError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        findPasswordView.hideProgress();
                    }
                });
    }

    @Override
    public void onDestroy() {

    }
}
