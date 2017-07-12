package com.easyhealth365.nutritionprescription.ui.register;

import com.easyhealth365.nutritionprescription.base.BasePresenter;
import com.easyhealth365.nutritionprescription.base.BaseView;

/**
 * Created by lingxiao-Ching on 2017/7/12.
 */

public interface RegisterContract {
    interface View extends BaseView {
        void showProgress();

        void hideProgress();

        void showError(String error);

        void navigateToUpdateUser();
        void showResult(String message);
    }

    interface Presenter extends BasePresenter {
        void register(String telephone, String password);
        void onDestroy();
    }
}
