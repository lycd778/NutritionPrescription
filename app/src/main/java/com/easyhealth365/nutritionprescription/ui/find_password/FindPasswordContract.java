package com.easyhealth365.nutritionprescription.ui.find_password;

import com.easyhealth365.nutritionprescription.base.BasePresenter;
import com.easyhealth365.nutritionprescription.base.BaseView;

/**
 * Created by lingxiao-Ching on 2017/7/25.
 */

public interface FindPasswordContract{
    interface View extends BaseView {
        void showProgress();
        void hideProgress();
        void showError(String error);
        void navigateToLogin();
        void SendMessage(int status);
    }
    interface Presenter extends BasePresenter {
        void findPassword(String mbtelephone,int type, String telephone,String password,boolean istelephonecheck);
        void checkedPhone(String phoneNum, String password);
        void onDestroy();
    }
}
