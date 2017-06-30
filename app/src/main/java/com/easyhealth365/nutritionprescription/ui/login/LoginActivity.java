package com.easyhealth365.nutritionprescription.ui.login;
import android.os.Bundle;
import com.easyhealth365.nutritionprescription.R;
import com.easyhealth365.nutritionprescription.base.BaseActivity;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity<LoginContract.Presenter> implements LoginContract.View {

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
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void navigateToMain() {

    }

    @Override
    public void navigateToRegister() {

    }
}
