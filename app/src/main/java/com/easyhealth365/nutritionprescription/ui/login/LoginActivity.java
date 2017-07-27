package com.easyhealth365.nutritionprescription.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.easyhealth365.nutritionprescription.R;
import com.easyhealth365.nutritionprescription.base.BaseActivity;
import com.easyhealth365.nutritionprescription.base.BaseApplication;
import com.easyhealth365.nutritionprescription.ui.find_password.FindPasswordActicity;
import com.easyhealth365.nutritionprescription.ui.mian.MainActivity;
import com.easyhealth365.nutritionprescription.ui.register.RegisterActivity;
import com.easyhealth365.nutritionprescription.utils.SharedPreferenceUtil;
import com.easyhealth365.nutritionprescription.utils.TLog;
import com.easyhealth365.nutritionprescription.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
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
    SharedPreferenceUtil spUtils=SharedPreferenceUtil.getInstance();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter = new LoginPresenter(this);
        mPresenter.start();
        init();

    }

    public void setPresenter(LoginPresenter presenter) {
        this.mPresenter = presenter;
    }
    @Override
    public void init() {
        et_username.setText(spUtils.getUsername());
        if (spUtils.getIsRemeber()){
            cb_password.setChecked(true);
            et_password.setText(spUtils.getPassword());
        }else {
            cb_password.setChecked(false);
        }
    }
    @Override
    public void navigateToMain() {
        Intent intent =new Intent(getBaseContext(),MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void navigateToRegister() {
        Intent intent =new Intent(getBaseContext(),RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToFindPassword() {
        Intent intent =new Intent(getBaseContext(),FindPasswordActicity.class);
        startActivity(intent);
    }

    @OnClick({R.id.tv_find_password, R.id.btn_login, R.id.btn_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_find_password:
                navigateToFindPassword();
                break;
            case R.id.btn_login:
                TLog.log("执行登录");
                if (cb_password.isChecked()){
                    spUtils.setIsRemeber(true);
                    spUtils.setUsername(et_username.getText().toString().trim());
                    spUtils.setPassword(et_password.getText().toString().trim());
                }else {
                    spUtils.setIsRemeber(false);
                    spUtils.setUsername(et_username.getText().toString().trim());
                    spUtils.setPassword(null);
                }
                mPresenter.login(et_username.getText().toString(),et_password.getText().toString());
                break;
            case R.id.btn_register:
                navigateToRegister();
                break;
            default:
                break;
        }
    }

@OnCheckedChanged(R.id.cb_password)
public void onCheckedChanged(CompoundButton buttonView, boolean isChecked){
    if (!isChecked) {
        ToastUtil.showShort(getApplicationContext(), "不记住密码");
    } else {
        ToastUtil.showShort(getApplicationContext(), "记住密码");
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

