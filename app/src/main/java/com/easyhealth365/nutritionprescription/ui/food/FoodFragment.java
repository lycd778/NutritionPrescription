package com.easyhealth365.nutritionprescription.ui.food;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easyhealth365.nutritionprescription.R;
import com.easyhealth365.nutritionprescription.base.BaseFragment;
import com.easyhealth365.nutritionprescription.beans.Plan;
import com.easyhealth365.nutritionprescription.utils.SharedPreferenceUtil;
import com.easyhealth365.nutritionprescription.utils.TLog;
import com.easyhealth365.nutritionprescription.view.ScrollPickerView;
import com.easyhealth365.nutritionprescription.view.StringScrollPicker;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoodFragment extends BaseFragment {
    @BindView(R.id.line_food)
    LinearLayout line_food;
    @BindView(R.id.line_food_noplan)
    LinearLayout line_food_noplan;
    @BindView(R.id.vegetable_plan)
    TextView vegetable_plan;
    @BindView(R.id.vegetable_actual)
    TextView vegetable_actual;
    @BindView(R.id.fruit_plan)
    TextView fruit_plan;
    @BindView(R.id.fruit_actual)
    TextView fruit_actual;
    @BindView(R.id.bread_plan)
    TextView bread_plan;
    @BindView(R.id.bread_actual)
    TextView bread_actual;
    @BindView(R.id.bean_plan)
    TextView bean_plan;
    @BindView(R.id.bean_actual)
    TextView bean_actual;
    @BindView(R.id.milk_plan)
    TextView milk_plan;
    @BindView(R.id.milk_actual)
    TextView milk_actual;
    @BindView(R.id.meat_plan)
    TextView meat_plan;
    @BindView(R.id.meat_actual)
    TextView meat_actual;
    @BindView(R.id.oil_plan)
    TextView oil_plan;
    @BindView(R.id.oil_actual)
    TextView oil_actual;
    @BindView(R.id.nut_plan)
    TextView nut_plan;
    @BindView(R.id.nut_actual)
    TextView nut_actual;
    private int vegetableNum = 0, fruitNum = 0, breadNum = 0, beanNum = 0, milkNum = 0, meatNum = 0, oilNum = 0, nutNum = 0, pageNum = 0;


    LayoutInflater mInflater;
    private StringScrollPicker sScrollPicker;
    private String[] titleList = {"早餐", "午餐", "晚餐", "早加餐", "午加餐", "晚加餐"};
    private List<CharSequence> newList = new ArrayList<>();//页卡标题集合
    private View view;
    private static final String TAG = FoodFragment.class.getSimpleName();
    SharedPreferenceUtil spUtils = SharedPreferenceUtil.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_food, null);
        }
        ButterKnife.bind(this, view);
        if (!spUtils.getHavePlan()) {
            line_food.setVisibility(View.GONE);
            line_food_noplan.setVisibility(View.VISIBLE);
        } else {
            initView();
        }

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @OnClick({R.id.vegetable_plus, R.id.vegetable_minus, R.id.fruit_plus, R.id.fruit_minus, R.id.bread_plus, R.id.bread_minus,
            R.id.bean_plus, R.id.bean_minus, R.id.milk_plus, R.id.milk_minus, R.id.meat_plus, R.id.meat_minus,
            R.id.oil_plus, R.id.oil_minus, R.id.nut_plus, R.id.nut_minus,
    })
    public void onClick(View view) {
        switch (view.getId()) {
     


        }
    }


    @Override
    public void initView() {
        sScrollPicker = (StringScrollPicker) view.findViewById(R.id.sScrollPicker);

        for (String s : titleList) {
            newList.add(s);
        }
        sScrollPicker.setData(newList);
        sScrollPicker.setCenterPosition(pageNum);
        loadFoodView(pageNum);
        sScrollPicker.setOnSelectedListener(new ScrollPickerView.OnSelectedListener() {
            @Override
            public void onSelected(ScrollPickerView scrollPickerView, int position) {
                TLog.log("position" + position);
                loadFoodView(position);
                pageNum = position;

            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void loadFoodView(int position) {
        Plan plan = spUtils.getPlan();
        if (position == 0) {
            vegetable_plan.setText(plan.getBreakfast_vegetable() + "份");
            fruit_plan.setText(plan.getBreakfast_fruit() + "份");
            bread_plan.setText(plan.getBreakfast_bread() + "份");
            bean_plan.setText(plan.getBreakfast_bean() + "份");
            milk_plan.setText(plan.getBreakfast_milk() + "份");
            meat_plan.setText(plan.getBreakfast_meat() + "份");
            oil_plan.setText(plan.getBreakfast_oil() + "份");
            nut_plan.setText(plan.getBreakfast_nut() + "份");
        } else if (position == 1) {
            vegetable_plan.setText(plan.getLunch_vegetable() + "份");
            fruit_plan.setText(plan.getLunch_fruit() + "份");
            bread_plan.setText(plan.getLunch_bread() + "份");
            bean_plan.setText(plan.getLunch_bean() + "份");
            milk_plan.setText(plan.getLunch_milk() + "份");
            meat_plan.setText(plan.getLunch_meat() + "份");
            oil_plan.setText(plan.getLunch_oil() + "份");
            nut_plan.setText(plan.getLunch_nut() + "份");
        } else if (position == 2) {
            vegetable_plan.setText(plan.getDinner_vegetable() + "份");
            fruit_plan.setText(plan.getDinner_fruit() + "份");
            bread_plan.setText(plan.getDinner_bread() + "份");
            bean_plan.setText(plan.getDinner_bean() + "份");
            milk_plan.setText(plan.getDinner_milk() + "份");
            meat_plan.setText(plan.getDinner_meat() + "份");
            oil_plan.setText(plan.getDinner_oil() + "份");
            nut_plan.setText(plan.getDinner_nut() + "份");
        } else if (position == 3) {
            vegetable_plan.setText(plan.getBreakfast_addition_vegetable() + "份");
            fruit_plan.setText(plan.getBreakfast_addition_fruit() + "份");
            bread_plan.setText(plan.getBreakfast_addition_bread() + "份");
            bean_plan.setText(plan.getBreakfast_addition_bean() + "份");
            milk_plan.setText(plan.getBreakfast_addition_milk() + "份");
            meat_plan.setText(plan.getBreakfast_addition_meat() + "份");
            oil_plan.setText(plan.getBreakfast_addition_oil() + "份");
            nut_plan.setText(plan.getBreakfast_addition_nut() + "份");
        } else if (position == 4) {
            vegetable_plan.setText(plan.getLunch_addition_vegetable() + "份");
            fruit_plan.setText(plan.getLunch_addition_fruit() + "份");
            bread_plan.setText(plan.getLunch_addition_bread() + "份");
            bean_plan.setText(plan.getLunch_addition_bean() + "份");
            milk_plan.setText(plan.getLunch_addition_milk() + "份");
            meat_plan.setText(plan.getLunch_addition_meat() + "份");
            oil_plan.setText(plan.getLunch_addition_oil() + "份");
            nut_plan.setText(plan.getLunch_addition_nut() + "份");
        } else if (position == 5) {
            vegetable_plan.setText(plan.getDinner_addition_vegetable() + "份");
            fruit_plan.setText(plan.getDinner_addition_fruit() + "份");
            bread_plan.setText(plan.getDinner_addition_bread() + "份");
            bean_plan.setText(plan.getDinner_addition_bean() + "份");
            milk_plan.setText(plan.getDinner_addition_milk() + "份");
            meat_plan.setText(plan.getDinner_addition_meat() + "份");
            oil_plan.setText(plan.getDinner_addition_oil() + "份");
            nut_plan.setText(plan.getDinner_addition_nut() + "份");
        }

    }

}
