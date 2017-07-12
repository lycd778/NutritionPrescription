package com.easyhealth365.nutritionprescription.ui.mian;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.easyhealth365.nutritionprescription.AppManager;
import com.easyhealth365.nutritionprescription.R;
import com.easyhealth365.nutritionprescription.base.BaseActivity;
import com.easyhealth365.nutritionprescription.base.BaseApplication;
import com.easyhealth365.nutritionprescription.beans.User;
import com.easyhealth365.nutritionprescription.ui.food.FoodFragment;
import com.easyhealth365.nutritionprescription.ui.plan.PlanFragment;
import com.easyhealth365.nutritionprescription.ui.user.UserFragment;
import com.easyhealth365.nutritionprescription.utils.SharedPreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.realtabcontent)
    FrameLayout realtabcontent;
    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabHost;
    TabHost.TabSpec tabSpec0;
    SharedPreferenceUtil spUtils=SharedPreferenceUtil.getInstance();
    private TextView tv_count;
    private Class fragmentArray[] = {PlanFragment.class, FoodFragment.class, UserFragment.class};
    private String tabHostTagArray[] = {"营养处方",  "饮食管理","个人信息"};
    private int mImageViewArray[] = {R.drawable.ic_plan, R.drawable.ic_food,R.drawable.ic_user};
    private TabHost.OnTabChangeListener tabChangeListener;
    private String currentTab=tabHostTagArray[0];
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenter(this);
        mPresenter.start();
        User user=spUtils.getUser();
        mPresenter.loadPlanlist(user.getResults().getUserid(),user.getResults().getAccess_token(),user.getResults().getHospitalBaseUrl());

    }
@OnClick({})
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
    public void initTabHost() {
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);
        int count = fragmentArray.length;
        for (int i = 0; i < count; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tabHostTagArray[i]).setIndicator(getTabItemView(i));
            if (i == 0) {
                this.tabSpec0 = tabSpec;
            }
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            //设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
        }

        tabChangeListener = new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //toolbar_title.setText(tabId);
                currentTab = tabId;
            }
        };
        mTabHost.setOnTabChangedListener(tabChangeListener);
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = View.inflate(MainActivity.this, R.layout.tabhost_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);
        TextView main_tab_unread_tv = (TextView) view.findViewById(R.id.main_tab_unread_tv);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(tabHostTagArray[index]);
        return view;
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {//当返回按键被按下
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);//新建一个对话框
            dialog.setMessage("确定要退出吗?");//设置提示信息
            //设置确定按钮并监听
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();//结束当前Activity
                }
            });
            //设置取消按钮并监听
            dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //这里什么也不用做
                }
            });
            dialog.show();//最后不要忘记把对话框显示出来
        }
        return false;
    }
}
