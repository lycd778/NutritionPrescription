package com.easyhealth365.nutritionprescription.ui.food;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.easyhealth365.nutritionprescription.R;
import com.easyhealth365.nutritionprescription.base.BaseFragment;
import com.easyhealth365.nutritionprescription.beans.Plan;
import com.easyhealth365.nutritionprescription.beans.Recored;
import com.easyhealth365.nutritionprescription.utils.DateUtil;
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
    @BindView(R.id.btn_save_plan)
    Button btn_save_plan;
    @BindView(R.id.previous_plan)
    Spinner previous_plan;
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
    private int vegetableNum = 0, fruitNum = 0, breadNum = 0, beanNum = 0, milkNum = 0, meatNum = 0, oilNum = 0, nutNum = 0, pageNum = 0,dateNum=0;
    private String[] preDate = new String[6];

    LayoutInflater mInflater;
    private StringScrollPicker sScrollPicker;
    private String[] titleList = {"早餐", "午餐", "晚餐", "早加餐", "午加餐", "晚加餐"};
    private List<CharSequence> newList = new ArrayList<>();//页卡标题集合
    private View view;
    private static final String TAG = FoodFragment.class.getSimpleName();
    Recored recored = null;
    private String time;
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
            recored = new Recored();
            for (int i = 0; i >= -5; i--) {
                preDate[Math.abs(i)] = DateUtil.getOldDate(i);
                TLog.log(preDate[Math.abs(i)]);
            }
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
            R.id.oil_plus, R.id.oil_minus, R.id.nut_plus, R.id.nut_minus, R.id.btn_save_plan
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.vegetable_plus:
                vegetableNum += 1;
                setActualRecord(0, vegetableNum);
                vegetable_actual.setText(vegetableNum + "份");
                break;
            case R.id.vegetable_minus:
                vegetableNum -= 1;
                if (vegetableNum < 0) {
                    vegetableNum = 0;
                }
                setActualRecord(0, vegetableNum);
                vegetable_actual.setText(vegetableNum + "份");
                break;
            case R.id.fruit_plus:
                fruitNum += 1;
                setActualRecord(1, fruitNum);
                fruit_actual.setText(fruitNum + "份");
                break;
            case R.id.fruit_minus:
                fruitNum -= 1;
                if (fruitNum < 0) {
                    fruitNum = 0;
                }
                setActualRecord(1, fruitNum);
                fruit_actual.setText(fruitNum + "份");
                break;
            case R.id.bread_plus:
                breadNum += 1;
                setActualRecord(2, breadNum);
                bread_actual.setText(breadNum + "份");
                break;
            case R.id.bread_minus:
                breadNum -= 1;
                if (breadNum < 0) {
                    breadNum = 0;
                }
                setActualRecord(2, breadNum);
                bread_actual.setText(breadNum + "份");
                break;
            case R.id.bean_plus:
                beanNum += 1;
                setActualRecord(3, beanNum);
                bean_actual.setText(beanNum + "份");
                break;
            case R.id.bean_minus:
                beanNum -= 1;
                if (beanNum < 0) {
                    beanNum = 0;
                }
                setActualRecord(3, beanNum);
                bean_actual.setText(beanNum + "份");
                break;
            case R.id.milk_plus:
                milkNum += 1;
                setActualRecord(4, milkNum);
                milk_actual.setText(milkNum + "份");
                break;
            case R.id.milk_minus:
                milkNum -= 1;
                if (milkNum < 0) {
                    milkNum = 0;
                }
                setActualRecord(4, milkNum);
                milk_actual.setText(milkNum + "份");
                break;
            case R.id.meat_plus:
                meatNum += 1;
                setActualRecord(5, meatNum);
                meat_actual.setText(meatNum + "份");
                break;
            case R.id.meat_minus:
                meatNum -= 1;
                if (meatNum < 0) {
                    meatNum = 0;
                }
                setActualRecord(5, meatNum);
                meat_actual.setText(meatNum + "份");
                break;
            case R.id.oil_plus:
                oilNum += 1;
                setActualRecord(6, oilNum);
                oil_actual.setText(oilNum + "份");
                break;
            case R.id.oil_minus:
                oilNum -= 1;
                if (oilNum < 0) {
                    oilNum = 0;
                }
                setActualRecord(6, oilNum);
                oil_actual.setText(oilNum + "份");
                break;
            case R.id.nut_plus:
                nutNum += 1;
                setActualRecord(7, nutNum);
                nut_actual.setText(nutNum + "份");
                break;
            case R.id.nut_minus:
                nutNum -= 1;
                if (nutNum < 0) {
                    nutNum = 0;
                }
                setActualRecord(7, nutNum);
                nut_actual.setText(nutNum + "份");
                break;
            case R.id.btn_save_plan:
                break;

        }
    }

    @Override
    public void initView() {
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, preDate);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        previous_plan.setAdapter(adapter);
        previous_plan.setSelection(0);
        previous_plan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                dateNum=pos;
                TLog.log("当前日期是:" + preDate[pos]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        sScrollPicker = (StringScrollPicker) view.findViewById(R.id.sScrollPicker);
        for (String s : titleList) {
            newList.add(s);
        }
        sScrollPicker.setData(newList);
        sScrollPicker.setCenterPosition(pageNum);
        loadPlanView(pageNum);
        sScrollPicker.setOnSelectedListener(new ScrollPickerView.OnSelectedListener() {
            @Override
            public void onSelected(ScrollPickerView scrollPickerView, int position) {
                TLog.log("position: " + position);
                loadPlanView(position);
                pageNum = position;
            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void loadPlanView(int position) {
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

    @SuppressLint("SetTextI18n")
    private void setActualRecord(int typeNum, int foodNum) {
        time = DateUtil.getDate("yyyy-MM-dd");
        recored.setCheckTime(time);
        switch (pageNum) {
            case 0:
                switch (typeNum) {
                    case 0:
                        recored.setBreakfast_vegetable(foodNum);
                        break;
                    case 1:
                        recored.setBreakfast_fruit(foodNum);
                        break;
                    case 2:
                        recored.setBreakfast_bread(foodNum);
                        break;
                    case 3:
                        recored.setBreakfast_bean(foodNum);
                        break;
                    case 4:
                        recored.setBreakfast_milk(foodNum);
                        break;
                    case 5:
                        recored.setBreakfast_meat(foodNum);
                        break;
                    case 6:
                        recored.setBreakfast_oil(foodNum);
                        break;
                    case 7:
                        recored.setBreakfast_nut(foodNum);
                        break;
                }
                break;
            case 1:
                switch (typeNum) {
                    case 0:
                        recored.setLunch_vegetable(foodNum);
                        break;
                    case 1:
                        recored.setLunch_fruit(foodNum);
                        break;
                    case 2:
                        recored.setLunch_bread(foodNum);
                        break;
                    case 3:
                        recored.setLunch_bean(foodNum);
                        break;
                    case 4:
                        recored.setLunch_milk(foodNum);
                        break;
                    case 5:
                        recored.setLunch_meat(foodNum);
                        break;
                    case 6:
                        recored.setLunch_oil(foodNum);
                        break;
                    case 7:
                        recored.setLunch_nut(foodNum);
                        break;
                }
                break;
            case 2:
                switch (typeNum) {
                    case 0:
                        recored.setDinner_vegetable(foodNum);
                        break;
                    case 1:
                        recored.setDinner_fruit(foodNum);
                        break;
                    case 2:
                        recored.setDinner_bread(foodNum);
                        break;
                    case 3:
                        recored.setDinner_bean(foodNum);
                        break;
                    case 4:
                        recored.setDinner_milk(foodNum);
                        break;
                    case 5:
                        recored.setDinner_meat(foodNum);
                        break;
                    case 6:
                        recored.setDinner_oil(foodNum);
                        break;
                    case 7:
                        recored.setDinner_nut(foodNum);
                        break;
                }
                break;
            case 3:
                switch (typeNum) {
                    case 0:
                        recored.setBreakfast_addition_vegetable(foodNum);
                        break;
                    case 1:
                        recored.setBreakfast_addition_fruit(foodNum);
                        break;
                    case 2:
                        recored.setBreakfast_addition_bread(foodNum);
                        break;
                    case 3:
                        recored.setBreakfast_addition_bean(foodNum);
                        break;
                    case 4:
                        recored.setBreakfast_addition_milk(foodNum);
                        break;
                    case 5:
                        recored.setBreakfast_addition_meat(foodNum);
                        break;
                    case 6:
                        recored.setBreakfast_addition_oil(foodNum);
                        break;
                    case 7:
                        recored.setBreakfast_addition_nut(foodNum);
                        break;
                }
                break;
            case 4:
                switch (typeNum) {
                    case 0:
                        recored.setLunch_addition_vegetable(foodNum);
                        break;
                    case 1:
                        recored.setLunch_addition_fruit(foodNum);
                        break;
                    case 2:
                        recored.setLunch_addition_bread(foodNum);
                        break;
                    case 3:
                        recored.setLunch_addition_bean(foodNum);
                        break;
                    case 4:
                        recored.setLunch_addition_milk(foodNum);
                        break;
                    case 5:
                        recored.setLunch_addition_meat(foodNum);
                        break;
                    case 6:
                        recored.setLunch_addition_oil(foodNum);
                        break;
                    case 7:
                        recored.setLunch_addition_nut(foodNum);
                        break;
                }
                break;
            case 5:
                switch (typeNum) {
                    case 0:
                        recored.setDinner_addition_vegetable(foodNum);
                        break;
                    case 1:
                        recored.setDinner_addition_fruit(foodNum);
                        break;
                    case 2:
                        recored.setDinner_addition_bread(foodNum);
                        break;
                    case 3:
                        recored.setDinner_addition_bean(foodNum);
                        break;
                    case 4:
                        recored.setDinner_addition_milk(foodNum);
                        break;
                    case 5:
                        recored.setDinner_addition_meat(foodNum);
                        break;
                    case 6:
                        recored.setDinner_addition_oil(foodNum);
                        break;
                    case 7:
                        recored.setDinner_addition_nut(foodNum);
                        break;
                }
                break;
        }


    }


}
