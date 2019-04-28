package com.joker.demo

import com.joker.logs.JLog


object LogUtils {
    init {
        JLog.init(JLog.INDEX_5, "JLOG")
    }

    fun e(msg: String) {
        JLog.e(msg)
    }
}