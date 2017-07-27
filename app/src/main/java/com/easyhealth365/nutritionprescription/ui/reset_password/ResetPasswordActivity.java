package com.easyhealth365.nutritionprescription.ui.reset_password;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.easyhealth365.nutritionprescription.R;
import com.easyhealth365.nutritionprescription.base.BaseActivity;
import com.easyhealth365.nutritionprescription.base.BaseApplication;
import com.easyhealth365.nutritionprescription.ui.login.LoginActivity;
import com.easyhealth365.nutritionprescription.utils.SharedPreferenceUtil;
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
import butterknife.OnCheckedChanged;
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
    @BindView(R.id.et_reset_password_new)
    EditText et_reset_password_new;
    @Required(order = 4, message = "请再次输入密码")
    @ConfirmPassword(order = 7, message = "两次输入密码不匹配")
    @BindView(R.id.et_reset_password_confirm)
    EditText et_reset_password_confirm;

    @BindView(R.id.reset_password_user_name)
    TextView reset_password_user_name;

    SharedPreferenceUtil spUtils = SharedPreferenceUtil.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        mPresenter = new ResetPasswordPresenter(this);
        mPresenter.start();
        reset_password_user_name.setText(spUtils.getUser().getResults().getRealname());
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

    @OnCheckedChanged(R.id.cb_show_password)
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (!isChecked) {
            et_reset_password_old.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            et_reset_password_new.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            et_reset_password_confirm.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            ToastUtil.showShort(getApplicationContext(), "显示密码");
        } else {
            et_reset_password_old.setTransformationMethod(PasswordTransformationMethod.getInstance());
            et_reset_password_new.setTransformationMethod(PasswordTransformationMethod.getInstance());
            et_reset_password_confirm.setTransformationMethod(PasswordTransformationMethod.getInstance());
            ToastUtil.showShort(getApplicationContext(), "隐藏密码");
        }
    }

    @Override
    public void onValidationSucceeded() {
        if (et_reset_password_old.getEditableText().toString()
                .equals(et_reset_password_new.getEditableText().toString())) {
            ToastUtil.showShort(this, "新密码不能与原密码一致");
            return;
        }
        mPresenter.resetPassword(spUtils.getUsername(),
                et_reset_password_old.getEditableText().toString().trim(),
                et_reset_password_new.getEditableText().toString().trim());
    }

    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        String message = failedRule.getFailureMessage();
        switch (failedView.getId()) {
            case R.id.et_reset_password_old:
                ToastUtil.showShort(this, message);
                break;
            case R.id.et_reset_password_new:
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
    public void showResult(int status) {
        if (status == 100) {
            ToastUtil.showShort(this, "重置密码成功");
        } else {
            ToastUtil.showShort(this, "重置密码失败");
        }
    }

    @Override
    public void navigateToLogin() {
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
