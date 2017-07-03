package com.easyhealth365.nutritionprescription.ui.mian;

/**
 * Created by Administrator on 2016/7/7.
 */
public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mainView;

    public MainPresenter(MainContract.View mainView) {
        this.mainView=mainView;
    }


    @Override
    public void start() {
        mainView.initTabHost();
    }

    @Override
    public void onDestroy() {
        if (mainView!=null){
            mainView=null;
        }
    }
}
