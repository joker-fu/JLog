package com.joker.demo

import com.joker.logs.JLog


object LogUtils {

    fun e(msg: String) {
        //封装工具类使用
        JLog.e(msg, JLog.TRACE_INDEX_5)
    }
}