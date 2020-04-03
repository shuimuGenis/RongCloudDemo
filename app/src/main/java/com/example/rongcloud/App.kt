package com.example.rongcloud

import android.app.Application
import com.example.rongcloud.config.log
import io.rong.imkit.RongExtensionManager
import io.rong.imkit.RongIM
import io.rong.imkit.utils.SystemUtils.getCurProcessName
import io.rong.imlib.RongIMClient

/**
 * @author shuimu{lwp}
 *
 * @time 2020/4/1  16:43
 *
 * @desc
 *
 */
class App : Application() {
    /**
     * 获取APP的对象
     */
    val INSTANCE = this
    /**
     * API调试返回的测试 token：
     * {"code":200,"userId":"123456","token":"HomahvjZYeM2JBdzexA4a0tEaWnC0Fl/TVWLGGRhtYLIaWQihijBJywJHFjV7NusEjzA8sPMSshRnNvVDE04AA=="}
     *
     * {"code":200,"userId":"2222","token":"HmoFmXsrIpT8mi1hyJc7KkymYNzqBLunrIEkXMoaoHtWuT0laC8RVHuwqqBUlg/doNdzCncPJEsDaTe3e3EPHg=="}
     */
    private val TOKEN =
        "Cwsj0Q/hSrYrYw//QgcQ5IY/mUC1iwesf/xLfkF8z7DIoQGUarwxF2w90wZDigMHO1VKAptjBVdl9cSlqMCPnA=="

    private val TOKEN2 =
        "HmoFmXsrIpT8mi1hyJc7KkymYNzqBLunrIEkXMoaoHtWuT0laC8RVHuwqqBUlg/doNdzCncPJEsDaTe3e3EPHg=="

    override fun onCreate() {
        super.onCreate()
        initRong()
    }

    fun initRong() {
        //判断是否等于主线程的情况下才初始化融云连接
        if (applicationInfo.packageName == (getCurProcessName(this))) {
            //初始化融云SDK
            RongIM.init(this)
            /**
             * SDK内部 InternalModuleManager 将会通过反射尝试获取RongCallModule对象并保存为成员变量。
             * 在DefaultExtensionModule中将会尝试从 InternalModuleManager 的getInternalPlugins()方法中获取Modules,
             * 所以,RongCallModule中的语音通话/视频通话 模块能够显示在拓展模块中。
             *
             * 由此可以得出，InternalModuleManager就是融云自己官方内部用于维护和拓展 功能模块用的管理类..只要我们导入对应的包,改包所对应的模块就能够自动被加载
             */
            //连接融云服务
            RongIM.connect(TOKEN2, object : RongIMClient.ConnectCallback() {
                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                override fun onSuccess(userId: String?) {
                    log("融云连接成功：userId = $userId")
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                override fun onError(errorCode: RongIMClient.ErrorCode?) {
                    log("融云连接失败：errorCode = $errorCode")
                }

                /**
                 *  Token 错误。可以从下面两点检查
                 *  1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                override fun onTokenIncorrect() {
                    log("融云Token 错误:(1)Token 是否过期(2)token 对应的 appKey 和工程里设置的 appKey 是否一致")
                }
            })
        }
    }
}