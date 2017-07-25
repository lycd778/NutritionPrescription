package com.easyhealth365.nutritionprescription.ui.login;
import com.easyhealth365.nutritionprescription.api.ApiService;
import com.easyhealth365.nutritionprescription.beans.User;
import com.easyhealth365.nutritionprescription.utils.SharedPreferenceUtil;
import com.easyhealth365.nutritionprescription.utils.TLog;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by lingxiao-Ching on 2017/6/29.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View loginView;
    public LoginPresenter(LoginContract.View loginView) {
        this.loginView = loginView;
    }
    SharedPreferenceUtil spUtils=SharedPreferenceUtil.getInstance();
    @Override
    public void login(String username, String password) {
        loginView.showProgress();
          Flowable<User> userFlowable = ApiService.userLogin(username, password);
          userFlowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        s.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(User user) {
                        loginView.hideProgress();
                        TLog.log("status: "+user.getStatus()+" message: "+user.getMessage());
                        if (103==user.getStatus()){
                            spUtils.setUsername(user.getResults().getTelephone());
                            spUtils.putUser(user);
                            spUtils.setIsLogin(true);
                            loginView.init();
                            loginView.navigateToMain();
                        }else{
                            loginView.showError(user.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        loginView.hideProgress();
                        TLog.log("error: "+t.getMessage());
                        loginView.showError(t.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        loginView.hideProgress();
                    }
                });
    }

    @Override
    public void start() {

    }
    @Override
    public void onDestroy() {
        TLog.log("-->loginPresenter  onDestroy");
    }
}
