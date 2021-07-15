package com.omoolen.omooroid.util

//mport com.kakao.auth.*
import android.app.Application
import android.content.Context
import android.util.Log
import com.kakao.sdk.common.KakaoSdk
import com.omoolen.omooroid.BuildConfig


class GlobalApplication : Application() {
    //var instance: GlobalApplication? = null
    init{
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("GLOBAL", "들어옴")
        // Kakao Sdk 초기화
        KakaoSdk.init(this, BuildConfig.KAKAO_LOGIN_KEY)
    }

    companion object {
        lateinit var instance: GlobalApplication
        fun ApplicationContext() : Context {
            return instance.applicationContext
        }
    }

//    override fun onTerminate() {
//        super.onTerminate()
//        instance = null
//    }
//
//    public fun getUniqueInstance(): Application? {
//        checkNotNull(instance) { "this app illegal state" }
//        return instance
//    }
//
//    class KakaoSDKAdapter : KakaoAdapter() {
//        override fun getSessionConfig(): ISessionConfig {
//            return object : ISessionConfig {
//                override fun getAuthTypes(): Array<AuthType> {
//                    // Kakao SDK로그인을 하는 방식에 대한 Enum class (카카오톡 앱 + 카카오 스토리 + 웹뷰 다이어로그 포함)
//                    return arrayOf(AuthType.KAKAO_LOGIN_ALL)
//                }
//
//                override fun isUsingWebviewTimer(): Boolean {
//                    return false
//                }
//
//                override fun isSecureMode(): Boolean {
//                    return false
//                }
//
//                @Nullable
//                override fun getApprovalType(): ApprovalType? {
//                    return ApprovalType.INDIVIDUAL
//                }
//
//                override fun isSaveFormData(): Boolean {
//                    return true
//                }
//            }
//        }
//
//        override fun getApplicationConfig(): IApplicationConfig? {
//            return IApplicationConfig {
//                GlobalApplication.getUniqueInstance()?.getGlobalApplicationContext()
//            }
//        }
//    }
}