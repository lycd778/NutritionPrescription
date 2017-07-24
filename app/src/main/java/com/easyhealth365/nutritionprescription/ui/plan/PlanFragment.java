package com.easyhealth365.nutritionprescription.ui.plan;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easyhealth365.nutritionprescription.R;
import com.easyhealth365.nutritionprescription.base.BaseFragment;
import com.easyhealth365.nutritionprescription.beans.Plan;
import com.easyhealth365.nutritionprescription.beans.Record;
import com.easyhealth365.nutritionprescription.utils.DateUtil;
import com.easyhealth365.nutritionprescription.utils.SharedPreferenceUtil;
import com.easyhealth365.nutritionprescription.utils.TLog;
import com.easyhealth365.nutritionprescription.view.CircleProgressView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PlanFragment extends BaseFragment {
    @BindView(R.id.vp_view)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @BindView(R.id.line_plan)
    LinearLayout line_plan;
    @BindView(R.id.line_plan_noplan)
    LinearLayout line_plan_noplan;


    @BindView(R.id.breakfast_plan)
    TextView breakfast_plan;
    @BindView(R.id.breakfast_vegetable)
    TextView breakfast_vegetable;
    @BindView(R.id.breakfast_fruit)
    TextView breakfast_fruit;
    @BindView(R.id.breakfast_bread)
    TextView breakfast_bread;
    @BindView(R.id.breakfast_bean)
    TextView breakfast_bean;
    @BindView(R.id.breakfast_milk)
    TextView breakfast_milk;
    @BindView(R.id.breakfast_meat)
    TextView breakfast_meat;
    @BindView(R.id.breakfast_oil)
    TextView breakfast_oil;
    @BindView(R.id.breakfast_nut)
    TextView breakfast_nut;

    @BindView(R.id.lunch_plan)
    TextView lunch_plan;
    @BindView(R.id.lunch_vegetable)
    TextView lunch_vegetable;
    @BindView(R.id.lunch_fruit)
    TextView lunch_fruit;
    @BindView(R.id.lunch_bread)
    TextView lunch_bread;
    @BindView(R.id.lunch_bean)
    TextView lunch_bean;
    @BindView(R.id.lunch_milk)
    TextView lunch_milk;
    @BindView(R.id.lunch_meat)
    TextView lunch_meat;
    @BindView(R.id.lunch_oil)
    TextView lunch_oil;
    @BindView(R.id.lunch_nut)
    TextView lunch_nut;

    @BindView(R.id.dinner_plan)
    TextView dinner_plan;
    @BindView(R.id.dinner_vegetable)
    TextView dinner_vegetable;
    @BindView(R.id.dinner_fruit)
    TextView dinner_fruit;
    @BindView(R.id.dinner_bread)
    TextView dinner_bread;
    @BindView(R.id.dinner_bean)
    TextView dinner_bean;
    @BindView(R.id.dinner_milk)
    TextView dinner_milk;
    @BindView(R.id.dinner_meat)
    TextView dinner_meat;
    @BindView(R.id.dinner_oil)
    TextView dinner_oil;
    @BindView(R.id.dinner_nut)
    TextView dinner_nut;

    @BindView(R.id.breakfast_addition_plan)
    TextView breakfast_addition_plan;
    @BindView(R.id.breakfast_addition_vegetable)
    TextView breakfast_addition_vegetable;
    @BindView(R.id.breakfast_addition_fruit)
    TextView breakfast_addition_fruit;
    @BindView(R.id.breakfast_addition_bread)
    TextView breakfast_addition_bread;
    @BindView(R.id.breakfast_addition_bean)
    TextView breakfast_addition_bean;
    @BindView(R.id.breakfast_addition_milk)
    TextView breakfast_addition_milk;
    @BindView(R.id.breakfast_addition_meat)
    TextView breakfast_addition_meat;
    @BindView(R.id.breakfast_addition_oil)
    TextView breakfast_addition_oil;
    @BindView(R.id.breakfast_addition_nut)
    TextView breakfast_addition_nut;

    @BindView(R.id.lunch_addition_plan)
    TextView lunch_addition_plan;
    @BindView(R.id.lunch_addition_vegetable)
    TextView lunch_addition_vegetable;
    @BindView(R.id.lunch_addition_fruit)
    TextView lunch_addition_fruit;
    @BindView(R.id.lunch_addition_bread)
    TextView lunch_addition_bread;
    @BindView(R.id.lunch_addition_bean)
    TextView lunch_addition_bean;
    @BindView(R.id.lunch_addition_milk)
    TextView lunch_addition_milk;
    @BindView(R.id.lunch_addition_meat)
    TextView lunch_addition_meat;
    @BindView(R.id.lunch_addition_oil)
    TextView lunch_addition_oil;
    @BindView(R.id.lunch_addition_nut)
    TextView lunch_addition_nut;

    @BindView(R.id.dinner_addition_plan)
    TextView dinner_addition_plan;
    @BindView(R.id.dinner_addition_vegetable)
    TextView dinner_addition_vegetable;
    @BindView(R.id.dinner_addition_fruit)
    TextView dinner_addition_fruit;
    @BindView(R.id.dinner_addition_bread)
    TextView dinner_addition_bread;
    @BindView(R.id.dinner_addition_bean)
    TextView dinner_addition_bean;
    @BindView(R.id.dinner_addition_milk)
    TextView dinner_addition_milk;
    @BindView(R.id.dinner_addition_meat)
    TextView dinner_addition_meat;
    @BindView(R.id.dinner_addition_oil)
    TextView dinner_addition_oil;
    @BindView(R.id.dinner_addition_nut)
    TextView dinner_addition_nut;

    @BindView(R.id.tv_circle_progress_text)
    TextView tv_circle_progress_text;

    @BindView(R.id.breakfast)
    LinearLayout breakfast;
    LayoutInflater mInflater;
    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private View view1, view2;//页卡视图
    private List<View> mViewList = new ArrayList<>();//页卡视图集合
    private View view;
    private static final String TAG = PlanFragment.class.getSimpleName();
    SharedPreferenceUtil spUtils = SharedPreferenceUtil.getInstance();
    private FragmentManager manager;
    private FragmentTransaction ft;
    private float total_plan_num;
    private int total_actual_num;
    private Record pRecord = null;
    private Plan pPlan = null;
    private String time;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_plan, null);
        }
        ButterKnife.bind(this, view);
        if (!spUtils.getHavePlan()) {
            line_plan.setVisibility(View.GONE);
            line_plan_noplan.setVisibility(View.VISIBLE);
        }
        manager = getFragmentManager();
        initView();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick({R.id.breakfast})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.breakfast:
//                FoodFragment foodFragment = new FoodFragment();
//                ft = manager.beginTransaction();
//               //当前的fragment会被myJDEditFragment替换
//                ft.replace(R.id.realtabcontent, foodFragment);
//                ft.addToBackStack(null);
//                ft.commit();
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        time = DateUtil.getDate("yyyy-MM-dd");
        pPlan = spUtils.getPlan();
        if (spUtils.getRecord() == null) {
            pRecord = new Record();
        } else {
            if (spUtils.getRecord().getCheckTime() == null) {
                pRecord = new Record();
            } else {
                if (!(spUtils.getRecord().getCheckTime().equals(time))) {
                    pRecord = new Record();
                } else {
                    pRecord = spUtils.getRecord();
                }
            }
        }

        total_plan_num = Float.parseFloat(pPlan.getFoodExchange());
        total_actual_num = pRecord.getBreakfast_plan() + pRecord.getLunch_plan() +
                pRecord.getDinner_plan() + pRecord.getBreakfast_addition_plan() +
                pRecord.getLunch_addition_plan() + pRecord.getDinner_addition_plan();
        CircleProgressView cpView = (CircleProgressView) view.findViewById(R.id.circle_progress_view);
        int percent = (int) ((total_actual_num / total_plan_num) * 100);
        TLog.log(percent + "%");
        cpView.setProgress(percent);
        cpView.setProgressText(total_actual_num + "份/" + total_plan_num + "份");
        tv_circle_progress_text.setText(percent + "%");





//        mInflater = LayoutInflater.from(getContext());
//        if (view1 == null && view2 == null) {
//            view1 = mInflater.inflate(R.layout.fragment_diet, null);
//            view2 = mInflater.inflate(R.layout.fragment_weight, null);
//            TextView tv_diet_num = (TextView) view1.findViewById(R.id.tv_diet_num);
//            CircleProgressView cpView = (CircleProgressView) view1.findViewById(R.id.circle_progress_view_diet);
//            int percent = (int) (total_actual_num / total_plan_num * 100);
//            cpView.setProgress(percent);
//            cpView.setProgressText(total_actual_num + "份/" + total_plan_num + "份");
//            tv_diet_num.setText(percent+"%");
//            //添加页卡视图
//            mViewList.add(view1);
//            mViewList.add(view2);
//            //添加页卡标题
//            mTitleList.add("饮食");
//            mTitleList.add("体重");
//
//
//            mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
//            mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
//            mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
//
//            mViewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager(), mViewList));//给ViewPager设置适配器
//            mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
//        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void initView() {
        Plan plan = spUtils.getPlan();
        breakfast_plan.setText(plan.getBreakfast_plan() + "份");
        breakfast_vegetable.setText("蔬菜类：" + plan.getBreakfast_vegetable() + "份");
        breakfast_fruit.setText("水果类：" + plan.getBreakfast_fruit() + "份");
        breakfast_bread.setText("谷薯类：" + plan.getBreakfast_bread() + "份");
        breakfast_bean.setText("豆类：" + plan.getBreakfast_bean() + "份");
        breakfast_milk.setText("乳类：" + plan.getBreakfast_milk() + "份");
        breakfast_meat.setText("肉蛋类：" + plan.getBreakfast_meat() + "份");
        breakfast_oil.setText("油脂类：" + plan.getBreakfast_oil() + "份");
        breakfast_nut.setText("坚果类：" + plan.getBreakfast_nut() + "份");

        lunch_plan.setText(plan.getLunch_plan() + "份");
        lunch_vegetable.setText("蔬菜类：" + plan.getLunch_vegetable() + "份");
        lunch_fruit.setText("水果类：" + plan.getLunch_fruit() + "份");
        lunch_bread.setText("谷薯类：" + plan.getLunch_bread() + "份");
        lunch_bean.setText("豆类：" + plan.getLunch_bean() + "份");
        lunch_milk.setText("乳类：" + plan.getLunch_milk() + "份");
        lunch_meat.setText("肉蛋类：" + plan.getLunch_meat() + "份");
        lunch_oil.setText("油脂类：" + plan.getLunch_oil() + "份");
        lunch_nut.setText("坚果类：" + plan.getLunch_nut() + "份");

        dinner_plan.setText(plan.getDinner_plan() + "份");
        dinner_vegetable.setText("蔬菜类：" + plan.getDinner_vegetable() + "份");
        dinner_fruit.setText("水果类：" + plan.getDinner_fruit() + "份");
        dinner_bread.setText("谷薯类：" + plan.getDinner_bread() + "份");
        dinner_bean.setText("豆类：" + plan.getDinner_bean() + "份");
        dinner_milk.setText("乳类：" + plan.getDinner_milk() + "份");
        dinner_meat.setText("肉蛋类：" + plan.getDinner_meat() + "份");
        dinner_oil.setText("油脂类：" + plan.getDinner_oil() + "份");
        dinner_nut.setText("坚果类：" + plan.getDinner_nut() + "份");

        breakfast_addition_plan.setText(plan.getBreakfast_addition_plan() + "份");
        breakfast_addition_vegetable.setText("蔬菜类：" + plan.getBreakfast_addition_vegetable() + "份");
        breakfast_addition_fruit.setText("水果类：" + plan.getBreakfast_addition_fruit() + "份");
        breakfast_addition_bread.setText("谷薯类：" + plan.getBreakfast_addition_bread() + "份");
        breakfast_addition_bean.setText("豆类：" + plan.getBreakfast_addition_bean() + "份");
        breakfast_addition_milk.setText("乳类：" + plan.getBreakfast_addition_milk() + "份");
        breakfast_addition_meat.setText("肉蛋类：" + plan.getBreakfast_addition_meat() + "份");
        breakfast_addition_oil.setText("油脂类：" + plan.getBreakfast_addition_oil() + "份");
        breakfast_addition_nut.setText("坚果类：" + plan.getBreakfast_addition_nut() + "份");

        lunch_addition_plan.setText(plan.getLunch_addition_plan() + "份");
        lunch_addition_vegetable.setText("蔬菜类：" + plan.getLunch_addition_vegetable() + "份");
        lunch_addition_fruit.setText("水果类：" + plan.getLunch_addition_fruit() + "份");
        lunch_addition_bread.setText("谷薯类：" + plan.getLunch_addition_bread() + "份");
        lunch_addition_bean.setText("豆类：" + plan.getLunch_addition_bean() + "份");
        lunch_addition_milk.setText("乳类：" + plan.getLunch_addition_milk() + "份");
        lunch_addition_meat.setText("肉蛋类：" + plan.getLunch_addition_meat() + "份");
        lunch_addition_oil.setText("油脂类：" + plan.getLunch_addition_oil() + "份");
        lunch_addition_nut.setText("坚果类：" + plan.getLunch_addition_nut() + "份");

        dinner_addition_plan.setText(plan.getDinner_addition_plan() + "份");
        dinner_addition_vegetable.setText("蔬菜类：" + plan.getDinner_addition_vegetable() + "份");
        dinner_addition_fruit.setText("水果类：" + plan.getDinner_addition_fruit() + "份");
        dinner_addition_bread.setText("谷薯类：" + plan.getDinner_addition_bread() + "份");
        dinner_addition_bean.setText("豆类：" + plan.getDinner_addition_bean() + "份");
        dinner_addition_milk.setText("乳类：" + plan.getDinner_addition_milk() + "份");
        dinner_addition_meat.setText("肉蛋类：" + plan.getDinner_addition_meat() + "份");
        dinner_addition_oil.setText("油脂类：" + plan.getDinner_addition_oil() + "份");
        dinner_addition_nut.setText("坚果类：" + plan.getDinner_addition_nut() + "份");

    }

    //ViewPager适配器
    class MyPagerAdapter extends PagerAdapter {
        private List<View> mViewList;

        public MyPagerAdapter(FragmentManager childFragmentManager, List<View> mViewList) {
            this.mViewList = mViewList;
        }

        @Override
        public int getCount() {
            return mViewList.size();//页卡数
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;//官方推荐写法
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViewList.get(position));//添加页卡
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViewList.get(position));//删除页卡
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitleList.get(position);//页卡标题
        }

    }
}