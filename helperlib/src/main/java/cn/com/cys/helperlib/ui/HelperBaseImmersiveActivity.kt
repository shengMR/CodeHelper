package cn.com.cys.helperlib.ui

import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import cn.com.cys.helperlib.R
import cn.com.cys.helperlib.util.HelperMetricsUtils
import cn.com.cys.helperlib.util.bindView

/**
 * author: Damon
 * 需要沉浸式界面的可以继承此基类
 *
 * 注意：
 * 继承后，需要在自己的主题文件中，把界面设置成NoActionBar模式的，不然会有个ActionBar出来挡住了界面
 */
abstract class HelperBaseImmersiveActivity : AppCompatActivity() {

    private val base_wrapper by bindView<LinearLayout>(R.id.base_wrapper)
    private val base_status_bar by bindView<View>(R.id.base_status_bar)
    private val base_content_wrapper by bindView<FrameLayout>(R.id.base_content_wrapper)
    protected var statusBarHeight = 0

    protected fun toast(@StringRes id: Int) {
        Toast.makeText(this, getString(id), Toast.LENGTH_SHORT).show()
    }

    protected fun toast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 设置沉浸式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        super.setContentView(R.layout.helper_activity_base)

        // 设置状态栏高度
        val layoutParams: ViewGroup.LayoutParams = base_status_bar.layoutParams
        statusBarHeight = HelperMetricsUtils.getStatusHeight(this)
        layoutParams.height = statusBarHeight
        base_status_bar.layoutParams = layoutParams

        if (getLayoutId() == -1) {
            return
        }
        val view: View =
            LayoutInflater.from(this).inflate(getLayoutId(), base_content_wrapper, false)
        base_content_wrapper.addView(view)

        onActivityCreate(savedInstanceState)
        helperBackColorRes()?.let {
            setBaseBackgroundRes(it)
        }
        helperStatusColorRes()?.let {
            setStatusColorRes(it)
        }
        initView()
        initData()
        initListener()
    }

    // 设置底层布局的背景颜色
    fun setBaseBackgroundRes(resId: Int) {
        base_wrapper.setBackgroundResource(resId)
    }

    fun setBaseBackgroundColor(color: Int) {
        base_wrapper.setBackgroundColor(color)
    }

    // 显示状态栏并设置颜色
    fun setStatusColorRes(resId: Int) {
        if (base_status_bar.visibility != View.VISIBLE) {
            base_status_bar.visibility = View.VISIBLE
        }
        base_status_bar.setBackgroundResource(resId)
    }

    fun setStatusColor(color: Int) {
        if (base_status_bar.visibility != View.VISIBLE) {
            base_status_bar.visibility = View.VISIBLE
        }
        base_status_bar.setBackgroundColor(color)
    }

    abstract fun onActivityCreate(savedInstanceState: Bundle?)

    abstract fun getLayoutId(): Int

    abstract fun initView()

    abstract fun initData()

    abstract fun initListener()

    open fun helperBackColorRes(): Int? {
        return null
    }

    open fun helperStatusColorRes(): Int? {
        return null
    }

}