//package com.omoolen.omooroid.login_signup.login
//
//import android.util.Log
////import com.kakao.auth.ISessionCallback
//import com.kakao.network.ErrorResult
//import com.kakao.usermgmt.UserManagement
//import com.kakao.usermgmt.callback.MeV2ResponseCallback
//import com.kakao.usermgmt.response.MeV2Response
//import com.kakao.util.OptionalBoolean
//import com.kakao.util.exception.KakaoException
//
//
//class SessionCallback : ISessionCallback {
//    // 로그인에 성공한 상태
//    override fun onSessionOpened() {
//        requestMe()
//    }
//
//    // 로그인에 실패한 상태
//    override fun onSessionOpenFailed(exception: KakaoException) {
//        Log.e("SessionCallback :: ", "onSessionOpenFailed : " + exception.message)
//    }
//
//    // 사용자 정보 요청
//    fun requestMe() {
//        UserManagement.getInstance()
//            .me(object : MeV2ResponseCallback() {
//                override fun onSessionClosed(errorResult: ErrorResult) {
//                    Log.e("KAKAO_API", "세션이 닫혀 있음: $errorResult")
//                }
//
//                override fun onFailure(errorResult: ErrorResult) {
//                    Log.e("KAKAO_API", "사용자 정보 요청 실패: $errorResult")
//                }
//
//                override fun onSuccess(result: MeV2Response) {
//                    Log.i("KAKAO_API", "사용자 아이디: " + result.id)
//                    val id = result.id.toString()
//                    val kakaoAccount = result.kakaoAccount
//                    if (kakaoAccount != null) {
//
//                        // 이메일
//                        val email = kakaoAccount.email
//                        val profile = kakaoAccount.profile
//                        if (profile == null) {
//                            Log.d("KAKAO_API", "onSuccess:profile null ")
//                        } else {
//                            Log.d(
//                                "KAKAO_API",
//                                "onSuccess:getProfileImageUrl " + profile.profileImageUrl
//                            )
//                            Log.d("KAKAO_API", "onSuccess:getNickname " + profile.nickname)
//                        }
//                        if (email != null) {
//                            Log.d("KAKAO_API", "onSuccess:email $email")
//                        } else if (kakaoAccount.emailNeedsAgreement() == OptionalBoolean.TRUE) {
//                            // 동의 요청 후 이메일 획득 가능
//                            // 단, 선택 동의로 설정되어 있다면 서비스 이용 시나리오 상에서 반드시 필요한 경우에만 요청해야 합니다.
//                            Log.d("KAKAO_API", "onSuccess: 동의 요청 후 이메일 획득 가능")
//                        } else {
//                            // 이메일 획득 불가
//                            Log.d("KAKAO_API", "onSuccess: cannot get email")
//                        }
//
//                        // 프로필
//                        val _profile = kakaoAccount.profile
//                        if (_profile != null) {
//                            Log.d("KAKAO_API", "nickname: " + _profile.nickname)
//                            Log.d("KAKAO_API", "profile image: " + _profile.profileImageUrl)
//                            Log.d("KAKAO_API", "thumbnail image: " + _profile.thumbnailImageUrl)
//                        } else if (kakaoAccount.profileNeedsAgreement() == OptionalBoolean.TRUE) {
//                            // 동의 요청 후 프로필 정보 획득 가능
//                        } else {
//                            // 프로필 획득 불가
//                        }
//                    } else {
//                        Log.i("KAKAO_API", "onSuccess: kakaoAccount null")
//                    }
//                }
//            })
//    }
//}