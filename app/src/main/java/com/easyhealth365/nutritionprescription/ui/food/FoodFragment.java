package com.easyhealth365.nutritionprescription.ui.food;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;


import com.easyhealth365.nutritionprescription.R;
import com.easyhealth365.nutritionprescription.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodFragment extends BaseFragment {
    @BindView(R.id.vp_view_food)
    ViewPager mViewPager;
    @BindView(R.id.tabs_food)
    TabLayout mTabLayout;

    LayoutInflater mInflater;

    private List<String> mTitleList = new ArrayList<>();//页卡标题集合
    private View viewB, viewL,viewS,viewBA,viewLA,viewSA;//页卡视图
    private List<View> mViewList = new ArrayList<>();//页卡视图集合
    private View view;
    private static final String TAG = FoodFragment.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_food, null);
        }
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();
        mInflater = LayoutInflater.from(getContext());
        if (viewB == null && viewL == null&& viewS == null&& viewBA == null&& viewLA == null&& viewSA == null) {
            viewB = mInflater.inflate(R.layout.fragment_breakfast, null);
            viewL = mInflater.inflate(R.layout.fragment_breakfast, null);
            viewS = mInflater.inflate(R.layout.fragment_breakfast, null);
            viewBA = mInflater.inflate(R.layout.fragment_breakfast, null);
            viewLA = mInflater.inflate(R.layout.fragment_breakfast, null);
            viewSA = mInflater.inflate(R.layout.fragment_breakfast, null);



            //添加页卡视图
            mViewList.add(viewB);
            mViewList.add(viewL);
            mViewList.add(viewS);
            mViewList.add(viewBA);
            mViewList.add(viewLA);
            mViewList.add(viewSA);

            //添加页卡标题
            mTitleList.add("早餐");
            mTitleList.add("午餐");
            mTitleList.add("晚餐");
            mTitleList.add("早加餐");
            mTitleList.add("早加餐");
            mTitleList.add("晚加餐");
            mTabLayout.setTabMode(TabLayout.MODE_FIXED);//设置tab模式，当前为系统默认模式
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(0)));//添加tab选项卡
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(1)));
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(2)));
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(3)));
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(4)));
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitleList.get(5)));


            mViewPager.setAdapter(new MyPagerAdapter(getChildFragmentManager(), mViewList));//给ViewPager设置适配器
            mTabLayout.setupWithViewPager(mViewPager);//将TabLayout和ViewPager关联起来。
        }
    }

    @Override
    public void initView() {

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
