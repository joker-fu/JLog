package com.joker.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.joker.logs.JLog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var json = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        JLog.i("app is running!")
        button.setOnClickListener {

            Api().post()

            JLog.d("JLog TAG", "aaaaaaaaaaaaaaaaa")

            JLog.w("""{"data":{"curPage":1,"datas":[{"completeDate":null,"completeDateStr":"","content":"突突突","date":1550160000000,"dateStr":"2019-02-15","id":7757,"priority":0,"status":0,"title":"徐局","type":0,"userId":16643}],"offset":0,"over":true,"pageCount":1,"size":20,"total":10},"errorCode":0,"errorMsg":""}""")

            LogUtils.e("封装成自己的Util")
        }
    }
}
