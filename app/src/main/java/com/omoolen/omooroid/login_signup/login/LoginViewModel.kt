package com.omoolen.omooroid.login_signup.login

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.common.model.KakaoSdkError
import com.kakao.sdk.user.UserApiClient
import com.omoolen.omooroid.login_signup.login.loginApi.RequestLoginData
import com.omoolen.omooroid.login_signup.login.loginApi.ResponseLoginData
import com.omoolen.omooroid.login_signup.login.loginApi.UserClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    var kakaoUser = KakaoUser("","")

    private var _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean>
        get() = _loginSuccess

    fun newKakao(context:Context){
        if (AuthApiClient.instance.hasToken()) { //로그인이 된 상태인지 확인
            UserApiClient.instance.accessTokenInfo { tokenInfo, error -> //서버에 유효한 access토큰이 있는지 가져옴
                //현재 유효한 access토큰이 없음
                //access토큰이 만료된 것이라면 sdk내부에서 accesstoken을 갱신한다.
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError()) {
                        //access토큰 갱신까지 실패한 것이기 때문에 refresh토큰이 유효하지 않음, 로그인 필요
                        Log.e(LOGINVIEWMODEL, "로그인 필요111 / "+error.toString(), error)
                        newKakaoLogin(context)
                    }
                    else {
                        //기타 에러
                        Log.e(LOGINVIEWMODEL, "로그인 실패222 / "+error.toString(), error)
                    }
                }
                else{
                    //토큰 유효성 체크 성공(필요 시 sdk내부에서 토큰 갱신됨)
                    Log.e(LOGINVIEWMODEL, tokenInfo.toString()+" / 로그인 성공333", error)
                    newKakaoLogin(context)
                }
            }
        }
        else {
            //단말에 토큰이 없으니 로그인 필요
            Log.d(LOGINVIEWMODEL, "로그인필요4444")
            newKakaoLogin(context)
        }
    }

    fun newKakaoLogin(context: Context){
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        Toast.makeText(context, "접근이 거부 됨(동의 취소)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(context, "유효하지 않은 앱", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(context, "인증 수단이 유효하지 않아 인증할 수 없는 상태", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(context, "요청 파라미터 오류", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Toast.makeText(context, "유효하지 않은 scope ID", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        Toast.makeText(context, "설정이 올바르지 않음(android key hash)", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        Toast.makeText(context, "서버 내부 에러", Toast.LENGTH_SHORT).show()
                    }
                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        Toast.makeText(context, "앱이 요청 권한이 없음", Toast.LENGTH_SHORT).show()
                    }
                    else -> { // Unknown
                        Toast.makeText(context, "기타 에러", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else if (token != null) {
                Toast.makeText(context, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
                getKakaoInfo()
            }
        }

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            Log.e(LOGINVIEWMODEL, "카카오톡으로")
            UserApiClient.instance.loginWithKakaoTalk(context, callback = callback)
        } else {
            Log.e(LOGINVIEWMODEL, "홈페이지로")
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }

    }

    fun getKakaoInfo(){
        // 사용자 정보 요청 (기본)
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("LOGINVIEWMODEL", "사용자 정보 요청 실패777 / "+error.toString(), error)
            }
            else if (user != null) {
                Log.i("LOGINVIEWMODEL_RESULT", "사용자 정보 요청 성공888" +
                        "\n회원번호: ${user.id}" +
                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
                kakaoUser.name = user.kakaoAccount?.profile?.nickname.toString()
                kakaoUser.oauthKey = user.id.toString()
                Log.d("LOGINVIEWMODEL_RESULT","${kakaoUser.oauthKey} + ${kakaoUser.name}")
                //서버에 요청
                postLogin()
            }
        }
    }

    //서버에 db저장, 토큰 받아오기
    fun postLogin(){
        Log.d("LOGIN","post${kakaoUser.oauthKey} + ${kakaoUser.name}")
        val requestLoginData = RequestLoginData(oauthKey = kakaoUser.oauthKey, name = kakaoUser.name) //전송할 데이터
        val call: Call<ResponseLoginData> = UserClient.getApi.postLogin(requestLoginData)
        call.enqueue(object : Callback<ResponseLoginData> {
            override fun onResponse(
                call: Call<ResponseLoginData>,
                response: Response<ResponseLoginData>
            ){
                Log.d("TEST_LOGINVIEWMODEL",response.isSuccessful.toString())
                Log.d("TEST_LOGINVIEWMODEL",response.body()?.accessToken.toString())
            }

            override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                Log.d("NetworkTest","error:$t")
            }
        })
    }

    fun kakaoLogout() {
        // 로그아웃
        UserApiClient.instance.logout { error ->
            if (error != null) {
                Log.e(LOGINVIEWMODEL, "로그아웃 실패. SDK에서 토큰 삭제됨", error)
            } else {
                Log.i(LOGINVIEWMODEL, "로그아웃 성공. SDK에서 토큰 삭제됨")
            }
            _loginSuccess.value = false
        }
    }

    fun kakaoDisconnect() {
        // 연결 끊기
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                Log.e(LOGINVIEWMODEL, "연결 끊기 실패", error)
            } else {
                Log.i(LOGINVIEWMODEL, "연결 끊기 성공. SDK에서 토큰 삭제 됨")
            }
        }
    }


    companion object {
        val LOGINVIEWMODEL = "LoginViewModel"
    }
}