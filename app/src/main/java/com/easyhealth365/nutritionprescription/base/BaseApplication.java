package com.easyhealth365.nutritionprescription.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.easyhealth365.nutritionprescription.R;
import com.easyhealth365.nutritionprescription.utils.SharedPreferenceUtil;
import com.squareup.leakcanary.LeakCanary;

/**
 * 获取上下文,Toast,以及各种初始化
 * Created by Administrator on 2016/7/5.
 */
public class BaseApplication extends Application {

    private static String lastToast = "";
    private static long lastToastTime;
    private static Context context;
    private static Resources resource;
    private static BaseApplication baseApplication;
    private String dexPath;

    public static synchronized BaseApplication getInstance() {
        return baseApplication;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getBaseContext();
        baseApplication = this;
        //检测内存泄露
        LeakCanary.install(this);

        SharedPreferenceUtil.initSharedPreference(getApplicationContext());
    }



















    /**
     * 防抖动 弹窗
     *
     * @param message
     * @param duration
     * @param icon
     * @param gravity
     */
    public static void showToast(String message, int duration, int icon,
                                 int gravity) {
        if (message != null && !message.equalsIgnoreCase("")) {
            long time = System.currentTimeMillis();
            if (!message.equalsIgnoreCase(lastToast)
                    || Math.abs(time - lastToastTime) > 2000) {
                View view = LayoutInflater.from(context).inflate(
                        R.layout.view_toast, null);
                ((TextView) view.findViewById(R.id.title_tv)).setText(message);
                if (icon != 0) {
                    ((ImageView) view.findViewById(R.id.icon_iv))
                            .setImageResource(icon);
                    view.findViewById(R.id.icon_iv)
                            .setVisibility(View.VISIBLE);
                }
                Toast toast = new Toast(context);
                toast.setView(view);
                if (gravity == Gravity.CENTER) {
                    toast.setGravity(gravity, 0, 0);
                } else {
                    toast.setGravity(gravity, 0, 35);
                }

                toast.setDuration(duration);
                toast.show();
                lastToast = message;
                lastToastTime = System.currentTimeMillis();
            }
        }
    }




    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        BaseApplication.context = context;
    }

    public static Resources getResource() {
        return resource;
    }

    public static void setResource(Resources resource) {
        BaseApplication.resource = resource;
    }


    public static void showShortToast(String message) {
        showToast(message, Toast.LENGTH_SHORT, 0, Gravity.BOTTOM);
    }

    public static void showLongToast(String message) {
        showToast(message, Toast.LENGTH_LONG, 0, Gravity.BOTTOM);
    }

    public static void showToast(String message) {
        showToast(message, Toast.LENGTH_LONG, 0, Gravity.BOTTOM);
    }

}
