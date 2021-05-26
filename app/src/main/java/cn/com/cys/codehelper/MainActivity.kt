package cn.com.cys.codehelper

import android.os.Bundle
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import cn.com.cys.helperlib.ui.HelperBaseImmersiveActivity
import cn.com.cys.helperlib.ui.dialog.HelperEasyDialog
import cn.com.cys.helperlib.ui.dialog.HelperLoadingDialog
import cn.com.cys.helperlib.ui.dialog.HelperNothingDialog
import cn.com.cys.helperlib.ui.widget.view.HelperTitleBar
import cn.com.cys.helperlib.ui.widget.drawable.HelperRadiusShapeDrawable
import cn.com.cys.helperlib.util.bindView

class MainActivity : HelperBaseImmersiveActivity() {

    val titleBar by bindView<HelperTitleBar>(R.id.titlebar)
    val button by bindView<Button>(R.id.button)

    override fun helperBackColorRes(): Int? {
        return R.color.white
    }

    override fun helperStatusColorRes(): Int? {
        return R.color.red
    }

    override fun onActivityCreate(savedInstanceState: Bundle?) {


    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        titleBar.setTextLeft("左边") {
            Toast.makeText(this, "Left", Toast.LENGTH_SHORT).show()
        }
            .setTextCenter("中间")
            .setTextRight("右边")
            .setLeftDrawable(R.mipmap.ic_launcher)
            .setRightDrawable1(R.mipmap.ic_launcher)
            .setRightDrawable2(R.mipmap.ic_launcher)

        button.background = HelperRadiusShapeDrawable(this)
            .setInnerColor(ContextCompat.getColor(this, R.color.red))

        findViewById<Button>(R.id.bt_show_bottomdialog)
            .setOnClickListener {
                val helperBottomDialog =
                    HelperNothingDialog(this)
                val textView = TextView(this)
                textView.layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                )
                textView.text = "文本"
                textView.background = HelperRadiusShapeDrawable(this).setInnerColorRes(R.color.red)
                helperBottomDialog.setContent(textView)
                helperBottomDialog.show()
            }

        findViewById<Button>(R.id.bt_show_dialog)
            .setOnClickListener {
                val helperEasyDialog = HelperEasyDialog(this)
                helperEasyDialog.setTitle("xxxxx")
                helperEasyDialog.setMessage("xxx")
                helperEasyDialog.setPositiveButton("ok", {

                })
                helperEasyDialog.show()
            }

        findViewById<Button>(R.id.bt_show_loading)
            .setOnClickListener {
                val helperLoadingDialog = HelperLoadingDialog(this)
                helperLoadingDialog.loading().show()
            }
    }

    override fun initData() {
    }

    override fun initListener() {
    }
}