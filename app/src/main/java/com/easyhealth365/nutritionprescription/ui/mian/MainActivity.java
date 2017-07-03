package com.easyhealth365.nutritionprescription.ui.mian;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.easyhealth365.nutritionprescription.AppManager;
import com.easyhealth365.nutritionprescription.R;
import com.easyhealth365.nutritionprescription.base.BaseActivity;
import com.easyhealth365.nutritionprescription.ui.fragment.food.FoodFragment;
import com.easyhealth365.nutritionprescription.ui.fragment.plan.PlanFragment;
import com.easyhealth365.nutritionprescription.ui.fragment.user.UserFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.realtabcontent)
    FrameLayout realtabcontent;
    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(android.R.id.tabhost)
    FragmentTabHost mTabHost;
    TabHost.TabSpec tabSpec0;
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
    public void onBackPressed() {
        // LxApplication.exit();
        AppManager.getAppManager().AppExit(this);
    }
}
