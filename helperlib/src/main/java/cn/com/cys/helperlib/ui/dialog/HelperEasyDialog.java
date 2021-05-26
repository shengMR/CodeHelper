package cn.com.cys.helperlib.ui.dialog;


import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import androidx.annotation.StringRes;

import cn.com.cys.helperlib.R;

/**
 * 简易的Dialog
 * <p>
 * // 1,初始化
 * val helperEasyDialog = HelperEasyDialog(this)
 * <p>
 * // 2，设置给Dialog
 * helperEasyDialog.setTitle("title")
 * helperEasyDialog.setMessage("message")
 * helperEasyDialog.setPositiveButton("ok", {
 * Toast("OK")
 * })
 * helperEasyDialog.setNegativeButton("cancel", {
 * Toast("Cancel")
 * })
 * <p>
 * // 3，显示
 * helperEasyDialog.show()
 * <p>
 * // 4，隐藏
 * helperEasyDialog.dismiss()
 */
public class HelperEasyDialog implements IDialog {

    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private TextView txt_title;
    private TextView txt_msg;
    private Button btn_neg;
    private Button btn_pos;
    private ImageView img_line;
    private Display display;
    private boolean showTitle = false;
    private boolean showMsg = false;
    private boolean showPosBtn = false;
    private boolean showNegBtn = false;

    public HelperEasyDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();

        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.helper_dialog_easy_view, null);

        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_title.setVisibility(View.GONE);
        txt_msg = (TextView) view.findViewById(R.id.txt_msg);
        txt_msg.setVisibility(View.GONE);
        btn_neg = (Button) view.findViewById(R.id.btn_neg);
        btn_neg.setVisibility(View.GONE);
        btn_pos = (Button) view.findViewById(R.id.btn_pos);
        btn_pos.setVisibility(View.GONE);
        img_line = (ImageView) view.findViewById(R.id.img_line);
        img_line.setVisibility(View.GONE);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.HelperBottomDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), LayoutParams.WRAP_CONTENT));
    }

    public HelperEasyDialog setTitle(Integer title) {
        return setTitle(context.getResources().getString(title));
    }

    public HelperEasyDialog setTitle(String title) {
        showTitle = true;
        if ("".equals(title)) {
            txt_title.setText(context.getString(R.string.helper_dialog_easy_title));
        } else {
            txt_title.setText(title);
        }
        return this;
    }

    public HelperEasyDialog setTitleTextColor(int color) {
        txt_title.setTextColor(color);
        return this;
    }

    public HelperEasyDialog setMessage(Integer msg) {
        return setMessage(context.getResources().getString(msg));
    }

    public HelperEasyDialog setMessage(String msg) {
        showMsg = true;
        if ("".equals(msg)) {
            txt_msg.setText(context.getString(R.string.helper_dialog_easy_content));
        } else {
            txt_msg.setText(msg);
        }
        return this;
    }

    public HelperEasyDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public HelperEasyDialog setPositiveButton(@StringRes int text,
                                              final OnClickListener listener) {
        return setPositiveButton(context.getResources().getString(text), listener);
    }

    public HelperEasyDialog setPositiveButton(String text,
                                              final OnClickListener listener) {
        showPosBtn = true;
        if ("".equals(text)) {
            btn_pos.setText(context.getString(R.string.helper_dialog_easy_ok));
        } else {
            btn_pos.setText(text);
        }
        btn_pos.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(v);
            }
            dialog.dismiss();
        });
        return this;
    }

    public HelperEasyDialog setNegativeButton(@StringRes int text,
                                              final OnClickListener listener) {
        return setNegativeButton(context.getResources().getString(text), listener);
    }

    public HelperEasyDialog setNegativeButton(String text,
                                              final OnClickListener listener) {
        showNegBtn = true;
        if ("".equals(text)) {
            btn_neg.setText(context.getString(R.string.helper_dialog_easy_cancel));
        } else {
            btn_neg.setText(text);
        }
        btn_neg.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(v);
            }
            dialog.dismiss();
        });
        return this;
    }


    private void setLayout() {
        if (!showTitle && !showMsg) {
            txt_title.setText(context.getString(R.string.helper_dialog_easy_title));
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showTitle) {
            txt_title.setVisibility(View.VISIBLE);
        }

        if (showMsg) {
            txt_msg.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.helper_dialog_easy_bt_right_selector);
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.helper_dialog_easy_bt_left_selector);
            img_line.setVisibility(View.VISIBLE);
        }

        if (showPosBtn && !showNegBtn) {
            btn_pos.setVisibility(View.VISIBLE);
            btn_pos.setBackgroundResource(R.drawable.helper_dialog_easy_bt_single_selector);
        }

        if (!showPosBtn && showNegBtn) {
            btn_neg.setVisibility(View.VISIBLE);
            btn_neg.setBackgroundResource(R.drawable.helper_dialog_easy_bt_single_selector);
        }
    }


    @Override
    public void show() {
        setLayout();
        dialog.show();
    }

    @Override
    public void dismiss() {
        dialog.dismiss();
    }

    @Override
    public boolean isShowing() {
        return dialog.isShowing();
    }
}
