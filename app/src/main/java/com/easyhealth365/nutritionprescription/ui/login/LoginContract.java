package com.easyhealth365.nutritionprescription.ui.login;

import com.easyhealth365.nutritionprescription.base.BasePresenter;
import com.easyhealth365.nutritionprescription.base.BaseView;
import com.easyhealth365.nutritionprescription.beans.User;

/**
 * Created by lingxiao-Ching on 2017/6/29.
 */

public interface LoginContract {
    interface View extends BaseView {
        void showProgress();
        void hideProgress();
        void showError(String error);
        void navigateToMain();
        void navigateToRegister();
    }

    interface Presenter extends BasePresenter {
        void login(String username, String password);
        void onDestroy();
    }

    interface Model{
        void saveUserInfo(User user);
        void saveLoginState(Boolean isLogin);
        void saveRememberPass(User user);

    }
}
