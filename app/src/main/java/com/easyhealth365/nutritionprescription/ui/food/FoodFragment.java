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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.easyhealth365.nutritionprescription.R;
import com.easyhealth365.nutritionprescription.base.BaseApplication;
import com.easyhealth365.nutritionprescription.base.BaseFragment;
import com.easyhealth365.nutritionprescription.beans.Plan;
import com.easyhealth365.nutritionprescription.beans.PreRecord;
import com.easyhealth365.nutritionprescription.beans.Record;
import com.easyhealth365.nutritionprescription.utils.DateUtil;
import com.easyhealth365.nutritionprescription.utils.SharedPreferenceUtil;
import com.easyhealth365.nutritionprescription.utils.TLog;
import com.easyhealth365.nutritionprescription.utils.ToastUtil;
import com.easyhealth365.nutritionprescription.view.CenterDialog;
import com.easyhealth365.nutritionprescription.view.ScrollPickerView;
import com.easyhealth365.nutritionprescription.view.StringScrollPicker;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FoodFragment extends BaseFragment<FoodContract.Presenter> implements FoodContract.View ,CenterDialog.OnCenterItemClickListener{
    @BindView(R.id.line_food)
    LinearLayout line_food;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.line_food_noplan)
    LinearLayout line_food_noplan;
    @BindView(R.id.btn_save_plan)
    Button btn_save_plan;
    @BindView(R.id.previous_plan)
    Spinner previous_plan;
    @BindView(R.id.tv_progress)
    TextView tv_progress;
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
    @BindView(R.id.image_progress)
    ImageView image_progress;
    @BindView(R.id.vegetable_plus)
    ImageView vegetable_plus;
    @BindView(R.id.vegetable_minus)
    ImageView vegetable_minus;
    @BindView(R.id.fruit_plus)
    ImageView fruit_plus;
    @BindView(R.id.fruit_minus)
    ImageView fruit_minus;
    @BindView(R.id.bread_plus)
    ImageView bread_plus;
    @BindView(R.id.bread_minus)
    ImageView bread_minus;
    @BindView(R.id.bean_plus)
    ImageView bean_plus;
    @BindView(R.id.bean_minus)
    ImageView bean_minus;
    @BindView(R.id.milk_plus)
    ImageView milk_plus;
    @BindView(R.id.milk_minus)
    ImageView milk_minus;
    @BindView(R.id.meat_plus)
    ImageView meat_plus;
    @BindView(R.id.meat_minus)
    ImageView meat_minus;
    @BindView(R.id.oil_plus)
    ImageView oil_plus;
    @BindView(R.id.oil_minus)
    ImageView oil_minus;
    @BindView(R.id.nut_plus)
    ImageView nut_plus;
    @BindView(R.id.nut_minus)
    ImageView nut_minus;


    private int vegetableNum = 0, fruitNum = 0, breadNum = 0, beanNum = 0, milkNum = 0, meatNum = 0, oilNum = 0, nutNum = 0,
            breakfastNum = 0, lunchNum = 0, dinnerNum, breakfastAdNum = 0, lunchAdNum = 0, dinnerAdNum,
            pageNum = 0, dateNum = 0;
    private String[] preDate = new String[6];
    private CenterDialog centerDialog;
    LayoutInflater mInflater;
    private StringScrollPicker sScrollPicker;
    private String[] titleList = {"早餐", "午餐", "晚餐", "早加餐", "午加餐", "晚加餐"};
    private List<CharSequence> newList = new ArrayList<>();//页卡标题集合
    private View view;
    private static final String TAG = FoodFragment.class.getSimpleName();
    private Record mRecord = null;
    private PreRecord mPreRecord = null;
    private Plan mPlan = null;
    private String time;
    SharedPreferenceUtil spUtils = SharedPreferenceUtil.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_food, null);
        }
        ButterKnife.bind(this, view);
        return view;
    }

    public void setPresenter(FoodPresenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter == null) {
            mPresenter = new FoodPresenter(this);
            mPresenter.start();
        }
    }

    @Override
    public void OnCenterItemClick(CenterDialog dialog, View view, String et_weight) {
        switch (view.getId()){
            case R.id.dialog_sure:
                //ToastUtil.showShort(getContext(),"确定");
                break;
            default:
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        centerDialog = new CenterDialog(getContext(), R.layout.dialog_tips, new int[]{ R.id.dialog_sure});
        centerDialog.setOnCenterItemClickListener(this);
        if (!spUtils.getHavePlan()) {
            line_food.setVisibility(View.GONE);
            line_food_noplan.setVisibility(View.VISIBLE);
        } else {
            time = DateUtil.getDate("yyyy-MM-dd");

            if (spUtils.getRecord() == null) {
                mRecord = new Record();
            } else {
                if (spUtils.getRecord().getCheckTime() == null) {
                    mRecord = new Record();
                } else {
                    if (!(spUtils.getRecord().getCheckTime().equals(time))) {
                        mRecord = new Record();
                    } else {
                        mRecord = spUtils.getRecord();
                    }
                }


            }
            mPlan = spUtils.getPlan();
            for (int i = 0; i >= -5; i--) {
                preDate[Math.abs(i)] = DateUtil.getOldDate(i);
                TLog.log(preDate[Math.abs(i)]);
            }
            initView();
        }

    }

    @OnClick({R.id.vegetable_plus, R.id.vegetable_minus, R.id.fruit_plus, R.id.fruit_minus, R.id.bread_plus, R.id.bread_minus,
            R.id.bean_plus, R.id.bean_minus, R.id.milk_plus, R.id.milk_minus, R.id.meat_plus, R.id.meat_minus,
            R.id.oil_plus, R.id.oil_minus, R.id.nut_plus, R.id.nut_minus, R.id.btn_save_plan,R.id.ll_show_tips
    })
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.vegetable_plus:
                vegetableNum += 1;
                setActualRecord(0, vegetableNum);
                setNowProgressBar();
                vegetable_actual.setText(vegetableNum + "份");
                break;
            case R.id.vegetable_minus:
                vegetableNum -= 1;
                if (vegetableNum < 0) {
                    vegetableNum = 0;
                }
                setActualRecord(0, vegetableNum);
                setNowProgressBar();
                vegetable_actual.setText(vegetableNum + "份");
                break;
            case R.id.fruit_plus:
                fruitNum += 1;
                setActualRecord(1, fruitNum);
                setNowProgressBar();
                fruit_actual.setText(fruitNum + "份");
                break;
            case R.id.fruit_minus:
                fruitNum -= 1;
                if (fruitNum < 0) {
                    fruitNum = 0;
                }
                setActualRecord(1, fruitNum);
                setNowProgressBar();
                fruit_actual.setText(fruitNum + "份");
                break;
            case R.id.bread_plus:
                breadNum += 1;
                setActualRecord(2, breadNum);
                setNowProgressBar();
                bread_actual.setText(breadNum + "份");
                break;
            case R.id.bread_minus:
                breadNum -= 1;
                if (breadNum < 0) {
                    breadNum = 0;
                }
                setActualRecord(2, breadNum);
                setNowProgressBar();
                bread_actual.setText(breadNum + "份");
                break;
            case R.id.bean_plus:
                beanNum += 1;
                setActualRecord(3, beanNum);
                setNowProgressBar();
                bean_actual.setText(beanNum + "份");
                break;
            case R.id.bean_minus:
                beanNum -= 1;
                if (beanNum < 0) {
                    beanNum = 0;
                }
                setActualRecord(3, beanNum);
                setNowProgressBar();
                bean_actual.setText(beanNum + "份");
                break;
            case R.id.milk_plus:
                milkNum += 1;
                setActualRecord(4, milkNum);
                setNowProgressBar();
                milk_actual.setText(milkNum + "份");
                break;
            case R.id.milk_minus:
                milkNum -= 1;
                if (milkNum < 0) {
                    milkNum = 0;
                }
                setActualRecord(4, milkNum);
                setNowProgressBar();
                milk_actual.setText(milkNum + "份");
                break;
            case R.id.meat_plus:
                meatNum += 1;
                setActualRecord(5, meatNum);
                setNowProgressBar();
                meat_actual.setText(meatNum + "份");
                break;
            case R.id.meat_minus:
                meatNum -= 1;
                if (meatNum < 0) {
                    meatNum = 0;
                }
                setActualRecord(5, meatNum);
                setNowProgressBar();
                meat_actual.setText(meatNum + "份");
                break;
            case R.id.oil_plus:
                oilNum += 1;
                setActualRecord(6, oilNum);
                setNowProgressBar();
                oil_actual.setText(oilNum + "份");
                break;
            case R.id.oil_minus:
                oilNum -= 1;
                if (oilNum < 0) {
                    oilNum = 0;
                }
                setActualRecord(6, oilNum);
                setNowProgressBar();
                oil_actual.setText(oilNum + "份");
                break;
            case R.id.nut_plus:
                nutNum += 1;
                setActualRecord(7, nutNum);
                setNowProgressBar();
                nut_actual.setText(nutNum + "份");
                break;
            case R.id.nut_minus:
                nutNum -= 1;
                if (nutNum < 0) {
                    nutNum = 0;
                }
                setActualRecord(7, nutNum);
                setNowProgressBar();
                nut_actual.setText(nutNum + "份");
                break;
            case R.id.btn_save_plan:
                mPresenter.updateRecord(mRecord,
                        spUtils.getUser().getResults().getUserid(),
                        spUtils.getUser().getResults().getAccess_token(),
                        spUtils.getUser().getResults().getHospitalBaseUrl());
                break;
            case R.id.ll_show_tips:
                centerDialog.show();
                break;

        }
    }

    @Override
    public void initView() {
        setNowProgressBar();
        //日期选择器
        // 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, preDate);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //绑定 Adapter到控件
        previous_plan.setAdapter(adapter);
        previous_plan.setSelection(dateNum);
        previous_plan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                dateNum = pos;
                //  TLog.log("当前日期是:" + preDate[pos]);
                if (dateNum == 0) {
                    loadActualView(pageNum, mRecord);
                    setNowProgressBar();
                } else if (dateNum > 0) {
                    mPresenter.getPreRecord(preDate[pos],
                            spUtils.getUser().getResults().getUserid(),
                            spUtils.getUser().getResults().getAccess_token(),
                            spUtils.getUser().getResults().getHospitalBaseUrl()
                    );
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        //三餐选择器
        sScrollPicker = (StringScrollPicker) view.findViewById(R.id.sScrollPicker);
        for (String s : titleList) {
            newList.add(s);
        }
        sScrollPicker.setData(newList);
        sScrollPicker.setSelectedPosition(pageNum);
        sScrollPicker.setOnSelectedListener(new ScrollPickerView.OnSelectedListener() {
            @Override
            public void onSelected(ScrollPickerView scrollPickerView, int position) {
                // TLog.log("position: " + position);
                loadPlanView(position);
                pageNum = position;
                if (dateNum == 0) {
                    loadActualView(pageNum, mRecord);
                    setNowProgressBar();
                } else if (dateNum > 0) {
                    loadPreRecordView(pageNum, mPreRecord);
                    setPreProgressBar();
                }


            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void loadPlanView(int position) {
        if (position == 0) {
            vegetable_plan.setText(mPlan.getBreakfast_vegetable() + "份");
            fruit_plan.setText(mPlan.getBreakfast_fruit() + "份");
            bread_plan.setText(mPlan.getBreakfast_bread() + "份");
            bean_plan.setText(mPlan.getBreakfast_bean() + "份");
            milk_plan.setText(mPlan.getBreakfast_milk() + "份");
            meat_plan.setText(mPlan.getBreakfast_meat() + "份");
            oil_plan.setText(mPlan.getBreakfast_oil() + "份");
            nut_plan.setText(mPlan.getBreakfast_nut() + "份");
        } else if (position == 1) {
            vegetable_plan.setText(mPlan.getLunch_vegetable() + "份");
            fruit_plan.setText(mPlan.getLunch_fruit() + "份");
            bread_plan.setText(mPlan.getLunch_bread() + "份");
            bean_plan.setText(mPlan.getLunch_bean() + "份");
            milk_plan.setText(mPlan.getLunch_milk() + "份");
            meat_plan.setText(mPlan.getLunch_meat() + "份");
            oil_plan.setText(mPlan.getLunch_oil() + "份");
            nut_plan.setText(mPlan.getLunch_nut() + "份");
        } else if (position == 2) {
            vegetable_plan.setText(mPlan.getDinner_vegetable() + "份");
            fruit_plan.setText(mPlan.getDinner_fruit() + "份");
            bread_plan.setText(mPlan.getDinner_bread() + "份");
            bean_plan.setText(mPlan.getDinner_bean() + "份");
            milk_plan.setText(mPlan.getDinner_milk() + "份");
            meat_plan.setText(mPlan.getDinner_meat() + "份");
            oil_plan.setText(mPlan.getDinner_oil() + "份");
            nut_plan.setText(mPlan.getDinner_nut() + "份");
        } else if (position == 3) {
            vegetable_plan.setText(mPlan.getBreakfast_addition_vegetable() + "份");
            fruit_plan.setText(mPlan.getBreakfast_addition_fruit() + "份");
            bread_plan.setText(mPlan.getBreakfast_addition_bread() + "份");
            bean_plan.setText(mPlan.getBreakfast_addition_bean() + "份");
            milk_plan.setText(mPlan.getBreakfast_addition_milk() + "份");
            meat_plan.setText(mPlan.getBreakfast_addition_meat() + "份");
            oil_plan.setText(mPlan.getBreakfast_addition_oil() + "份");
            nut_plan.setText(mPlan.getBreakfast_addition_nut() + "份");
        } else if (position == 4) {
            vegetable_plan.setText(mPlan.getLunch_addition_vegetable() + "份");
            fruit_plan.setText(mPlan.getLunch_addition_fruit() + "份");
            bread_plan.setText(mPlan.getLunch_addition_bread() + "份");
            bean_plan.setText(mPlan.getLunch_addition_bean() + "份");
            milk_plan.setText(mPlan.getLunch_addition_milk() + "份");
            meat_plan.setText(mPlan.getLunch_addition_meat() + "份");
            oil_plan.setText(mPlan.getLunch_addition_oil() + "份");
            nut_plan.setText(mPlan.getLunch_addition_nut() + "份");
        } else if (position == 5) {
            vegetable_plan.setText(mPlan.getDinner_addition_vegetable() + "份");
            fruit_plan.setText(mPlan.getDinner_addition_fruit() + "份");
            bread_plan.setText(mPlan.getDinner_addition_bread() + "份");
            bean_plan.setText(mPlan.getDinner_addition_bean() + "份");
            milk_plan.setText(mPlan.getDinner_addition_milk() + "份");
            meat_plan.setText(mPlan.getDinner_addition_meat() + "份");
            oil_plan.setText(mPlan.getDinner_addition_oil() + "份");
            nut_plan.setText(mPlan.getDinner_addition_nut() + "份");
        }
    }

    @SuppressLint("SetTextI18n")
    private void loadActualView(int position, Record record) {
        btn_save_plan.setVisibility(View.VISIBLE);
        vegetable_plus.setVisibility(View.VISIBLE);
        vegetable_minus.setVisibility(View.VISIBLE);
        fruit_plus.setVisibility(View.VISIBLE);
        fruit_minus.setVisibility(View.VISIBLE);
        bread_plus.setVisibility(View.VISIBLE);
        bread_minus.setVisibility(View.VISIBLE);
        bean_plus.setVisibility(View.VISIBLE);
        bean_minus.setVisibility(View.VISIBLE);
        milk_plus.setVisibility(View.VISIBLE);
        milk_minus.setVisibility(View.VISIBLE);
        meat_plus.setVisibility(View.VISIBLE);
        meat_minus.setVisibility(View.VISIBLE);
        oil_plus.setVisibility(View.VISIBLE);
        oil_minus.setVisibility(View.VISIBLE);
        nut_plus.setVisibility(View.VISIBLE);
        nut_minus.setVisibility(View.VISIBLE);
        if (position == 0) {
            vegetable_actual.setText(record.getBreakfast_vegetable() + "份");
            fruit_actual.setText(record.getBreakfast_fruit() + "份");
            bread_actual.setText(record.getBreakfast_bread() + "份");
            bean_actual.setText(record.getBreakfast_bean() + "份");
            milk_actual.setText(record.getBreakfast_milk() + "份");
            meat_actual.setText(record.getBreakfast_meat() + "份");
            oil_actual.setText(record.getBreakfast_oil() + "份");
            nut_actual.setText(record.getBreakfast_nut() + "份");
        } else if (position == 1) {
            vegetable_actual.setText(record.getLunch_vegetable() + "份");
            fruit_actual.setText(record.getLunch_fruit() + "份");
            bread_actual.setText(record.getLunch_bread() + "份");
            bean_actual.setText(record.getLunch_bean() + "份");
            milk_actual.setText(record.getLunch_milk() + "份");
            meat_actual.setText(record.getLunch_meat() + "份");
            oil_actual.setText(record.getLunch_oil() + "份");
            nut_actual.setText(record.getLunch_nut() + "份");
        } else if (position == 2) {
            vegetable_actual.setText(record.getDinner_vegetable() + "份");
            fruit_actual.setText(record.getDinner_fruit() + "份");
            bread_actual.setText(record.getDinner_bread() + "份");
            bean_actual.setText(record.getDinner_bean() + "份");
            milk_actual.setText(record.getDinner_milk() + "份");
            meat_actual.setText(record.getDinner_meat() + "份");
            oil_actual.setText(record.getDinner_oil() + "份");
            nut_actual.setText(record.getDinner_nut() + "份");
        } else if (position == 3) {
            vegetable_actual.setText(record.getBreakfast_addition_vegetable() + "份");
            fruit_actual.setText(record.getBreakfast_addition_fruit() + "份");
            bread_actual.setText(record.getBreakfast_addition_bread() + "份");
            bean_actual.setText(record.getBreakfast_addition_bean() + "份");
            milk_actual.setText(record.getBreakfast_addition_milk() + "份");
            meat_actual.setText(record.getBreakfast_addition_meat() + "份");
            oil_actual.setText(record.getBreakfast_addition_oil() + "份");
            nut_actual.setText(record.getBreakfast_addition_nut() + "份");
        } else if (position == 4) {
            vegetable_actual.setText(record.getLunch_addition_vegetable() + "份");
            fruit_actual.setText(record.getLunch_addition_fruit() + "份");
            bread_actual.setText(record.getLunch_addition_bread() + "份");
            bean_actual.setText(record.getLunch_addition_bean() + "份");
            milk_actual.setText(record.getLunch_addition_milk() + "份");
            meat_actual.setText(record.getLunch_addition_meat() + "份");
            oil_actual.setText(record.getLunch_addition_oil() + "份");
            nut_actual.setText(record.getLunch_addition_nut() + "份");
        } else if (position == 5) {
            vegetable_actual.setText(record.getDinner_addition_vegetable() + "份");
            fruit_actual.setText(record.getDinner_addition_fruit() + "份");
            bread_actual.setText(record.getDinner_addition_bread() + "份");
            bean_actual.setText(record.getDinner_addition_bean() + "份");
            milk_actual.setText(record.getDinner_addition_milk() + "份");
            meat_actual.setText(record.getDinner_addition_meat() + "份");
            oil_actual.setText(record.getDinner_addition_oil() + "份");
            nut_actual.setText(record.getDinner_addition_nut() + "份");
        }
    }

    @SuppressLint("SetTextI18n")
    private void loadPreRecordView(int position, PreRecord record) {
        btn_save_plan.setVisibility(View.INVISIBLE);
        vegetable_plus.setVisibility(View.INVISIBLE);
        vegetable_minus.setVisibility(View.INVISIBLE);
        fruit_plus.setVisibility(View.INVISIBLE);
        fruit_minus.setVisibility(View.INVISIBLE);
        bread_plus.setVisibility(View.INVISIBLE);
        bread_minus.setVisibility(View.INVISIBLE);
        bean_plus.setVisibility(View.INVISIBLE);
        bean_minus.setVisibility(View.INVISIBLE);
        milk_plus.setVisibility(View.INVISIBLE);
        milk_minus.setVisibility(View.INVISIBLE);
        meat_plus.setVisibility(View.INVISIBLE);
        meat_minus.setVisibility(View.INVISIBLE);
        oil_plus.setVisibility(View.INVISIBLE);
        oil_minus.setVisibility(View.INVISIBLE);
        nut_plus.setVisibility(View.INVISIBLE);
        nut_minus.setVisibility(View.INVISIBLE);
        if (position == 0) {
            vegetable_actual.setText(record.getBreakfast_vegetable() + "份");
            fruit_actual.setText(record.getBreakfast_fruit() + "份");
            bread_actual.setText(record.getBreakfast_bread() + "份");
            bean_actual.setText(record.getBreakfast_bean() + "份");
            milk_actual.setText(record.getBreakfast_milk() + "份");
            meat_actual.setText(record.getBreakfast_meat() + "份");
            oil_actual.setText(record.getBreakfast_oil() + "份");
            nut_actual.setText(record.getBreakfast_nut() + "份");
        } else if (position == 1) {
            vegetable_actual.setText(record.getLunch_vegetable() + "份");
            fruit_actual.setText(record.getLunch_fruit() + "份");
            bread_actual.setText(record.getLunch_bread() + "份");
            bean_actual.setText(record.getLunch_bean() + "份");
            milk_actual.setText(record.getLunch_milk() + "份");
            meat_actual.setText(record.getLunch_meat() + "份");
            oil_actual.setText(record.getLunch_oil() + "份");
            nut_actual.setText(record.getLunch_nut() + "份");
        } else if (position == 2) {
            vegetable_actual.setText(record.getDinner_vegetable() + "份");
            fruit_actual.setText(record.getDinner_fruit() + "份");
            bread_actual.setText(record.getDinner_bread() + "份");
            bean_actual.setText(record.getDinner_bean() + "份");
            milk_actual.setText(record.getDinner_milk() + "份");
            meat_actual.setText(record.getDinner_meat() + "份");
            oil_actual.setText(record.getDinner_oil() + "份");
            nut_actual.setText(record.getDinner_nut() + "份");
        } else if (position == 3) {
            vegetable_actual.setText(record.getBreakfast_addition_vegetable() + "份");
            fruit_actual.setText(record.getBreakfast_addition_fruit() + "份");
            bread_actual.setText(record.getBreakfast_addition_bread() + "份");
            bean_actual.setText(record.getBreakfast_addition_bean() + "份");
            milk_actual.setText(record.getBreakfast_addition_milk() + "份");
            meat_actual.setText(record.getBreakfast_addition_meat() + "份");
            oil_actual.setText(record.getBreakfast_addition_oil() + "份");
            nut_actual.setText(record.getBreakfast_addition_nut() + "份");
        } else if (position == 4) {
            vegetable_actual.setText(record.getLunch_addition_vegetable() + "份");
            fruit_actual.setText(record.getLunch_addition_fruit() + "份");
            bread_actual.setText(record.getLunch_addition_bread() + "份");
            bean_actual.setText(record.getLunch_addition_bean() + "份");
            milk_actual.setText(record.getLunch_addition_milk() + "份");
            meat_actual.setText(record.getLunch_addition_meat() + "份");
            oil_actual.setText(record.getLunch_addition_oil() + "份");
            nut_actual.setText(record.getLunch_addition_nut() + "份");
        } else if (position == 5) {
            vegetable_actual.setText(record.getDinner_addition_vegetable() + "份");
            fruit_actual.setText(record.getDinner_addition_fruit() + "份");
            bread_actual.setText(record.getDinner_addition_bread() + "份");
            bean_actual.setText(record.getDinner_addition_bean() + "份");
            milk_actual.setText(record.getDinner_addition_milk() + "份");
            meat_actual.setText(record.getDinner_addition_meat() + "份");
            oil_actual.setText(record.getDinner_addition_oil() + "份");
            nut_actual.setText(record.getDinner_addition_nut() + "份");
        }
    }

    @SuppressLint("SetTextI18n")
    private void setActualRecord(int typeNum, int foodNum) {
        mRecord.setCheckTime(time);
        switch (pageNum) {
            case 0:
                switch (typeNum) {
                    case 0:
                        mRecord.setBreakfast_vegetable(foodNum);
                        break;
                    case 1:
                        mRecord.setBreakfast_fruit(foodNum);
                        break;
                    case 2:
                        mRecord.setBreakfast_bread(foodNum);
                        break;
                    case 3:
                        mRecord.setBreakfast_bean(foodNum);
                        break;
                    case 4:
                        mRecord.setBreakfast_milk(foodNum);
                        break;
                    case 5:
                        mRecord.setBreakfast_meat(foodNum);
                        break;
                    case 6:
                        mRecord.setBreakfast_oil(foodNum);
                        break;
                    case 7:
                        mRecord.setBreakfast_nut(foodNum);
                        break;
                }
                breakfastNum = mRecord.getBreakfast_vegetable() +
                        mRecord.getBreakfast_fruit() +
                        mRecord.getBreakfast_bread() +
                        mRecord.getBreakfast_bean() +
                        mRecord.getBreakfast_milk() +
                        mRecord.getBreakfast_meat() +
                        mRecord.getBreakfast_oil() +
                        mRecord.getBreakfast_nut();
                mRecord.setBreakfast_plan(breakfastNum);
                break;
            case 1:
                switch (typeNum) {
                    case 0:
                        mRecord.setLunch_vegetable(foodNum);
                        break;
                    case 1:
                        mRecord.setLunch_fruit(foodNum);
                        break;
                    case 2:
                        mRecord.setLunch_bread(foodNum);
                        break;
                    case 3:
                        mRecord.setLunch_bean(foodNum);
                        break;
                    case 4:
                        mRecord.setLunch_milk(foodNum);
                        break;
                    case 5:
                        mRecord.setLunch_meat(foodNum);
                        break;
                    case 6:
                        mRecord.setLunch_oil(foodNum);
                        break;
                    case 7:
                        mRecord.setLunch_nut(foodNum);
                        break;
                }
                lunchNum = mRecord.getLunch_vegetable() +
                        mRecord.getLunch_fruit() +
                        mRecord.getLunch_bread() +
                        mRecord.getLunch_bean() +
                        mRecord.getLunch_milk() +
                        mRecord.getLunch_meat() +
                        mRecord.getLunch_oil() +
                        mRecord.getLunch_nut();
                mRecord.setLunch_plan(lunchNum);
                break;
            case 2:
                switch (typeNum) {
                    case 0:
                        mRecord.setDinner_vegetable(foodNum);
                        break;
                    case 1:
                        mRecord.setDinner_fruit(foodNum);
                        break;
                    case 2:
                        mRecord.setDinner_bread(foodNum);
                        break;
                    case 3:
                        mRecord.setDinner_bean(foodNum);
                        break;
                    case 4:
                        mRecord.setDinner_milk(foodNum);
                        break;
                    case 5:
                        mRecord.setDinner_meat(foodNum);
                        break;
                    case 6:
                        mRecord.setDinner_oil(foodNum);
                        break;
                    case 7:
                        mRecord.setDinner_nut(foodNum);
                        break;
                }
                dinnerNum = mRecord.getDinner_vegetable() +
                        mRecord.getDinner_fruit() +
                        mRecord.getDinner_bread() +
                        mRecord.getDinner_bean() +
                        mRecord.getDinner_milk() +
                        mRecord.getDinner_meat() +
                        mRecord.getDinner_oil() +
                        mRecord.getDinner_nut();
                mRecord.setDinner_plan(dinnerNum);
                break;
            case 3:
                switch (typeNum) {
                    case 0:
                        mRecord.setBreakfast_addition_vegetable(foodNum);
                        break;
                    case 1:
                        mRecord.setBreakfast_addition_fruit(foodNum);
                        break;
                    case 2:
                        mRecord.setBreakfast_addition_bread(foodNum);
                        break;
                    case 3:
                        mRecord.setBreakfast_addition_bean(foodNum);
                        break;
                    case 4:
                        mRecord.setBreakfast_addition_milk(foodNum);
                        break;
                    case 5:
                        mRecord.setBreakfast_addition_meat(foodNum);
                        break;
                    case 6:
                        mRecord.setBreakfast_addition_oil(foodNum);
                        break;
                    case 7:
                        mRecord.setBreakfast_addition_nut(foodNum);
                        break;
                }
                breakfastAdNum = mRecord.getBreakfast_addition_vegetable() +
                        mRecord.getBreakfast_addition_fruit() +
                        mRecord.getBreakfast_addition_bread() +
                        mRecord.getBreakfast_addition_bean() +
                        mRecord.getBreakfast_addition_milk() +
                        mRecord.getBreakfast_addition_meat() +
                        mRecord.getBreakfast_addition_oil() +
                        mRecord.getBreakfast_addition_nut();
                mRecord.setBreakfast_addition_plan(breakfastAdNum);
                break;
            case 4:
                switch (typeNum) {
                    case 0:
                        mRecord.setLunch_addition_vegetable(foodNum);
                        break;
                    case 1:
                        mRecord.setLunch_addition_fruit(foodNum);
                        break;
                    case 2:
                        mRecord.setLunch_addition_bread(foodNum);
                        break;
                    case 3:
                        mRecord.setLunch_addition_bean(foodNum);
                        break;
                    case 4:
                        mRecord.setLunch_addition_milk(foodNum);
                        break;
                    case 5:
                        mRecord.setLunch_addition_meat(foodNum);
                        break;
                    case 6:
                        mRecord.setLunch_addition_oil(foodNum);
                        break;
                    case 7:
                        mRecord.setLunch_addition_nut(foodNum);
                        break;
                }
                lunchAdNum = mRecord.getLunch_addition_vegetable() +
                        mRecord.getLunch_addition_fruit() +
                        mRecord.getLunch_addition_bread() +
                        mRecord.getLunch_addition_bean() +
                        mRecord.getLunch_addition_milk() +
                        mRecord.getLunch_addition_meat() +
                        mRecord.getLunch_addition_oil() +
                        mRecord.getLunch_addition_nut();
                mRecord.setLunch_addition_plan(lunchAdNum);
                break;
            case 5:
                switch (typeNum) {
                    case 0:
                        mRecord.setDinner_addition_vegetable(foodNum);
                        break;
                    case 1:
                        mRecord.setDinner_addition_fruit(foodNum);
                        break;
                    case 2:
                        mRecord.setDinner_addition_bread(foodNum);
                        break;
                    case 3:
                        mRecord.setDinner_addition_bean(foodNum);
                        break;
                    case 4:
                        mRecord.setDinner_addition_milk(foodNum);
                        break;
                    case 5:
                        mRecord.setDinner_addition_meat(foodNum);
                        break;
                    case 6:
                        mRecord.setDinner_addition_oil(foodNum);
                        break;
                    case 7:
                        mRecord.setDinner_addition_nut(foodNum);
                        break;
                }
                dinnerAdNum = mRecord.getDinner_addition_vegetable() +
                        mRecord.getDinner_addition_fruit() +
                        mRecord.getDinner_addition_bread() +
                        mRecord.getDinner_addition_bean() +
                        mRecord.getDinner_addition_milk() +
                        mRecord.getDinner_addition_meat() +
                        mRecord.getDinner_addition_oil() +
                        mRecord.getDinner_addition_nut();
                mRecord.setDinner_addition_plan(dinnerAdNum);
                break;
        }
    }

    public void setNowProgressBar() {
        switch (pageNum) {
            case 0:
                progressBar.setMax(mPlan.getBreakfast_plan());
                progressBar.setProgress(mRecord.getBreakfast_plan());
                tv_progress.setText(mRecord.getBreakfast_plan() + "/" + mPlan.getBreakfast_plan());
                if (mRecord.getBreakfast_plan() == mPlan.getBreakfast_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_complete);
                } else if (mRecord.getBreakfast_plan() > mPlan.getBreakfast_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_over);
                } else {
                    image_progress.setVisibility(View.INVISIBLE);
                }
                break;
            case 1:
                progressBar.setMax(mPlan.getLunch_plan());
                progressBar.setProgress(mRecord.getLunch_plan());
                tv_progress.setText(mRecord.getLunch_plan() + "/" + mPlan.getLunch_plan());
                if (mRecord.getLunch_plan() == mPlan.getLunch_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_complete);
                } else if (mRecord.getLunch_plan() > mPlan.getLunch_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_over);
                } else {
                    image_progress.setVisibility(View.INVISIBLE);
                }
                break;
            case 2:
                progressBar.setMax(mPlan.getDinner_plan());
                progressBar.setProgress(mRecord.getDinner_plan());
                tv_progress.setText(mRecord.getDinner_plan() + "/" + mPlan.getDinner_plan());
                if (mRecord.getDinner_plan() == mPlan.getDinner_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_complete);
                } else if (mRecord.getDinner_plan() > mPlan.getDinner_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_over);
                } else {
                    image_progress.setVisibility(View.INVISIBLE);
                }
                break;
            case 3:
                progressBar.setMax(mPlan.getBreakfast_addition_plan());
                progressBar.setProgress(mRecord.getBreakfast_addition_plan());
                tv_progress.setText(mRecord.getBreakfast_addition_plan() + "/" + mPlan.getBreakfast_addition_plan());
                if (mRecord.getBreakfast_addition_plan() == mPlan.getBreakfast_addition_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_complete);
                } else if (mRecord.getBreakfast_addition_plan() > mPlan.getBreakfast_addition_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_over);
                } else {
                    image_progress.setVisibility(View.INVISIBLE);
                }
                break;
            case 4:
                progressBar.setMax(mPlan.getLunch_addition_plan());
                progressBar.setProgress(mRecord.getLunch_addition_plan());
                tv_progress.setText(mRecord.getLunch_addition_plan() + "/" + mPlan.getLunch_addition_plan());
                if (mRecord.getLunch_addition_plan() == mPlan.getLunch_addition_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_complete);
                } else if (mRecord.getLunch_addition_plan() > mPlan.getLunch_addition_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_over);
                } else {
                    image_progress.setVisibility(View.INVISIBLE);
                }
                break;
            case 5:
                progressBar.setMax(mPlan.getDinner_addition_plan());
                progressBar.setProgress(mRecord.getDinner_addition_plan());
                tv_progress.setText(mRecord.getDinner_addition_plan() + "/" + mPlan.getDinner_addition_plan());
                if (mRecord.getDinner_addition_plan() == mPlan.getDinner_addition_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_complete);
                } else if (mRecord.getDinner_addition_plan() > mPlan.getDinner_addition_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_over);
                } else {
                    image_progress.setVisibility(View.INVISIBLE);
                }
                break;
        }


    }

    public void setPreProgressBar() {
        switch (pageNum) {
            case 0:
                progressBar.setMax(mPlan.getBreakfast_plan());
                progressBar.setProgress(mPreRecord.getBreakfast_plan());
                tv_progress.setText(mPreRecord.getBreakfast_plan() + "/" + mPlan.getBreakfast_plan());
                if (mPreRecord.getBreakfast_plan() == mPlan.getBreakfast_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_complete);
                } else if (mPreRecord.getBreakfast_plan() > mPlan.getBreakfast_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_over);
                } else {
                    image_progress.setVisibility(View.INVISIBLE);
                }
                break;
            case 1:
                progressBar.setMax(mPlan.getLunch_plan());
                progressBar.setProgress(mPreRecord.getLunch_plan());
                tv_progress.setText(mPreRecord.getLunch_plan() + "/" + mPlan.getLunch_plan());
                if (mPreRecord.getLunch_plan() == mPlan.getLunch_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_complete);
                } else if (mPreRecord.getLunch_plan() > mPlan.getLunch_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_over);
                } else {
                    image_progress.setVisibility(View.INVISIBLE);
                }
                break;
            case 2:
                progressBar.setMax(mPlan.getDinner_plan());
                progressBar.setProgress(mPreRecord.getDinner_plan());
                tv_progress.setText(mPreRecord.getDinner_plan() + "/" + mPlan.getDinner_plan());
                if (mPreRecord.getDinner_plan() == mPlan.getDinner_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_complete);
                } else if (mPreRecord.getDinner_plan() > mPlan.getDinner_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_over);
                } else {
                    image_progress.setVisibility(View.INVISIBLE);
                }
                break;
            case 3:
                progressBar.setMax(mPlan.getBreakfast_addition_plan());
                progressBar.setProgress(mPreRecord.getBreakfast_addition_plan());
                tv_progress.setText(mPreRecord.getBreakfast_addition_plan() + "/" + mPlan.getBreakfast_addition_plan());
                if (mPreRecord.getBreakfast_addition_plan() == mPlan.getBreakfast_addition_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_complete);
                } else if (mPreRecord.getBreakfast_addition_plan() > mPlan.getBreakfast_addition_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_over);
                } else {
                    image_progress.setVisibility(View.INVISIBLE);
                }
                break;
            case 4:
                progressBar.setMax(mPlan.getLunch_addition_plan());
                progressBar.setProgress(mPreRecord.getLunch_addition_plan());
                tv_progress.setText(mPreRecord.getLunch_addition_plan() + "/" + mPlan.getLunch_addition_plan());
                if (mPreRecord.getLunch_addition_plan() == mPlan.getLunch_addition_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_complete);
                } else if (mPreRecord.getLunch_addition_plan() > mPlan.getLunch_addition_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_over);
                } else {
                    image_progress.setVisibility(View.INVISIBLE);
                }
                break;
            case 5:
                progressBar.setMax(mPlan.getDinner_addition_plan());
                progressBar.setProgress(mPreRecord.getDinner_addition_plan());
                tv_progress.setText(mPreRecord.getDinner_addition_plan() + "/" + mPlan.getDinner_addition_plan());
                if (mPreRecord.getDinner_addition_plan() == mPlan.getDinner_addition_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_complete);
                } else if (mPreRecord.getDinner_addition_plan() > mPlan.getDinner_addition_plan()) {
                    image_progress.setVisibility(View.VISIBLE);
                    image_progress.setImageResource(R.mipmap.ic_over);
                } else {
                    image_progress.setVisibility(View.INVISIBLE);
                }
                break;
        }


    }

    @Override
    public void reLoadActualRecord(PreRecord preRecord) {
        mPreRecord = preRecord;
        loadPreRecordView(pageNum, mPreRecord);
        setPreProgressBar();
    }

    @Override
    public void showUpdateResult(int state) {
        if (state == 1) {
            ToastUtil.showShort(getContext(), "记录上传成功");
        } else if (state == -1) {
            ToastUtil.showShort(getContext(), "记录上传失败");
        }
    }


    @Override
    public void showLoading() {
        super.showLoading();
    }

    @Override
    public void dismissLoading() {
        super.dismissLoading();
    }

    @Override
    public void showError(String error) {
        BaseApplication.showShortToast(error);
    }

    @Override
    public void onPause() {
        super.onPause();
        TLog.log("保存处方记录");
        spUtils.putRecord(mRecord);
    }

    @Override
    public void onStop() {
        super.onStop();
        TLog.log("上传处方记录");
        mPresenter.updateRecord(mRecord,
                spUtils.getUser().getResults().getUserid(),
                spUtils.getUser().getResults().getAccess_token(),
                spUtils.getUser().getResults().getHospitalBaseUrl());
    }

}




