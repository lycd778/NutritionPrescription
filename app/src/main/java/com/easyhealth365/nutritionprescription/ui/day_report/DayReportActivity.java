package com.easyhealth365.nutritionprescription.ui.day_report;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.easyhealth365.nutritionprescription.R;
import com.easyhealth365.nutritionprescription.base.BaseActivity;
import com.easyhealth365.nutritionprescription.beans.Plan;
import com.easyhealth365.nutritionprescription.beans.Record;
import com.easyhealth365.nutritionprescription.utils.SharedPreferenceUtil;
import com.easyhealth365.nutritionprescription.utils.TLog;

import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lingxiao-Ching on 2017/7/21.
 */

public class DayReportActivity extends BaseActivity {
    @BindView(R.id.tv_day_report_realname)
    TextView tv_day_report_realname;
    @BindView(R.id.total_progressBar)
    ProgressBar total_progressBar;
    @BindView(R.id.tv_total_actual_num)
    TextView tv_total_actual_num;
    @BindView(R.id.tv_total_plan_num)
    TextView tv_total_plan_num;
    @BindView(R.id.image_total)
    ImageView image_total;

    @BindView(R.id.cho_progressBar)
    ProgressBar cho_progressBar;
    @BindView(R.id.tv_cho_actual_num)
    TextView tv_cho_actual_num;
    @BindView(R.id.tv_cho_plan_num)
    TextView tv_cho_plan_num;
    @BindView(R.id.image_cho)
    ImageView image_cho;

    @BindView(R.id.pr_progressBar)
    ProgressBar pr_progressBar;
    @BindView(R.id.tv_pr_actual_num)
    TextView tv_pr_actual_num;
    @BindView(R.id.tv_pr_plan_num)
    TextView tv_pr_plan_num;
    @BindView(R.id.image_pr)
    ImageView image_pr;

    @BindView(R.id.fat_progressBar)
    ProgressBar fat_progressBar;
    @BindView(R.id.tv_fat_actual_num)
    TextView tv_fat_actual_num;
    @BindView(R.id.tv_fat_plan_num)
    TextView tv_fat_plan_num;
    @BindView(R.id.image_fat)
    ImageView image_fat;

    @BindView(R.id.progressBar_bmi_1)
    ProgressBar progressBar_bmi_1;
    @BindView(R.id.progressBar_bmi_2)
    ProgressBar progressBar_bmi_2;
    @BindView(R.id.progressBar_bmi_3)
    ProgressBar progressBar_bmi_3;
    @BindView(R.id.progressBar_bmi_4)
    ProgressBar progressBar_bmi_4;
    @BindView(R.id.progressBar_bmi_5)
    ProgressBar progressBar_bmi_5;

    @BindView(R.id.image_bmi_1)
    ImageView image_bmi_1;
    @BindView(R.id.image_bmi_2)
    ImageView image_bmi_2;
    @BindView(R.id.image_bmi_3)
    ImageView image_bmi_3;
    @BindView(R.id.image_bmi_4)
    ImageView image_bmi_4;
    @BindView(R.id.image_bmi_5)
    ImageView image_bmi_5;

    @BindView(R.id.food_recommend)
    TextView food_recommend;
    @BindView(R.id.food_prohibited)
    TextView food_prohibited;
    @BindView(R.id.remark)
    TextView remark;

    SharedPreferenceUtil spUtils = SharedPreferenceUtil.getInstance();
    private Record dRecord = null;
    private Plan dPlan = null;
    private int total_plan_num, total_actual_num, cho_plan_num, cho_actual_num, pr_plan_num, pr_actual_num, fat_plan_num, fat_actual_num;
    private double bmi_num;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_report);
        ButterKnife.bind(this);
        dRecord = spUtils.getRecord();
        dPlan = spUtils.getPlan();
        init();
    }

    public void init() {
        tv_day_report_realname.setText(spUtils.getUser().getResults().getRealname());
        total_plan_num = Integer.parseInt(dPlan.getFoodExchange());
        total_actual_num = dRecord.getBreakfast_plan() + dRecord.getLunch_plan() +
                dRecord.getDinner_plan() + dRecord.getBreakfast_addition_plan() +
                dRecord.getLunch_addition_plan() + dRecord.getDinner_addition_plan();
        tv_total_plan_num.setText("/" + total_plan_num);
        tv_total_actual_num.setText(total_actual_num + "");
        if (total_actual_num < total_plan_num) {
            setProgressBar(total_progressBar, -1);
            image_total.setImageResource(R.mipmap.ic_uncomplete);
        } else if (total_actual_num == total_plan_num) {
            setProgressBar(total_progressBar, 0);
            image_total.setImageResource(R.mipmap.ic_complete);
        } else if (total_actual_num > total_plan_num) {
            setProgressBar(total_progressBar, 1);
            image_total.setImageResource(R.mipmap.ic_over);
            tv_total_actual_num.setTextColor(this.getResources().getColor(R.color.red));
        }
        total_progressBar.setMax(total_plan_num);
        total_progressBar.setProgress(total_actual_num);


        cho_plan_num = dPlan.getCHO();
        cho_actual_num = dRecord.getBreakfast_vegetable() + dRecord.getLunch_vegetable() +
                dRecord.getDinner_vegetable() + dRecord.getBreakfast_addition_vegetable() +
                dRecord.getLunch_addition_vegetable() + dRecord.getDinner_addition_vegetable() +
                dRecord.getBreakfast_fruit() + dRecord.getLunch_fruit() +
                dRecord.getDinner_fruit() + dRecord.getBreakfast_addition_fruit() +
                dRecord.getLunch_addition_fruit() + dRecord.getDinner_addition_fruit() +
                dRecord.getBreakfast_bread() + dRecord.getLunch_bread() +
                dRecord.getDinner_bread() + dRecord.getBreakfast_addition_bread() +
                dRecord.getLunch_addition_bread() + dRecord.getDinner_addition_bread();
        tv_cho_plan_num.setText("/" + cho_plan_num);
        tv_cho_actual_num.setText(cho_actual_num + "");
        if (cho_actual_num < cho_plan_num) {
            setProgressBar(cho_progressBar, -1);
            image_cho.setImageResource(R.mipmap.ic_uncomplete);
        } else if (cho_actual_num == cho_plan_num) {
            setProgressBar(cho_progressBar, 0);
            image_cho.setImageResource(R.mipmap.ic_complete);
        } else if (cho_actual_num > cho_plan_num) {
            setProgressBar(cho_progressBar, 1);
            image_cho.setImageResource(R.mipmap.ic_over);
            tv_cho_actual_num.setTextColor(this.getResources().getColor(R.color.red));
        }
        cho_progressBar.setMax(cho_plan_num);
        cho_progressBar.setProgress(cho_actual_num);


        pr_plan_num = dPlan.getPR();
        pr_actual_num = dRecord.getBreakfast_bean() + dRecord.getLunch_bean() +
                dRecord.getDinner_bean() + dRecord.getBreakfast_addition_bean() +
                dRecord.getLunch_addition_bean() + dRecord.getDinner_addition_bean() +
                dRecord.getBreakfast_milk() + dRecord.getLunch_milk() +
                dRecord.getDinner_milk() + dRecord.getBreakfast_addition_milk() +
                dRecord.getLunch_addition_milk() + dRecord.getDinner_addition_milk() +
                dRecord.getBreakfast_meat() + dRecord.getLunch_meat() +
                dRecord.getDinner_meat() + dRecord.getBreakfast_addition_meat() +
                dRecord.getLunch_addition_meat() + dRecord.getDinner_addition_meat();
        tv_pr_plan_num.setText("/" + pr_plan_num);
        tv_pr_actual_num.setText(pr_actual_num + "");
        if (pr_actual_num < pr_plan_num) {
            setProgressBar(pr_progressBar, -1);
            image_pr.setImageResource(R.mipmap.ic_uncomplete);
        } else if (pr_actual_num == pr_plan_num) {
            setProgressBar(pr_progressBar, 0);
            image_pr.setImageResource(R.mipmap.ic_complete);
        } else if (pr_actual_num > pr_plan_num) {
            setProgressBar(pr_progressBar, 1);
            image_pr.setImageResource(R.mipmap.ic_over);
            tv_pr_actual_num.setTextColor(this.getResources().getColor(R.color.red));
        }
        pr_progressBar.setMax(pr_plan_num);
        pr_progressBar.setProgress(pr_actual_num);


        fat_plan_num = dPlan.getFat();
        fat_actual_num = dRecord.getBreakfast_oil() + dRecord.getLunch_oil() +
                dRecord.getDinner_oil() + dRecord.getBreakfast_addition_oil() +
                dRecord.getLunch_addition_oil() + dRecord.getDinner_addition_oil() +
                dRecord.getBreakfast_nut() + dRecord.getLunch_nut() +
                dRecord.getDinner_nut() + dRecord.getBreakfast_addition_nut() +
                dRecord.getLunch_addition_nut() + dRecord.getDinner_addition_nut();
        tv_fat_plan_num.setText("/" + fat_plan_num);
        tv_fat_actual_num.setText(fat_actual_num + "");
        fat_progressBar.setMax(45);
        fat_progressBar.setProgress(30);
        if (fat_actual_num < fat_plan_num) {
            setProgressBar(fat_progressBar, -1);
            image_fat.setImageResource(R.mipmap.ic_uncomplete);
        } else if (fat_actual_num == fat_plan_num) {
            setProgressBar(fat_progressBar, 0);
            image_fat.setImageResource(R.mipmap.ic_complete);
        } else if (fat_actual_num > fat_plan_num) {
            setProgressBar(fat_progressBar, 1);
            image_fat.setImageResource(R.mipmap.ic_over);
            tv_fat_actual_num.setTextColor(this.getResources().getColor(R.color.red));
        }

        bmi_num = Double.parseDouble(dPlan.getBmi());
        TLog.log("bmi_num: " + bmi_num);
        if (0 < bmi_num - 18.5) {
            progressBar_bmi_1.setMax(7);
            int x1 = (int) ((bmi_num - 18.5) * 2);
            progressBar_bmi_1.setProgress(x1);
            image_bmi_1.setVisibility(View.VISIBLE);
        }
        if (bmi_num - 18.5 > 3.5) {
            setBmiProgressBar(progressBar_bmi_1);
            progressBar_bmi_1.setMax(2);
            int x2 = (int) (bmi_num - 22);
            progressBar_bmi_1.setProgress(x2);
            image_bmi_2.setVisibility(View.VISIBLE);
        }
        if (bmi_num - 22 > 2) {
            setBmiProgressBar(progressBar_bmi_2);
            progressBar_bmi_1.setMax(2);
            int x3 = (int) (bmi_num - 24);
            progressBar_bmi_1.setProgress(x3);
            image_bmi_3.setVisibility(View.VISIBLE);
        }
        if (bmi_num - 24 > 4) {
            setBmiProgressBar(progressBar_bmi_3);
            progressBar_bmi_1.setMax(4);
            int x4 = (int) (bmi_num - 28);
            progressBar_bmi_1.setProgress(x4);
            image_bmi_4.setVisibility(View.VISIBLE);
        }
        if (bmi_num - 28 > 2) {
            setBmiProgressBar(progressBar_bmi_4);
            progressBar_bmi_1.setMax(4);
            int x4 = (int) (bmi_num - 30);
            progressBar_bmi_1.setProgress(x4);
            image_bmi_5.setVisibility(View.VISIBLE);
        }
        if (bmi_num - 28 > 4) {
            setBmiProgressBar(progressBar_bmi_5);
        }
        food_recommend.setText(dPlan.getFoodRecommend());
        food_prohibited.setText(dPlan.getFoodProhibited());
        remark.setText(dPlan.getRemark());
    }

    private void setProgressBar(ProgressBar progressBar, int state) {
        switch (state) {
            case -1:
                Drawable uDrawable = progressBar.getResources().getDrawable(R.drawable.progressbar_uncomplete);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    progressBar.setProgressDrawableTiled(this.getResources().getDrawable(R.drawable.progressbar_uncomplete));
                } else {
                    Drawable d = getMethod("tileify", progressBar, new Object[]{uDrawable, false});
                    progressBar.setProgressDrawable(d);
                }
                break;
            case 0:
                Drawable cDrawable = progressBar.getResources().getDrawable(R.drawable.progressbar_complete);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    progressBar.setProgressDrawableTiled(this.getResources().getDrawable(R.drawable.progressbar_complete));
                } else {
                    Drawable d = getMethod("tileify", progressBar, new Object[]{cDrawable, false});
                    progressBar.setProgressDrawable(d);
                }
                break;
            case 1:
                Drawable oDrawable = progressBar.getResources().getDrawable(R.drawable.progressbar_over);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    progressBar.setProgressDrawableTiled(this.getResources().getDrawable(R.drawable.progressbar_over));
                } else {
                    Drawable d = getMethod("tileify", progressBar, new Object[]{oDrawable, false});
                    progressBar.setProgressDrawable(d);
                }
                break;

        }
    }

    private void setBmiProgressBar(ProgressBar progressBar) {
        Drawable uDrawable = progressBar.getResources().getDrawable(R.drawable.progressbar_bmi_complete);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            progressBar.setProgressDrawableTiled(this.getResources().getDrawable(R.drawable.progressbar_bmi_complete));
        } else {
            Drawable d = getMethod("tileify", progressBar, new Object[]{uDrawable, false});
            progressBar.setProgressDrawable(d);
        }

    }

    private static Drawable getMethod(String MethodName, Object o, Object[] paras) {
        Drawable newDrawable = null;
        try {
            Class c[] = new Class[2];
            c[0] = Drawable.class;
            c[1] = boolean.class;
            Method method = ProgressBar.class.getDeclaredMethod(MethodName, c);
            method.setAccessible(true);
            newDrawable = (Drawable) method.invoke(o, paras);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return newDrawable;
    }
}
