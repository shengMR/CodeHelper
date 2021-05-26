package cn.com.cys.helperlib.util;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;

import java.util.List;

/**
 * 作用：代码中直接设置状态图
 */
public class HelperViewBackgroundUtils {

    // 正常状态
    public static final int[] STATE_NORMAL = {
            -android.R.attr.state_selected,
            -android.R.attr.state_checked,
            -android.R.attr.state_pressed,
    };

    // 选择状态
    public static final int[] STATE_SELECTED = {
            android.R.attr.state_selected,
    };
    // 按下状态
    public static final int[] STATE_PRESS = {
            android.R.attr.state_pressed,
    };
    // 选中状态
    public static final int[] STATE_CHECKED = {
            android.R.attr.state_checked
    };

    /**
     * 设置View的背景
     * @param view View
     * @param state1 状态1
     * @param drawable1 状态1要展示的位图
     * @param state2 状态2
     * @param drawable2 状态2要展示的位图
     */
    public static void viewBgWithState(View view,
                                       int[] state1, Drawable drawable1,
                                       int[] state2, Drawable drawable2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(state1, drawable1);
        stateListDrawable.addState(state2, drawable2);
        view.setBackground(stateListDrawable);
    }

    /**
     * 设置View的背景
     * @param view View
     * @param stateArray 状态数组
     * @param drawables 状态要展示的位图数组
     */
    public static void viewBgWithState(View view,
                                       List<int[]> stateArray,
                                       List<Drawable> drawables) {
        if (stateArray == null || drawables == null) {
            return;
        }
        if (stateArray.size() != drawables.size()) {
            throw new RuntimeException("状态数量和图片数量必须一致");
        }

        StateListDrawable stateListDrawable = new StateListDrawable();
        for (int i = 0; i < stateArray.size(); i++) {
            stateListDrawable.addState(stateArray.get(i), drawables.get(i));
        }
        view.setBackground(stateListDrawable);
    }
}
