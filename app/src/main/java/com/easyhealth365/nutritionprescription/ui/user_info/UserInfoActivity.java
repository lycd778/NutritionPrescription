package com.easyhealth365.nutritionprescription.ui.user_info;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.easyhealth365.nutritionprescription.R;
import com.easyhealth365.nutritionprescription.base.BaseActivity;
import com.easyhealth365.nutritionprescription.beans.Plan;
import com.easyhealth365.nutritionprescription.beans.User;
import com.easyhealth365.nutritionprescription.utils.SharedPreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lingxiao-Ching on 2017/7/21.
 */

public class UserInfoActivity extends BaseActivity {
    @BindView(R.id.tv_user_info_username)
    TextView tv_user_info_username;
    @BindView(R.id.tv_user_info_realname)
    TextView tv_user_info_realname;
    @BindView(R.id.tv_user_info_height)
    TextView tv_user_info_height;
    @BindView(R.id.tv_user_info_weight)
    TextView tv_user_info_weight;
    @BindView(R.id.tv_user_info_bmi)
    TextView tv_user_info_bmi;
    @BindView(R.id.image_user_info_male)
    ImageView image_user_info_male;
    @BindView(R.id.image_user_info_female)
    ImageView image_user_info_female;
    SharedPreferenceUtil spUtils = SharedPreferenceUtil.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);
        User user = spUtils.getUser();
        Plan plan = spUtils.getPlan();
        tv_user_info_username.setText(user.getResults().getTelephone());
        tv_user_info_realname.setText(user.getResults().getRealname());
        if (user.getResults().isGender()) {
            image_user_info_male.setImageResource(R.mipmap.ic_user_male_checked);
        } else {
            image_user_info_male.setImageResource(R.mipmap.ic_user_female_checked);
        }
        if (!spUtils.getHavePlan()) {
            tv_user_info_height.setText(plan.getHeight());
            tv_user_info_weight.setText(plan.getWeight());
            tv_user_info_bmi.setText(plan.getBmi());
        }

    }
}
