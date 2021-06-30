package cn.com.cys.helperlib.util

import android.util.Log
import java.lang.StringBuilder

/**
 * author: Damon
 * data: On 5/25/21
 */
object HelperLog {

    const val TAG = "HelperLog"
    var debug = false
    var showThreadName = false

    fun debug(b: Boolean) {
        debug = true
    }

    fun showRunThread(show: Boolean) {
        showThreadName = show
    }

    fun v(userTag: String = "", msg: String) {
        if (debug) {
            Log.v(TAG, getPrintMsg(userTag, msg))
        }
    }

    fun d(userTag: String = "", msg: String) {
        if (debug) {
            Log.d(TAG, getPrintMsg(userTag, msg))
        }
    }

    fun i(userTag: String = "", msg: String) {
        if (debug) {
            Log.i(TAG, getPrintMsg(userTag, msg))
        }
    }

    fun w(userTag: String = "", msg: String) {
        if (debug) {
            Log.w(TAG, getPrintMsg(userTag, msg))
        }
    }

    fun e(userTag: String = "", msg: String) {
        if (debug) {
            Log.e(TAG, getPrintMsg(userTag, msg))
        }
    }

    private fun getPrintMsg(userTag: String, msg: String): String {
        val sb = StringBuilder()
        sb.append(userTag)
        sb.append("-")
        sb.append(getThreadName())
        sb.append(":")
        sb.append(msg)
        return sb.toString()
    }

    private fun getThreadName(): String {
        if (showThreadName) {
            return Thread.currentThread().name
        }
        return ""
    }

}