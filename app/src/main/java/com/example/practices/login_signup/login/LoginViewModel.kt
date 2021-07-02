package com.example.practices.login_signup.login

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient

class LoginViewModel(application: Application) : AndroidViewModel(application){
    val id = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private var _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean>
        get() = _loginSuccess

    lateinit var kakaoUser : KakaoUser

    fun kakaoLogin(context: Context){
        // 로그인 공통 callback 구성
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                //TODO : 카톡 깔려있고 계정 연결 안돼있는 경우 에러 해결 필요
                Log.e(LOGINVIEWMODEL, "로그인 실패", error)
                _loginSuccess.value = false
            }
            else if (token != null) {
                Log.i(LOGINVIEWMODEL, "로그인 성공 ${token.accessToken}")
                _loginSuccess.value = true
            }
        }

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            UserApiClient.instance.loginWithKakaoTalk(context, callback = callback)
        } else {
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
        updateKakaoLogin()
    }


    fun updateKakaoLogin(){
        lateinit var tokenId:String
        // 토큰 정보 보기
        Log.e(LOGINVIEWMODEL, "들어옴")
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) { //로그인 X
                Log.e(LOGINVIEWMODEL, "토큰 정보 보기 실패", error)
                tokenId = ""
            }
            else if (tokenInfo != null) { //로그인 O
                Log.i(LOGINVIEWMODEL, "토큰 정보 보기 성공" +
                        "\n회원번호: ${tokenInfo.id}" +
                        "\n만료시간: ${tokenInfo.expiresIn} 초")
                tokenId = tokenInfo.id.toString()
            }
        }

        // 사용자 정보 요청 (기본)
        UserApiClient.instance.me { user, error ->
            if (error != null) { //로그인 X
                Log.e(LOGINVIEWMODEL, "사용자 정보 요청 실패", error)
                kakaoUser = KakaoUser("","","","","","","")
                _loginSuccess.value = false
            }
            else if (user != null && tokenId != "") { //로그인 O
                Log.i(LOGINVIEWMODEL, "사용자 정보 요청 성공" +
                        "\n회원번호: ${user.id}" +
                        "\n이메일: ${user.kakaoAccount?.email}" +
                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}"+
                        "\n성별: ${user.kakaoAccount?.gender}" +
                        "\n연령대: ${user.kakaoAccount?.ageRange}")

                kakaoUser = KakaoUser(tokenId,user.id.toString(),user.kakaoAccount?.email.toString(),
                        user.kakaoAccount?.profile?.nickname.toString(),user.kakaoAccount?.profile?.thumbnailImageUrl.toString(),
                        user.kakaoAccount?.gender.toString(),user.kakaoAccount?.ageRange.toString())
                _loginSuccess.value = true
                //TODO : 나중에 이 kakaoUser정보를 서버에 보내서 토큰값과, 온보딩 같이 해서 저장
            }
        }
    }

    fun kakaoLogout(){
        // 로그아웃
        UserApiClient.instance.logout{ error ->
            if (error != null) {
                Log.e(LOGINVIEWMODEL, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
            }
            else {
                Log.i(LOGINVIEWMODEL, "로그아웃 성공. SDK에서 토큰 삭제됨")
            }
            _loginSuccess.value = false
        }
    }

    fun kakaoDisconnect(){
        // 연결 끊기
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                Log.e(LOGINVIEWMODEL, "연결 끊기 실패", error)
            }
            else {
                Log.i(LOGINVIEWMODEL, "연결 끊기 성공. SDK에서 토큰 삭제 됨")
            }
        }
    }


    companion object{
        val LOGINVIEWMODEL = "LoginViewModel"
    }
}