package com.easyhealth365.nutritionprescription.ui.reset_password;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.easyhealth365.nutritionprescription.R;
import com.easyhealth365.nutritionprescription.base.BaseActivity;
import com.easyhealth365.nutritionprescription.utils.TLog;
import com.easyhealth365.nutritionprescription.utils.ToastUtil;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by lingxiao-Ching on 2017/7/24.
 */

public class ResetPasswordActivity extends BaseActivity<ResetPasswordContract.Presenter> implements ResetPasswordContract.View, Validator.ValidationListener {
    @Required(order = 1, message = "旧密码不能为空")
    @TextRule(order = 5, minLength = 6, maxLength = 16, message = "密码格式不正确")
    @BindView(R.id.et_reset_password_old)
    EditText et_reset_password_old;
    @Required(order = 2, message = "新密码不能为空")
    @Password(order = 3)
    @TextRule(order = 6, minLength = 6, maxLength = 16, message = "密码格式不正确")
    @BindView(R.id.et_reset_password_newt)
    EditText et_reset_password_newt;

    @Required(order = 4, message = "请再次输入密码")
    @ConfirmPassword(order = 7, message = "两次输入密码不匹配")
    @BindView(R.id.et_reset_password_confirm)
    EditText et_reset_password_confirm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        mPresenter = new ResetPasswordPresenter(this);
        mPresenter.start();
    }

    @OnClick({R.id.btn_reset_password})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_reset_password:
                Validator validator = new Validator(this);
                validator.setValidationListener(this);
                validator.validate();
                break;
        }
    }

    @Override
    public void onValidationSucceeded() {
        if (et_reset_password_old.getEditableText().toString()
                .equals(et_reset_password_newt.getEditableText().toString())) {
            ToastUtil.showShort(this, "新密码不能与原密码一致");
            return;
        }

    }

    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        String message = failedRule.getFailureMessage();
        switch (failedView.getId()) {
            case R.id.et_reset_password_old:
                ToastUtil.showShort(this, message);
                break;
            case R.id.et_reset_password_newt:
                ToastUtil.showShort(this, message);
                break;
            case R.id.et_reset_password_confirm:
                ToastUtil.showShort(this, message);
                break;
            default:
                break;
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void navigateToLogin() {

    }
}
