package cn.com.cys.codehelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import cn.com.cys.helperlib.ui.HelperBaseImmersiveActivity
import cn.com.cys.helperlib.util.HelperWifiUtils

class WifiActivity : HelperBaseImmersiveActivity() {

    lateinit var tv:TextView

    override fun helperBackColorRes(): Int? {
        return R.color.white
    }

    override fun helperStatusColorRes(): Int? {
        return R.color.red
    }

    override fun onActivityCreate(savedInstanceState: Bundle?) {

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_wifi;
    }

    override fun initView() {
        tv = findViewById(R.id.tv)
    }

    override fun initData() {
        var wiFiBroadcastAddress = HelperWifiUtils.getLocalIpV4Address()
        tv.text = wiFiBroadcastAddress
    }

    override fun initListener() {
    }
}