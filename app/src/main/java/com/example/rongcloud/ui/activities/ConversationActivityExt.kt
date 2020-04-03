package com.example.rongcloud.ui.activities

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.example.rongcloud.R
import com.example.rongcloud.ui.fragment.ConversationFragmentExt

/**
 * @author shuimu{lwp}
 *
 * @time 2020/4/2  14:23
 *
 * @desc 用来承载会话界面的Activity
 *
 */

class ConversationActivityExt : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation)
        val fragmentExt = ConversationFragmentExt()
        supportFragmentManager.beginTransaction().replace(R.id.base_content_fragment, fragmentExt)
            .commit()
    }
}