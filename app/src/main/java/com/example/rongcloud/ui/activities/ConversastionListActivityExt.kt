package com.example.rongcloud.ui.activities

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.example.rongcloud.R
import com.example.rongcloud.ui.fragment.ConversationListFragmentExt
import io.rong.imlib.model.Conversation


/**
 * @author shuimu{lwp}
 *
 * @time 2020/4/1  18:17
 *
 * @desc 拓展用的会话列表界面
 *
 */
class ConversastionListActivityExt : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation_list)
        val contentfragment = ConversationListFragmentExt()

        contentfragment.uri = initUri()
        supportFragmentManager.beginTransaction()
            .replace(R.id.base_content_fragment, contentfragment).commit()
    }

    /**
     * 初始化Uri
     */
    fun initUri(): Uri? {
        var uri: Uri? = intent.data
        if (uri == null) {
            uri = Uri.parse("rong://" + applicationInfo.packageName).buildUpon()
                .appendPath("conversationlist")
                .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false")
                .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")
                .appendQueryParameter(
                    Conversation.ConversationType.PUBLIC_SERVICE.getName(),
                    "false"
                )
                .appendQueryParameter(
                    Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(),
                    "false"
                )
                .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")
                .build()
        }
        return uri
    }
}