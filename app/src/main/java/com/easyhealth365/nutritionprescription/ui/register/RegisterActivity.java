package com.easyhealth365.nutritionprescription.ui.register;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.easyhealth365.nutritionprescription.R;
import com.easyhealth365.nutritionprescription.base.BaseActivity;
import com.easyhealth365.nutritionprescription.base.BaseApplication;
import com.easyhealth365.nutritionprescription.ui.updateUser.UpdateUserInfoActivity;
import com.easyhealth365.nutritionprescription.utils.TLog;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterContract.Presenter> implements Validator.ValidationListener, RegisterContract.View {
    @Required(order = 1, message = "用户名不能为空")
    @TextRule(order = 7, minLength = 11, maxLength = 11, message = "用户名格式错误")
    @BindView(R.id.et_re_username)
    EditText et_re_username;

    @Required(order = 2, message = "邮箱不能为空")
    @Email(order = 8, message = "邮件格式不正确")
    @BindView(R.id.et_re_email)
    EditText et_re_email;

    @Required(order = 3, message = "密码不能为空")
    @Password(order = 4)
    @TextRule(order = 9, minLength = 6, maxLength = 16, message = "密码格式不正确")
    @BindView(R.id.et_re_password)
    EditText et_re_password;


    @Required(order = 6, message = "请再次输入密码")
    @ConfirmPassword(order = 10, message = "两次输入密码不匹配")
    @BindView(R.id.et_re_confirm_password)
    EditText et_re_confirm_password;

    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.tv_email)
    TextView tv_email;
    @BindView(R.id.tv_password)
    TextView tv_password;
    @BindView(R.id.tv_confirm_password)
    TextView tv_confirm_password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mPresenter = new RegisterPresenter(this);
        mPresenter.start();
        ButterKnife.bind(this);
    }

    public void setPresenter(RegisterPresenter presenter) {
        this.mPresenter = presenter;
    }

    @OnClick({R.id.btn_re_register})
    public void regist(View view) {
        Validator validator = new Validator(this);
        validator.setValidationListener(this);
        validator.validate();
    }

    @Override
    public void onValidationSucceeded() {
        mPresenter.register(et_re_username.getText().toString().trim(), et_re_password.getText().toString().trim());
    }

    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        String message = failedRule.getFailureMessage();
        switch (failedView.getId()) {
            case R.id.et_re_username:
                showErrorTip(tv_username, message);
                break;
            case R.id.et_re_email:
                showErrorTip(tv_email, message);
                break;
            case R.id.et_re_password:
                showErrorTip(tv_password, message);
                break;
            case R.id.et_re_confirm_password:
                showErrorTip(tv_confirm_password, message);
                break;
            default:
                break;
        }
    }

    private void showErrorTip(TextView view, String message) {
        view.setTextColor(Color.RED);
        view.setText(message);
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

    @Override
    public void navigateToUpdateUser() {
        Intent intent =new Intent(getBaseContext(),UpdateUserInfoActivity.class);
        startActivity(intent);
    }

    @Override
    public void showResult(String message) {
        showErrorTip(tv_username, message);
    }
}
