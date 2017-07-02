package com.easyhealth365.nutritionprescription.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.easyhealth365.nutritionprescription.R;
import com.easyhealth365.nutritionprescription.base.BaseActivity;
import com.easyhealth365.nutritionprescription.base.BaseApplication;
import com.easyhealth365.nutritionprescription.ui.register.RegisterActivity;
import com.easyhealth365.nutritionprescription.utils.TLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginContract.Presenter> implements LoginContract.View {
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.cb_password)
    CheckBox cb_password;
    @BindView(R.id.tv_find_password)
    TextView tv_find_password;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.btn_register)
    Button btn_register;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter = new LoginPresenter(this);
        mPresenter.start();
    }

    public void setPresenter(LoginPresenter presenter) {
        this.mPresenter = presenter;
    }
    @Override
    public void navigateToMain() {

    }

    @Override
    public void navigateToRegister() {
        Intent intent =new Intent(getBaseContext(),RegisterActivity.class);
        startActivity(intent);
    }

    @OnClick({R.id.tv_find_password, R.id.btn_login, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_find_password:
                finish();
                break;
            case R.id.btn_login:
                TLog.log("执行登录");
                mPresenter.login(et_password.getText().toString(),et_username.getText().toString());
                break;
            case R.id.btn_register:
                navigateToRegister();
                break;
            default:
                break;
        }
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

