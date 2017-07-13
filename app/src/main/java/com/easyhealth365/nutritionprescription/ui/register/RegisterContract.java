package com.easyhealth365.nutritionprescription.ui.register;

import com.easyhealth365.nutritionprescription.base.BasePresenter;
import com.easyhealth365.nutritionprescription.base.BaseView;
import com.easyhealth365.nutritionprescription.beans.RegisterUser;

/**
 * Created by lingxiao-Ching on 2017/7/12.
 */

public interface RegisterContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showError(String error);
        void navigateToLogin();
        void updateReUser();
        void showResult(String message);
    }

    interface Presenter extends BasePresenter {
        void checkPhone(String telephone, String password);
        void register(RegisterUser reUser);
        void onDestroy();
    }
}
