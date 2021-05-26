package cn.com.cys.helperlib.util

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes

/**
 * author: Damon
 * data: On 4/9/21
 */
fun <T : View> Activity.bindView(@IdRes res: Int): Lazy<T> {
    return lazy { findViewById<T>(res) }
}