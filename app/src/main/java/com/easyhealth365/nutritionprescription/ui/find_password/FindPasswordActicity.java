package com.easyhealth365.nutritionprescription.ui.find_password;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.easyhealth365.nutritionprescription.R;
import com.easyhealth365.nutritionprescription.base.BaseActivity;
import com.easyhealth365.nutritionprescription.base.BaseApplication;
import com.easyhealth365.nutritionprescription.utils.SharedPreferenceUtil;
import com.easyhealth365.nutritionprescription.utils.ToastUtil;
import com.mob.MobSDK;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by lingxiao-Ching on 2017/7/25.
 */

public class FindPasswordActicity extends BaseActivity<FindPasswordContract.Presenter> implements FindPasswordContract.View, Validator.ValidationListener {
    @Required(order = 1, message = "手机号不能为空")
    @TextRule(order = 6, minLength = 11, maxLength = 11, message = "手机号格式错误")
    @BindView(R.id.et_phone)
    EditText et_phone;
    @Required(order = 2, message = "验证码不能为空")
    @BindView(R.id.et_verification_code)
    EditText et_verification_code;
    @Required(order = 3, message = "新密码不能为空")
    @Password(order = 4)
    @TextRule(order = 7, minLength = 6, maxLength = 16, message = "密码格式不正确")
    @BindView(R.id.et_new_password)
    EditText et_new_password;
    @Required(order = 5, message = "请再次输入密码")
    @ConfirmPassword(order = 8, message = "两次输入密码不匹配")
    @BindView(R.id.et_confirm_password)
    EditText et_confirm_password;
    @BindView(R.id.tv_verification_code)
    TextView tv_verification_code;
    SharedPreferenceUtil spUtils = SharedPreferenceUtil.getInstance();
    private final String appKey="1fb0970afa105";
    private final String appSecret="0d976c8253bfa3c0cad0c5f5a250703b";
    private EventHandler eh;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        ButterKnife.bind(this);
        mPresenter = new FindPasswordPresenter(this);
        mPresenter.start();
        MobSDK.init(this, appKey, appSecret);
//        eh=new EventHandler(){
//            @Override
//            public void afterEvent(int event, int result, Object data) {
//                if (result == SMSSDK.RESULT_COMPLETE) {
//                    //回调完成
//                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
//                        //提交验证码成功
//                        Message msg = new Message();
//                        msg.arg1 = 0;
//                        msg.obj = data;
//                        handler.sendMessage(msg);
//                        Log.d(TAG, "提交验证码成功");
//                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
//                        Message msg = new Message();
//                        //获取验证码成功
//                        msg.arg1 = 1;
//                        msg.obj = "获取验证码成功";
//                        handler.sendMessage(msg);
//                        Log.d(TAG, "获取验证码成功");
//                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
//                        Message msg = new Message();
//                        //返回支持发送验证码的国家列表
//                        msg.arg1 = 2;
//                        msg.obj = "返回支持发送验证码的国家列表";
//                        handler.sendMessage(msg);
//                        Log.d(TAG, "返回支持发送验证码的国家列表");
//                    }
//                } else {
//                    Message msg = new Message();
//                    //返回支持发送验证码的国家列表
//                    msg.arg1 = 3;
//                    msg.obj = "验证失败";
//                    handler.sendMessage(msg);
//                    Log.d(TAG, "验证失败");
//                    ((Throwable) data).printStackTrace();
//                }
//            }
//        };
//
//        SMSSDK.registerEventHandler(eh); //注册短信回调
    }

    @OnClick({R.id.btn_find_password,R.id.tv_verification_code})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_find_password:
                Validator validator = new Validator(this);
                validator.setValidationListener(this);
                validator.validate();
                break;
            case R.id.tv_verification_code:

                break;
        }
    }
    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        String message = failedRule.getFailureMessage();
        switch (failedView.getId()) {
            case R.id.et_phone:
                ToastUtil.showShort(this, message);
                break;
            case R.id.et_new_password:
                ToastUtil.showShort(this, message);
                break;
            case R.id.et_confirm_password:
                ToastUtil.showShort(this, message);
                break;
            default:
                break;
        }
    }



    @Override
    public void showResult(int status) {

    }

    @Override
    public void navigateToLogin() {

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
