package cn.com.cys.helperlib.ui.dialog;


import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import cn.com.cys.helperlib.R;

public class HelperLoadingDialog implements IDialog {
    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private TextView tv_icon;
    private TextView tv_content;
    private Display display;

    private RotateAnimation rotateAnimation;

    public HelperLoadingDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();

        rotateAnimation = new RotateAnimation(
                0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(600);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(-1);

        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.helper_dialog_loading_view, null);

        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        tv_icon = (TextView) view.findViewById(R.id.tv_icon);
        tv_content = (TextView) view.findViewById(R.id.tv_content);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.HelperLoadingDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
    }

    public HelperLoadingDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public HelperLoadingDialog setIcon(@DrawableRes int resId) {
        tv_icon.clearAnimation();
        tv_icon.setBackgroundResource(resId);
        return this;
    }

    public HelperLoadingDialog setLoadingIcon(@DrawableRes int resId) {
        tv_icon.setBackgroundResource(resId);
        return this;
    }

    public HelperLoadingDialog loading() {
        tv_icon.startAnimation(rotateAnimation);
        return this;
    }

    public HelperLoadingDialog setContent(@StringRes int resId) {
        tv_content.setText(resId);
        return this;
    }

    public HelperLoadingDialog setContent(String text) {
        tv_content.setText(text);
        return this;
    }

    @Override
    public void dismiss() {
        tv_icon.clearAnimation();
        dialog.dismiss();
    }

    @Override
    public void show() {
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        dialog.show();
    }

    @Override
    public boolean isShowing() {
        return dialog.isShowing();
    }
}
