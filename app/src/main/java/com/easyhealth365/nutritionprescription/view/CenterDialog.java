package com.easyhealth365.nutritionprescription.view;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.easyhealth365.nutritionprescription.R;
import com.easyhealth365.nutritionprescription.utils.TLog;

/**
 * @describe 自定义居中弹出dialog
 */
public class CenterDialog extends Dialog implements View.OnClickListener {

    private Context context;

    private int layoutResID;

    private EditText editText;
    private boolean isWeight;
    private Double height=0.5;
    /**
     * 要监听的控件id
     */
    private int[] listenedItems;

    private OnCenterItemClickListener listener;

    public CenterDialog(Context context, int layoutResID, int[] listenedItems) {
        super(context, R.style.dialog_custom);
        this.context = context;
        this.layoutResID = layoutResID;
        this.listenedItems = listenedItems;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER); // 此处可以设置dialog显示的位置为居中
        window.setWindowAnimations(R.style.bottom_menu_animation); // 添加动画效果
        setContentView(layoutResID);
        // 宽度全屏
        WindowManager windowManager = ((Activity) context).getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.width = display.getWidth() * 6 / 7; // 设置dialog宽度为屏幕的4/5
        lp.height = (int) (display.getHeight() *height);// 设置dialog高度为屏幕的1/3
        getWindow().setAttributes(lp);
        // 点击Dialog外部消失
        setCanceledOnTouchOutside(true);

        for (int id : listenedItems) {
            if (id == R.id.et_current_weight) {
                editText = (EditText) findViewById(id);
                isWeight = true;
            } else {
                findViewById(id).setOnClickListener(this);
            }
        }

    }

    public interface OnCenterItemClickListener {

        void OnCenterItemClick(CenterDialog dialog, View view, String et_weight);

    }

    public void setOnCenterItemClickListener(OnCenterItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        dismiss();
        if (isWeight) {
            listener.OnCenterItemClick(this, view, editText.getText().toString().trim());
        } else {
            listener.OnCenterItemClick(this, view, isWeight + "");
        }
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}

