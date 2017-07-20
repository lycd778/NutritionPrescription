package com.easyhealth365.nutritionprescription.ui.food;

import com.easyhealth365.nutritionprescription.base.BasePresenter;
import com.easyhealth365.nutritionprescription.base.BaseView;
import com.easyhealth365.nutritionprescription.beans.PreRecord;
import com.easyhealth365.nutritionprescription.beans.Record;

/**
 * Created by lingxiao-Ching on 2017/7/20.
 */

public interface FoodContract {
    interface View extends BaseView {
        void showLoading();
        void dismissLoading();
        void showError(String error);
        void reLoadActualRecord(PreRecord preRecord);
        void showUpdateResult(int state);
    }

    interface Presenter extends BasePresenter {
        void updateRecord(Record recored, String patientId, String access_token, String hospital_url);
        void getPreRecord(String checkTime, String patientId, String access_token, String hospital_url);
        void onDestroy();
    }
}
