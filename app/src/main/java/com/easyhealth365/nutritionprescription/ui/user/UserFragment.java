package com.easyhealth365.nutritionprescription.ui.user;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easyhealth365.nutritionprescription.R;
import com.easyhealth365.nutritionprescription.base.BaseFragment;
import com.easyhealth365.nutritionprescription.ui.day_report.DayReportActivity;
import com.easyhealth365.nutritionprescription.ui.login.LoginActivity;
import com.easyhealth365.nutritionprescription.ui.mian.MainActivity;
import com.easyhealth365.nutritionprescription.ui.register.RegisterActivity;
import com.easyhealth365.nutritionprescription.ui.user_info.UserInfoActivity;
import com.easyhealth365.nutritionprescription.utils.TLog;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserFragment extends BaseFragment {
    private View view;
    private static final String TAG = UserFragment.class.getSimpleName();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_user, null);
        }
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
    @OnClick({R.id.btn_login_out,R.id.user_info,R.id.user_day_report})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_out:
                Intent intent =new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.user_info:
                TLog.log("跳转到UserInfo");
                Intent userInfoIntent = new Intent(getActivity(), UserInfoActivity.class);
                startActivity(userInfoIntent);
                break;
            case R.id.user_day_report:
                TLog.log("跳转到DayReport");
                Intent dayReportIntent = new Intent(getActivity(), DayReportActivity.class);
                startActivity(dayReportIntent);
                break;
            default:
                break;
        }
    }
    @Override
    public void initView() {

    }

}
