package com.easyhealth365.nutritionprescription.ui.reset_password;

import com.easyhealth365.nutritionprescription.base.BasePresenter;
import com.easyhealth365.nutritionprescription.base.BaseView;

/**
 * Created by lingxiao-Ching on 2017/7/24.
 */

public interface ResetPasswordContract{
    interface View extends BaseView {
        void showProgress();
        void hideProgress();
        void showError(String error);
        void navigateToLogin();
    }
    interface Presenter extends BasePresenter {
        void resetPassword(String username, String oldpassword, String newpassword);
        void onDestroy();
    }

}
