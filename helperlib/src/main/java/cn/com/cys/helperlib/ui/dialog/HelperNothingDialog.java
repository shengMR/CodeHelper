package cn.com.cys.helperlib.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.IntDef;

import cn.com.cys.helperlib.R;

/**
 * 可自由设置布局和弹出方向的Dialog
 * <p>
 * // 1,初始化
 * val helperBottomDialog = HelperBottomDialog(this)
 * <p>
 * // 2，创建需要显示的界面
 * val textView = TextView(this)
 * textView.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
 * textView.text = "文本"
 * textView.background = HelperRadiusShapeDrawable(this).setInnerColorRes(R.color.red)
 * <p>
 * // 3，设置给Dialog
 * helperBottomDialog.setContent(textView)
 * <p>
 * // 4，显示
 * helperBottomDialog.show()
 * <p>
 * // 5，隐藏
 * helperBottomDialog.dismiss()
 */
public class HelperNothingDialog implements IDialog{

    public final static int BOTTOM = Gravity.BOTTOM;
    public final static int CENTER = Gravity.CENTER;
    public final static int TOP = Gravity.TOP;

    @IntDef({BOTTOM, CENTER, TOP})
    public @interface DialogGravity {

    }

    private Context context;
    private Dialog dialog;
    private LinearLayout lLayout_bg;
    private FrameLayout flContent;
    private View contentView;
    private Display display;
    private int gravity = CENTER;

    public HelperNothingDialog(Context context) {
        this.context = context;
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();

        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(
                R.layout.helper_dialog_nothing_view, null);

        // 获取自定义Dialog布局中的控件
        lLayout_bg = (LinearLayout) view.findViewById(R.id.lLayout_bg);
        flContent = (FrameLayout) view.findViewById(R.id.content);
        flContent.setVisibility(View.VISIBLE);

        // 定义Dialog布局和参数
        dialog = new Dialog(context, R.style.HelperBottomDialogStyle);
        dialog.setContentView(view);

        // 调整dialog背景大小
        lLayout_bg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 1f), LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    public HelperNothingDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public HelperNothingDialog setContent(int resLayoutId) {
        flContent.removeAllViews();
        contentView = LayoutInflater.from(context).inflate(resLayoutId, null, false);
        flContent.addView(contentView);
        return this;
    }

    public HelperNothingDialog setContent(View view) {
        flContent.removeAllViews();
        contentView = view;
        flContent.addView(contentView);
        return this;
    }

    public HelperNothingDialog setGravity(@DialogGravity int gravity) {
        this.gravity = gravity;
        return this;
    }

    public View getContentView() {
        return contentView;
    }

    @Override
    public void dismiss() {
        dialog.dismiss();
    }

    @Override
    public void show() {
        Window window = dialog.getWindow();
        window.setGravity(gravity);
        dialog.show();
    }

    @Override
    public boolean isShowing() {
        return dialog.isShowing();
    }
}
