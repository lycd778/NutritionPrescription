package com.easyhealth365.nutritionprescription.ui.guider;

import com.easyhealth365.nutritionprescription.base.BasePresenter;
import com.easyhealth365.nutritionprescription.base.BaseView;

/**
 * Created by lingxiao-Ching on 2017/7/17.
 */

public interface GuiderContract {
    interface View extends BaseView {
        void showProgress();
        void hideProgress();
        void showError(String error);
        void navigateToMain();
        void showNoplan();
    }
    interface Presenter extends BasePresenter {
        void loadPlanlist(String patientId,String access_token,String hospital_url);
        void onDestroy();
    }
}
