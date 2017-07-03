package com.easyhealth365.nutritionprescription.ui.mian;

import com.easyhealth365.nutritionprescription.base.BasePresenter;
import com.easyhealth365.nutritionprescription.base.BaseView;

/**
 * Created by lingxiao-Ching on 2017/7/3.
 */

public interface MainContract {

    interface View extends BaseView {
        void initTabHost();
    }

    interface Presenter extends BasePresenter {

    }

}
