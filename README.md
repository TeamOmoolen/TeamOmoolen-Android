<img src="https://user-images.githubusercontent.com/67837091/125954812-8c015a7d-d97c-4074-abe6-728d84ffd223.png" /></br>

## <img src="https://user-images.githubusercontent.com/49470328/125881901-5c857059-403a-4ba3-8d14-bacf11ba8f0b.png" width="75px"/> 오늘은 무슨 렌즈? - Om👀len
> **콘택트렌즈 사용자를 위한 맞춤 렌즈 추천 및 오프라인 픽업 예약 서비스**
>
> 국내 모든 렌즈 정보, 리뷰 부터  의료 커뮤니티와 오프라인 픽업 예약 까지! "오늘 무슨 렌즈끼지?" 고민될 땐, **오무렌!**

> **SOPT 28th APPJAM**
> 
> 프로젝트 기간: 2021.06.26 ~ 2021.07.17

<img src="https://user-images.githubusercontent.com/67837091/125959361-0a2376fb-25e9-44d5-ac68-32fb60e72382.png" />

### 📄 IA
<kbd>
  <img src="https://user-images.githubusercontent.com/67837091/125954645-aafd7754-5991-4eef-ae1b-fb34d979a1e8.jpg" />
</kbd>

## 🔍  Main Function

<table>
  <tr>
    <td><img width="300" src="https://user-images.githubusercontent.com/49470328/126744328-731c89f5-5298-443c-8483-6c044ce69017.gif"></td>
    <td><img width="300" src="https://user-images.githubusercontent.com/49470328/126743469-2a88990e-101e-430c-a03d-b5996ecf8d94.gif"></td>
  </tr>
  <tr>
    <td align="center"><b>Splash</b></td>
    <td align="center"><b>카카오톡 로그인</b></td>
    
  </tr>
</table>
<table>
  <tr>
    <td><img width="300" src="https://user-images.githubusercontent.com/49470328/126744442-9d0afd4a-6d6d-4819-a2ef-a205e4439f9b.gif"></td>
    <td><img width="300" src="https://user-images.githubusercontent.com/49470328/126744448-913d9716-2b92-48ed-b26d-2abe3965b7b8.gif"></td>
    <td><img width="300" src="https://user-images.githubusercontent.com/49470328/126744388-2fb5d1f6-bf6c-4081-adff-0226aa925293.gif"></td>
  </tr>
  <tr>
    <td align="center"><b>온보딩1,2</b></td>
    <td align="center"><b>온보딩3</b></td>
    <td align="center"><b>온보딩4</b></td>
  </tr>
</table>
<table>
  <tr>
    <td><img width="300" src=""></td>
    <td><img width="300" src=""></td>
    <td><img width="300" src="https://user-images.githubusercontent.com/49470328/126745744-e1678a22-eb7a-4fd9-8f04-4e2971166601.mp4"></td>
  </tr>
  <tr>
    <td align="center"><b>홈</b></td>
    <td align="center"><b>발견</b></td>
    <td align="center"><b>제품 상세</b></td>
  </tr>
</table>
<table>
  <tr>
   <td><img width="300" src="https://user-images.githubusercontent.com/49470328/126745083-5f2178cc-34f6-437d-b4d2-73c46b7ff763.gif"></td>
   <td><img width="300" src="https://user-images.githubusercontent.com/49470328/126744812-551cc494-49a7-4d8f-b6e6-c4ff15a76527.gif"></td>
  </tr>
  <tr>
    <td align="center"><b>최근 검색</b></td>
    <td align="center"><b>필터 검색</b></td>
  </tr>
</table>
<br><br>




### **1. Kakaotalk Login**
  - 카카오톡을 이용하여 소셜 로그인을 합니다.
    <details>
    <summary>Show Details</summary>
    <div markdown="1">       
      
      ✔ 구현 방법        
      ---
      
      ◾ Kakaotalk Login
      
      🧾 LoginViewModel.kt
      1. 단말 로그인 상태 확인      
      ```kotlin
      
      fun newKakao(context:Context){
        if (AuthApiClient.instance.hasToken()) { //로그인이 된 상태인지 확인
            UserApiClient.instance.accessTokenInfo { tokenInfo, error -> //서버에 유효한 access토큰이 있는지 가져옴
                //현재 유효한 access토큰이 없음
                //access토큰이 만료된 것이라면 sdk내부에서 accesstoken을 갱신한다.
                if (error != null) {
                    if (error is KakaoSdkError && error.isInvalidTokenError()) {
                        //access토큰 갱신까지 실패한 것이기 때문에 refresh토큰이 유효하지 않음, 로그인 필요
                        newKakaoLogin(context)
                    }
                    else {
                        //기타 에러
                    }
                }
                else{
                    //토큰 유효성 체크 성공(필요 시 sdk내부에서 토큰 갱신됨)
                    newKakaoLogin(context)
                }
            }
        }
        else {
            //단말에 토큰이 없으니 로그인 필요
            newKakaoLogin(context)
        }
    }
      
      ```   
       2. 카카오톡 설치 여부 확인 후 로그인 창으로 이동  
      ```kotlin   
      
      fun newKakaoLogin(context: Context){
            val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
                if (error != null) {
                    when {
                    }
                }
                else if (token != null) {
                    //Toast.makeText(context, "로그인에 성공하였습니다.", Toast.LENGTH_SHORT).show()
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
      ```
      
      3. 사용자 정보 요청
      ```kotlin   
      
         fun getKakaoInfo(){
          // 사용자 정보 요청 (기본)
          UserApiClient.instance.me { user, error ->
              if (error != null) {
                  Log.e("LOGINVIEWMODEL", "사용자 정보 요청 실패 / "+error.toString(), error)
              }
              else if (user != null) {
                  Log.i("LOGINVIEWMODEL_RESULT", "사용자 정보 요청 성공" +
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

      ```
      4. 서버에 사용자 정보 전송 및 자동 로그인 위한 sharedpreference 설정을 진행합니다. 
         (splash화면에서 로그인 내역이 존재하면 바로 homeActivity로, 아니면 loginActivity로 intent)
      ```kotlin
      
      fun postLogin(){
        Log.d("LOGIN","post${kakaoUser.oauthKey} + ${kakaoUser.name}")
        val requestLoginData = RequestLoginData(oauthKey = kakaoUser.oauthKey, name = kakaoUser.name) //전송할 데이터
        val call: Call<ResponseLoginData> = UserClient.getApi.postLogin(requestLoginData)
        call.enqueue(object : Callback<ResponseLoginData> {
            override fun onResponse(
                call: Call<ResponseLoginData>,
                response: Response<ResponseLoginData>
            ){
                //token값 저장
                SharedPreferenceToken.putSettingItem(getApplication<Application>().applicationContext,"USER_TOKEN",response.body()?.accessToken.toString())
                isNew.value = response.body()?.isNewUser
            }
            override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                Log.d("NetworkTest","error:$t")
            }
        })
    }
      ```

    </div>
    </details>
  
### **2. Onboarding**
  - 사용자 맞춤 큐레이션을 제공하기 위해 온보딩 과정을 수행합니다.
    <details>
    <summary>Show Details</summary>
    <div markdown="1">       
      
      ✔ 구현 방법        
      ---
      
      ◾ Onboarding
      
      🧾 OnboardDatabase.kt
      - onboardData라는 객체를 singletone으로 생성하여 4개의 fragment에서 한 객체를 공유하도록 하였습니다.   
      (각 화면에서 얻은 정보들을 한 객체에 넣어 서버 전달)
      ```kotlin
      class OnboardDatabase {
      //싱글톤 객체 생성
      companion object{
          lateinit var onboardData:OnboardData
      }
      fun getOnboardData():OnboardData{
          return onboardData
      } 
      //...
      }
      ```
      
      ◾ 각 fragment에서 recyclerView를 이용하여 버튼을 구성하였습니다.
      - RecyclerView SingleChoice.  
      : 각 recyclerView의 adapter에 single choice를 위한 Interface를 정의한 후 fragment에서 해당하는 setOnClickListener를 달아 사용합니다.
      
      🧾 AgeAdapter.kt
      ```kotlin
      class AgeAdapter : RecyclerView.Adapter<AgeAdapter.MyViewHolder>() {
    val ageList = mutableListOf<AgeInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemOnboardTextBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = ageList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(ageList[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }
    // (2) 리스너 인터페이스
    interface OnItemClickListener {
        fun onClick(v: View, position: Int)
    }
    // (3) 외부에서 클릭 시 이벤트 설정
    fun setItemClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }
    // (4) setItemClickListener로 설정한 함수 실행
    private lateinit var itemClickListener : OnItemClickListener

    class MyViewHolder(
        private val binding: ItemOnboardTextBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(ageInfo: AgeInfo) {
            binding.tvText.text = ageInfo.age
        }
    }
    }
      ```
      
      🧾 OneOnboardFragment.kt
      ```kotlin
        private fun singleChoice() {
        binding.rvGender.addOnItemTouchListener(object :
            RecyclerView.OnItemTouchListener {
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                if (e.action == MotionEvent.ACTION_MOVE) { }
                else viewModel.genderSingleChoice(rv,e)
                return false
            }
            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
      ```
      
      <br>

    </div>
    </details>
    
### **3. Home**
  - 사용자 맞춤 큐레이션, 이벤트, 상식 등을 간략히 모아볼 수 있습니다. 
    <details>
    <summary>Show Details</summary>
    <div markdown="1">       
      
      ✔ 구현 방법        
      ---
      
      ◾ 큐레이션,이벤트,상식 등의 정보들을 recyclerView를 이용하여 구성하였습니다.
      
    </div>
    </details>
      
### **4. 발견**
  - 사용자 맞춤 큐레이션을 한눈에 모아볼 수 있습니다.
    <details>
    <summary>Show Details</summary>
    <div markdown="1">       
      
      ✔ 구현 방법        
      ---
      
      ◾ Kakaotalk Login
      

      ✔ 구현 코드
      ---
      
      ◾ Login  
      
      🧾 UserClient.kt
            
      ```kotlin
      
      data class KakaoUser(
            var oauthKey: String,
            var name: String
        )
      
      ```
      <br>

    </div>
    </details>    
    
### **5. 상품 상세**
  - 상품의 상세 정보를 제공합니다.
    <details>
    <summary>Show Details</summary>
    <div markdown="1">       
      
      ✔ 구현 방법        
      ---
      
      ◾ ViewPager2
        
        - 이미지 스와이프 전환을 위해 ViewPager2를 사용
      
      ◾ DotsIndicator
        
        - TabLayout의 Indicator custom
      
      
      ✔ 구현 코드
      ---
      
      ◾ ViewPager2
        - 이미지 스와이프 전환을 위해 ViewPager2를 사용
      
      🧾 UserClient.kt
            
      ```kotlin
      
      data class KakaoUser(
            var oauthKey: String,
            var name: String
        )
      
      ```
      <br>
      
      ◾ DotsIndicator
        - TabLayout의 Indicator custom
      
      🧾 UserClient.kt
            
      ```kotlin
      
      data class KakaoUser(
            var oauthKey: String,
            var name: String
        )
      
      ```
      <br>

    </div>
    </details> 
    
### **6. 검색 상세**
  - 검색 결과를 제공합니다.
    <details>
    <summary>Show Details</summary>
    <div markdown="1">       
      
      ✔ 구현 방법        
      ---
      
      ◾ Kakaotalk Login
      

      ✔ 구현 코드
      ---
      
      ◾ Login  
      
      🧾 UserClient.kt
            
      ```kotlin
      
      data class KakaoUser(
            var oauthKey: String,
            var name: String
        )
      
      ```
      <br>

    </div>
    </details>

### **7. 검색 (키워드/최근, 필터)**
  - 키워드를 이용하여 검색을 수행합니다.
  - 브랜드, 컬러, 직경, 주기를 이용하여 필터 검색을 수행합니다.
    <details>
    <summary>Show Details</summary>
    <div markdown="1">       
      
      ✔ 구현 방법        
      ---
      
      ◾ 키워드 검색의 경우 부모 activity에서 입력받은 키워드를 자식 fragment에서 처리해야 합니다.   
      따라서 fragment들에서 activity의 viewModel을 공유하여 사용하기 위해 아래와 같이 뷰모델을 정의합니다.    
      🧾 OneSearchFragment.kt
      ```kotlin
      private val viewModel: SearchViewModel by activityViewModels()
      ```
      
      ◾ 최근 검색어 추가를 위해 sharedPreference를 사용합니다.   
      (현 코드의 경우 mutableList를 sharedPreference에 넣는 오류를 범하고 있습니다. 이는 고쳐져야 할 코드 패턴입니다.)   
      🧾 SharedPreferences.kt
      ```kotlin
          object SharedPreferences {

        fun setStringArrayPref(context: Context, key: String, values: MutableList<RecentInfo>) {
            val prefs = context.getSharedPreferences("setting",Context.MODE_PRIVATE)
            val editor = prefs.edit()
            val a = JSONArray()
            for (i in 0 until values.size) {
                a.put(values[i].name)
            }
            if (values.isNotEmpty()) {
                editor.putString(key, a.toString())
            } else {
                editor.putString(key, null)
            }
            editor.apply()
        }

        fun getStringArrayPref(context: Context, key: String): ArrayList<String>? {
            val prefs = context.getSharedPreferences("setting",Context.MODE_PRIVATE)
            val json = prefs.getString(key, null)
            val urls = ArrayList<String>()
            if (json != null) {
                try {
                    val a = JSONArray(json)
                    for (i in 0 until a.length()) {
                        val url = a.optString(i)
                        urls.add(url)
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            return urls
        }


        }
      ```
      🧾 SearchViewModel.kt
      ```kotlin
      fun updateRecent(context:Context, recentSearch: MutableList<RecentInfo>, recentAdapter: RecentAdapter) {
        //sharedPreference
        SharedPreferences.setStringArrayPref(context,"RECENT_KEY",recentSearch)

        recentAdapter.recentList.clear()
        recentAdapter.recentList.addAll(recentSearch)
        recentAdapter.notifyDataSetChanged()
      }
      ```
      
      ◾ 필터 검색의 경우도 온보딩과 동일하게 정보를 한 객체에 모으기 위해 SearchDatabase를 생성하여 관리 후 서버로 전송합니다.
      🧾 SearchDatabase.kt
            
      ```kotlin
      
         class SearchDatabase {
        //싱글톤 객체 생성
        companion object{
            lateinit var searchData:SearchData
        }
      
      ```
      <br>

    </div>
    </details>
      
<br>

## 👋 Specification   

<table class="tg">
<tbody>
  <tr>
    <td><b>Architecture</b></td>
    <td>MVVM</td>
  </tr>
<tr>
    <td><b>Jetpack Components</b></td>
<td>DataBinding, LiveData, ViewModel, Lifecycle</td>
</tr>
<tr>
    <td><b>Login</b></td>
<td>Kakaotalk Login</td>
</tr>
<tr>
    <td><b>Network</b></td>
<td>OkHttp, Retrofit2</td>
</tr>
<tr>
    <td><b>Fragment Management</b></td>
<td>Navigation</td>
</tr>
<tr>
    <td><b>Strategy</b></td>
<td>Git Flow</td>
</tr>

<tr>
    <td><b>Other Tool</b></td>
<td>Notion, Slack</td>
</tr>
<tr>
    <td><b>Continuous Integration</b></td>
<td>Slack - Git auto notification</td>
</tr>
  
  
</tbody>
</table>

<br>

<br>

<br>

## 📦 Package Structure

```bash
📦omoolen
    └─om👀roid
        ├─📂detail
        │  ├─📂detailApi
        │  ├─📂popular
        │  └─📂recommend
        ├─📂home
        │  ├─📂fragments
        │  │  ├─📂five
        │  │  ├─📂four
        │  │  ├─📂one
        │  │  │  ├─📂curating
        │  │  │  ├─📂event
        │  │  │  ├─📂networkApi
        │  │  │  ├─📂newItem
        │  │  │  ├─📂recommend
        │  │  │  └─📂tip
        │  │  ├─📂three
        │  │  └─📂two
        │  │      ├─📂api
        │  │      ├─📂foryou
        │  │      ├─📂newItem
        │  │      ├─📂season
        │  │      └─📂situation
        │  └─📂homeApi
        ├─📂login_signup
        │  └─📂login
        │      └─📂loginApi
        ├─📂onboarding
        │  ├─📂api
        │  └─📂fragments
        │      ├─📂four
        │      │  ├─📂brand
        │      │  └─📂when
        │      ├─📂one
        │      │  └─📂recycle
        │      │      ├─📂age
        │      │      └─📂gender
        │      ├─📂three
        │      │  └─📂recycle
        │      │      ├─📂effect
        │      │      └─📂period
        │      └─📂two
        │          └─📂recycle
        │              ├─📂color
        │              └─📂what
        ├─📂search
        │  ├─📂data
        │  ├─📂fragment
        │  │  ├─📂one
        │  │  │  └─📂recycle
        │  │  │      ├─📂popular
        │  │  │      └─📂recent
        │  │  └─📂two
        │  │      ├─📂filterSearchApi
        │  │      └─📂recycle
        │  │          ├─📂brand
        │  │          ├─📂color
        │  │          ├─📂diameter
        │  │          └─📂period
        │  └─📂search_result
        ├─📂splash
        └─📂util
            ├─📂api
            └─📂firebase
``` 

<br>

<br>

<br>

### 🙆🏻‍♀️ Who we are?! 🙆🏻‍♀️
------
<table class="tg">
<tbody>
<tbody>
        <td><a href="https://github.com/Jionee">유지원</a></td>
        <td><a href="https://github.com/mdb1217">이유정</a></td>
        <td><a href="https://github.com/sgh002400">차지수</a></td>
    </tr>
    <tr>
        <td><img src="https://user-images.githubusercontent.com/49470328/125880657-6597e7d9-3861-4c3a-a3fa-035065b68d70.jpeg" width="300px"/></td>
        <td><img src="https://user-images.githubusercontent.com/49470328/125880648-e0eba4b0-60da-4037-9106-eda9f8b35573.jpeg"  width="300px"/></td>
        <td><img src="https://user-images.githubusercontent.com/49470328/125880665-7d324c2e-98a4-4277-ad9e-5cbbe5786da6.png"  width="300px"/></td>
    </tr>
    <tr>
  <td><sub>스플래시/카카오톡 로그인, 온보딩, 검색(키워드,필터)</sub></td>
        <td><sub>홈, 발견</sub></td>
        <td><sub>상품 상세, 검색 상세</sub></td>
    </tr>
</tbody>
</table>
