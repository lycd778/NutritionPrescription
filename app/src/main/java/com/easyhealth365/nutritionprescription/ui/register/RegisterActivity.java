package com.easyhealth365.nutritionprescription.ui.register;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easyhealth365.nutritionprescription.R;
import com.easyhealth365.nutritionprescription.base.BaseActivity;
import com.easyhealth365.nutritionprescription.base.BaseApplication;
import com.easyhealth365.nutritionprescription.beans.RegisterUser;
import com.easyhealth365.nutritionprescription.ui.login.LoginActivity;
import com.easyhealth365.nutritionprescription.utils.SharedPreferenceUtil;
import com.easyhealth365.nutritionprescription.utils.TLog;
import com.easyhealth365.nutritionprescription.utils.ToastUtil;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Required;
import com.mobsandgeeks.saripaar.annotation.TextRule;

import org.feezu.liuli.timeselector.TimeSelector;
import org.feezu.liuli.timeselector.view.PickerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
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

    @Required(order = 11, message = "真实姓名不能为空")
    @BindView(R.id.et_re_realname)
    EditText et_re_realname;


    @BindView(R.id.tv_username)
    TextView tv_username;
    @BindView(R.id.tv_email)
    TextView tv_email;
    @BindView(R.id.tv_password)
    TextView tv_password;
    @BindView(R.id.tv_confirm_password)
    TextView tv_confirm_password;
    @BindView(R.id.tv_realname)
    TextView tv_realname;


    @BindView(R.id.rg_re)
    RadioGroup rg_re;
    @BindView(R.id.rb_re_male)
    RadioButton rb_re_male;
    @BindView(R.id.rb_re_female)
    RadioButton rb_re_female;

    @BindView(R.id.month_pv)
    PickerView month_pv;

    @BindView(R.id.tv_re_title)
    TextView tv_re_title;
    @BindView(R.id.annv)
    RelativeLayout annv;
    @BindView(R.id.btn_re_ship)
    Button btn_re_ship;
    @BindView(R.id.register_xx0)
    LinearLayout register_xx0;
    @BindView(R.id.register_xx1)
    LinearLayout register_xx1;
    @BindView(R.id.register_xx2)
    LinearLayout register_xx2;
    @BindView(R.id.register_xx3)
    LinearLayout register_xx3;
    private boolean gender = true; // true 男 ，false 女
    private boolean isGenderChecked=false;
    SharedPreferenceUtil spUtils = SharedPreferenceUtil.getInstance();
    private int i = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mPresenter = new RegisterPresenter(this);
        mPresenter.start();
        ButterKnife.bind(this);
        et_re_username.setText("15645464748");
        et_re_email.setText("qlx7117@sina.com");
        et_re_password.setText("123456");
        et_re_confirm_password.setText("123456");


    }

    public void setPresenter(RegisterPresenter presenter) {
        this.mPresenter = presenter;
    }

    @OnClick({R.id.btn_re_register, R.id.btn_re_ship, R.id.btn_re_next, R.id.btn_re_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_re_register:
                Validator validator = new Validator(this);
                validator.setValidationListener(this);
                validator.validate();
                break;
            case R.id.btn_re_back:
                i--;
                if (i < 0) {
                    navigateToLogin();
                } else {
                    switchInfo();
                }
                break;
            case R.id.btn_re_next:
                if (judgeInfo()) {
                    i++;
                    if (i > 6) {
                        //mPresenter.register(reUser);
                    } else {
                        switchInfo();
                    }
                } else {
                    ToastUtil.showShort(this, "该项为必填项！");
                }
                break;
            case R.id.btn_re_ship:
//                if (judgeInfo()) {
//                    i++;
//                    if (i > 6) {
//                        //mPresenter.register(reUser);
//                    } else {
//                        switchInfo();
//                    }
//                } else{
//                    ToastUtil.showShort(this,"该项为必填项！");
//                }
                break;

        }
    }

    @OnCheckedChanged({R.id.rb_re_female, R.id.rb_re_male})
    public void OnCheckedChanged(RadioButton p0, boolean p1) {
            isGenderChecked=true;
            gender = p1;
    }

    private void switchInfo() {
        switch (i) {
            case 0:
                tv_re_title.setText("真实姓名");
                register_xx0.setVisibility(View.VISIBLE);
                register_xx1.setVisibility(View.GONE);
                btn_re_ship.setVisibility(View.VISIBLE);
                annv.setVisibility(View.GONE);
                break;
            case 1:
                tv_re_title.setText("真实姓名");
                register_xx0.setVisibility(View.GONE);
                register_xx1.setVisibility(View.VISIBLE);
                register_xx2.setVisibility(View.GONE);
                annv.setVisibility(View.VISIBLE);
                btn_re_ship.setVisibility(View.GONE);
                break;
            case 2:
                tv_re_title.setText("性别");
                register_xx1.setVisibility(View.GONE);
                register_xx2.setVisibility(View.VISIBLE);
                register_xx3.setVisibility(View.GONE);
                break;
            case 3:
                tv_re_title.setText("出生日期");
                register_xx2.setVisibility(View.GONE);
                register_xx3.setVisibility(View.VISIBLE);
                btn_re_ship.setVisibility(View.VISIBLE);
                TimeSelector timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
                    @Override
                    public void handle(String time) {
                        ToastUtil.showShort(getApplicationContext(),time);
                    }
                }, "1900-01-01", "2017-01-01");
                timeSelector.setMode(TimeSelector.MODE.YMD);
                timeSelector.show();
                break;
            case 4:
                ;
                break;
            case 5:
                ;
                break;
            case 6:
                ;
                break;
        }

    }

    private Boolean judgeInfo() {
        switch (i) {
            case 1:
                if (et_re_realname.getText().toString().trim().equals("")) {
                    return false;
                } else {
                    spUtils.getReUser().setRealname(et_re_realname.getText().toString().trim());
                    return true;
                }
            case 2:
                if(isGenderChecked){
                    spUtils.getReUser().setGender(gender);
                    return true;
                }else {
                    return false;
                }



            default:
                return true;

        }
    }


    @Override
    public void onValidationSucceeded() {
        mPresenter.checkPhone(et_re_username.getText().toString().trim(), et_re_password.getText().toString().trim());
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
    public void updateReUser() {
        RegisterUser reUser = new RegisterUser();
        reUser.setUsername(et_re_username.getText().toString().trim());
        reUser.setPassword(et_re_password.getText().toString().trim());
        reUser.setEmail(et_re_email.getText().toString().trim());
        spUtils.putReUser(reUser);
        i += 1;
        switchInfo();
    }


    @Override
    public void showResult(String message) {
        showErrorTip(tv_username, message);
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
    public void navigateToLogin() {
        Intent intent = new Intent(getBaseContext(), LoginActivity.class);
        startActivity(intent);
    }
}
