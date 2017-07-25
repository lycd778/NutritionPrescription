package com.easyhealth365.nutritionprescription.ui.find_password;

/**
 * Created by lingxiao-Ching on 2017/7/25.
 */

public class FindPasswordPresenter implements FindPasswordContract.Presenter{
    private FindPasswordContract.View findPasswordView;

    public FindPasswordPresenter(FindPasswordContract.View findPasswordView) {
        this.findPasswordView = findPasswordView;
    }


    @Override
    public void start() {

    }

    @Override
    public void findPassword(String mbtelephone, int type, String telephone, String password, boolean istelephonecheck) {

    }

    @Override
    public void onDestroy() {

    }
}
