package cn.com.cys.helperlib.ui.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;

import cn.com.cys.helperlib.R;
import cn.com.cys.helperlib.util.HelperMetricsUtils;

/**
 * author: Damon
 * data: On 5/12/21
 */
public class HelperTitleBar extends LinearLayout {

    private ImageView ivLeft;
    private TextView tvLeft;
    private TextView tvCenter;
    private TextView tvRight;
    private ImageView ivRight1;
    private ImageView ivRight2;

    public HelperTitleBar(Context context) {
        this(context, null);
    }

    public HelperTitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HelperTitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.helper_include_titlebar, null, false);
        addView(inflate);
        tvLeft = inflate.findViewById(R.id.tv_title_left);
        ivLeft = inflate.findViewById(R.id.iv_title_left);
        tvCenter = inflate.findViewById(R.id.tv_title_center);
        tvRight = inflate.findViewById(R.id.tv_title_right);
        ivRight1 = inflate.findViewById(R.id.iv_title_right1);
        ivRight2 = inflate.findViewById(R.id.iv_title_right2);
    }

    public HelperTitleBar setTextLeft(String msg) {
        tvLeft.setPadding(HelperMetricsUtils.dp2px(getContext(), 16), 0, 0, 0);
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText(msg);
        return this;
    }

    public HelperTitleBar setTextLeft(String msg, View.OnClickListener listener) {
        tvLeft.setPadding(HelperMetricsUtils.dp2px(getContext(), 16), 0, 0, 0);
        tvLeft.setVisibility(View.VISIBLE);
        tvLeft.setText(msg);
        if (listener != null) {
            tvLeft.setOnClickListener(listener);
        }
        return this;
    }

    public HelperTitleBar setLeftDrawable(@DrawableRes int id) {
        tvLeft.setPadding(0, 0, 0, 0);
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setImageResource(id);
        return this;
    }

    public HelperTitleBar setLeftDrawable(@DrawableRes int id, View.OnClickListener listener) {
        tvLeft.setPadding(0, 0, 0, 0);
        ivLeft.setVisibility(View.VISIBLE);
        ivLeft.setImageResource(id);
        if (listener != null) {
            ivLeft.setOnClickListener(listener);
        }
        return this;
    }

    public HelperTitleBar setTextCenter(String msg) {
        tvCenter.setVisibility(View.VISIBLE);
        tvCenter.setText(msg);
        return this;
    }

    public HelperTitleBar setTextCenter(String msg, View.OnClickListener listener) {
        tvCenter.setVisibility(View.VISIBLE);
        tvCenter.setText(msg);
        if (listener != null) {
            tvCenter.setOnClickListener(listener);
        }
        return this;
    }

    public HelperTitleBar setTextRight(String msg) {
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(msg);
        return this;
    }

    public HelperTitleBar setTextRight(String msg, View.OnClickListener listener) {
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(msg);
        if (listener != null) {
            tvRight.setOnClickListener(listener);
        }
        return this;
    }

    public HelperTitleBar setRightDrawable1(@DrawableRes int id) {
        ivRight1.setVisibility(View.VISIBLE);
        ivRight1.setImageResource(id);
        return this;
    }

    public HelperTitleBar setRightDrawable1(@DrawableRes int id, View.OnClickListener listener) {
        ivRight1.setVisibility(View.VISIBLE);
        ivRight1.setImageResource(id);
        if (listener != null) {
            ivRight1.setOnClickListener(listener);
        }
        return this;
    }

    public HelperTitleBar setRightDrawable2(@DrawableRes int id) {
        ivRight2.setVisibility(View.VISIBLE);
        ivRight2.setImageResource(id);
        return this;
    }

    public HelperTitleBar setRightDrawable2(@DrawableRes int id, View.OnClickListener listener) {
        ivRight2.setVisibility(View.VISIBLE);
        ivRight2.setImageResource(id);
        if (listener != null) {
            ivRight2.setOnClickListener(listener);
        }
        return this;
    }

    public ImageView getTitleLeftDrawable() {
        return ivLeft;
    }

    public TextView getTitleLeftText() {
        return tvLeft;
    }

    public TextView getTitleCenterText() {
        return tvCenter;
    }

    public TextView getTitleRightText() {
        return tvRight;
    }

    public ImageView getTitleRight1Drawable() {
        return ivRight1;
    }

    public ImageView getTitleRight2Drawable() {
        return ivRight2;
    }
}
