package com.example.rongcloud.ui.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.rongcloud.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        conversationListBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val viewId = v!!.id
        when (viewId) {
            R.id.conversationListBtn -> Go2ConversationList()
        }
    }

    fun Go2ConversationList() {
        val intent = Intent(this@MainActivity, ConversastionListActivityExt::class.java)
        this@MainActivity.startActivity(intent)
    }
}
