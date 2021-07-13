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
import com.omoolen.omooroid.login_signup.login.loginApi.UserClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var oauthKey : String
    lateinit var name : String

    private var _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean>
        get() = _loginSuccess

    lateinit var kakaoUser: KakaoUser

    fun newKakao(context:Context){
        if (AuthApiClient.instance.hasToken()) { //로그인이 된 상태인지 확인
            UserApiClient.instance.accessTokenInfo { tokenInfo, error -> //서버에 유효한 access토큰이 있는지 가져옴
                //현재 유효한 access토큰이 없음
                //access토큰이 만료된 것이라면 sdk내부에서 accesstoken을 갱신한다.
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError()) {
                        //access토큰 갱신까지 실패한 것이기 때문에 refresh토큰이 유효하지 않음, 로그인 필요
                        Log.e(LOGINVIEWMODEL, "로그인 필요111 / "+error.toString(), error)
                        //kakaoLogin(context)
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
                    getKakaoInfo()
                    //TODO : 홈 activity로 이동
                }
            }
        }
        else {
            //단말에 토큰이 없으니 로그인 필요
            Log.d(LOGINVIEWMODEL, "로그인필요4444")
            //kakaoLogin(context)
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
                //onboarding으로 보내고
                Log.d("LOGINVIEWMODEL",token.toString())
                oauthKey = token.toString()
            }
        }

        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            Log.e(LOGINVIEWMODEL, "카카오톡으로")
            UserApiClient.instance.loginWithKakaoTalk(context, callback = callback)
        } else {
            Log.e(LOGINVIEWMODEL, "홈페이지로")
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
        getKakaoInfo()
    }

    fun kakaoLogin(context: Context) {
        // 로그인 공통 callback 구성
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            Log.e(LOGINVIEWMODEL, "callback 들옴어옴 / " + error.toString(), error)
            if (error != null) {
                //TODO : 카톡 깔려있고 계정 연결 안돼있는 경우 에러 해결 필요
                Log.e(LOGINVIEWMODEL, "로그인 실패555 / " + error.toString(), error)
                //_loginSuccess.value = false
            } else if (token != null) {
                getKakaoInfo()
                Log.i(LOGINVIEWMODEL, "로그인 성공666 / ${token.accessToken}")
                //_loginSuccess.value = true
            }
            else{
                Log.e(LOGINVIEWMODEL, "무언가잘못됏다 / " + error.toString(), error)
            }
        }

        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)) {
            Log.e(LOGINVIEWMODEL, "카카오톡으로")
            UserApiClient.instance.loginWithKakaoTalk(context, callback = callback)
        } else {
            Log.e(LOGINVIEWMODEL, "홈페이지로")
            UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
        }
        //updateKakaoLogin()
        getKakaoInfo()
    }

    fun getKakaoInfo(){
        // 사용자 정보 요청 (기본)
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e("LOGINVIEWMODEL", "사용자 정보 요청 실패777 / "+error.toString(), error)
            }
            else if (user != null) {
                Log.i("LOGINVIEWMODEL", "사용자 정보 요청 성공888" +
                        "\n회원번호: ${user.id}" +
                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")
                name = user.kakaoAccount?.profile?.nickname.toString()
            }
        }
        //getUserApi()
    }

//    @SuppressLint("CheckResult")
//    fun getUserApi(){
//        UserClient.getApi.saveUser()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({items ->
//                newsList.clear()
//                items.data.forEach {
//                    newsList.add(NewsInformation(it.version,it.id,it.event,it.image,it.info,it.place,it.title,it.title_idx))
//                }
//            },{e ->
//                println(e.toString())
//            })
//    }


    fun updateKakaoLogin() {
        lateinit var tokenId: String
        // 토큰 정보 보기
        Log.e(LOGINVIEWMODEL, "들어옴")
//        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
//            if (error != null) { //로그인 X
//                Log.e(LOGINVIEWMODEL, "토큰 정보 보기 실패", error)
//                tokenId = ""
//            } else if (tokenInfo != null) { //로그인 O
//                Log.i(
//                    LOGINVIEWMODEL, "토큰 정보 보기 성공" +
//                            "\n회원번호: ${tokenInfo.id}" +
//                            "\n만료시간: ${tokenInfo.expiresIn} 초"
//                )
//                tokenId = tokenInfo.id.toString()
//            }
//        }

        // 사용자 정보 요청 (기본)
        UserApiClient.instance.me { user, error ->
            if (error != null) { //로그인 X
                Log.e(LOGINVIEWMODEL, "사용자 정보 요청 실패", error)
                kakaoUser = KakaoUser("", "", "", "", "", "", "")
                _loginSuccess.value = false
            } else if (user != null && tokenId != "") { //로그인 O
                Log.i(
                    LOGINVIEWMODEL, "사용자 정보 요청 성공" +
                            "\n회원번호: ${user.id}" +
                            "\n이메일: ${user.kakaoAccount?.email}" +
                            "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                            "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}" +
                            "\n성별: ${user.kakaoAccount?.gender}" +
                            "\n연령대: ${user.kakaoAccount?.ageRange}"
                )

                kakaoUser = KakaoUser(
                    tokenId,
                    user.id.toString(),
                    user.kakaoAccount?.email.toString(),
                    user.kakaoAccount?.profile?.nickname.toString(),
                    user.kakaoAccount?.profile?.thumbnailImageUrl.toString(),
                    user.kakaoAccount?.gender.toString(),
                    user.kakaoAccount?.ageRange.toString()
                )
                _loginSuccess.value = true
                //TODO : 나중에 이 kakaoUser정보를 서버에 보내서 토큰값과, 온보딩 같이 해서 저장
                //토큰값 OK랑 온보딩 완료 OK 값이 모두 TRue일때만 splash엣 홈화면으로 넘어가게 하기
                //sharedpreference로 토큰 저장!!

            }
        }
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