package com.easyhealth365.nutritionprescription.ui.guider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.easyhealth365.nutritionprescription.R;
import com.easyhealth365.nutritionprescription.base.BaseActivity;
import com.easyhealth365.nutritionprescription.base.BaseApplication;
import com.easyhealth365.nutritionprescription.beans.User;
import com.easyhealth365.nutritionprescription.ui.mian.MainActivity;
import com.easyhealth365.nutritionprescription.utils.SharedPreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lingxiao-Ching on 2017/7/17.
 */

public class GuiderActicity extends BaseActivity<GuiderContract.Presenter> implements GuiderContract.View {
    SharedPreferenceUtil spUtils = SharedPreferenceUtil.getInstance();
    @BindView(R.id.guider_noplan)
    LinearLayout guider_noplan;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guider);
        ButterKnife.bind(this);
        mPresenter = new GuiderPresenter(this);
        mPresenter.start();
        User user = spUtils.getUser();
        mPresenter.loadPlanlist(user.getResults().getUserid(), user.getResults().getAccess_token(), user.getResults().getHospitalBaseUrl());
    }
    @Override
    public void navigateToMain() {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void showNoplan() {
        guider_noplan.setVisibility(View.VISIBLE);
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showError(String error) {
        BaseApplication.showShortToast(error);
    }


}
