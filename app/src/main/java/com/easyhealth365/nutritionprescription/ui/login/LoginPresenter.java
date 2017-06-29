package com.easyhealth365.nutritionprescription.ui.login;

import com.easyhealth365.nutritionprescription.api.ApiService;
import com.easyhealth365.nutritionprescription.beans.User;
import com.easyhealth365.nutritionprescription.utils.ILog;

import io.reactivex.android.schedulers.AndroidSchedulers;
import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by lingxiao-Ching on 2017/6/29.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View loginView;
    public LoginPresenter(LoginContract.View loginView) {
        this.loginView = loginView;
    }
    @Override
    public void login(String username, String password) {
        loginView.showProgress();
        Observable<User> userObservable = ApiService.userLogin(username, password);
        userObservable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                        loginView.hideProgress();
                    }

                    @Override
                    public void onError(Throwable e) {
                        ILog.log(e.getMessage().toString());
                        loginView.hideProgress();
                        loginView.showError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(User getIpInfoResponse) {
                        ILog.log(getIpInfoResponse.toString());
                        loginView.navigateToMain();
                    }
                });
    }

    @Override
    public void start() {

    }
    @Override
    public void onDestroy() {

    }
}
