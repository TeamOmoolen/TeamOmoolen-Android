<br>

# <img src="https://user-images.githubusercontent.com/49470328/125881901-5c857059-403a-4ba3-8d14-bacf11ba8f0b.png" width="100px"/> 오늘은 무슨 렌즈? - Om👀len

![image](https://user-images.githubusercontent.com/70841402/126086808-f16f1aa6-bea0-4217-8570-2b12acee730a.png)

<br>

## 콘택트렌즈 사용자를 위한 맞춤 렌즈 추천 및 오프라인 픽업 예약 서비스

👀 국내 모든 렌즈 정보, 리뷰 부터 의료 커뮤니티와 오프라인 픽업 예약 까지! "오늘 무슨 렌즈끼지?" 고민될 땐, 오무렌! 👀

> Om👀Roid  
> SOPT 28th APPJAM  
> 2021.06.26 ~ 2021.07.16   

<br>

<br>

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
    <summary>여기를 눌러주세요</summary>
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
  
2. Onboarding
  - 사용자 맞춤 큐레이션을 제공하기 위해 온보딩 과정을 수행합니다.
    <details>
    <summary>여기를 눌러주세요</summary>
    <div markdown="1">       
      
      ✔ 구현 방법        
      ---
      
      ◾ Onboarding
      

      ✔ 구현 코드
      ---
      
      ◾ Onboarding  
      
      🧾 OnboardActivity.kt
            
      ```kotlin
      
        class OnboardActivity : AppCompatActivity(){
      private lateinit var navController: NavController
      private val fragmentOnboardOne by lazy { OneOnboardFragment() }
      private val fragmentOnboardTwo by lazy { TwoOnboardFragment() }
      private val fragmentOnboardThree by lazy { ThreeOnboardFragment() }
      private val fragmentOnboardFour by lazy { FourOnboardFragment() }

      override fun onCreate(savedInstanceState: Bundle?) {
          super.onCreate(savedInstanceState)
          setContentView(R.layout.activity_onboard)
          initNavController()
          //changeFragment(fragmentOnboardOne)

          //back 버튼 누를 경우
          val back : ImageView = findViewById(R.id.iv_back)
          back.setOnClickListener{
              onBackPressed()
          }
      }

      private fun initNavController() {
          val navHostFragment =
              supportFragmentManager.findFragmentById(R.id.host_nav_fragment_onboard) as NavHostFragment
          navController = navHostFragment.navController
      }

      private fun changeFragment(fragment: Fragment) {
          Log.d("fragmentChanged", fragment.toString())
          supportFragmentManager
              .beginTransaction()
              .replace(R.id.host_nav_fragment_onboard, fragment)
              .commit()
        }
    }
      
      ```
      
      <br>

    </div>
    </details>
    
3. Home
  - 사용자 맞춤 큐레이션, 이벤트, 상식 등을 간략히 모아볼 수 있습니다. 
    <details>
    <summary>여기를 눌러주세요</summary>
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
    
4. 검색(키워드,필터)
  - 키워드를 이용하여 검색을 수행합니다.
  - 브랜드, 컬러, 직경, 주기를 이용하여 필터 검색을 수행합니다.
    <details>
    <summary>여기를 눌러주세요</summary>
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
    
5. 발견
  - 사용자 맞춤 큐레이션을 한눈에 모아볼 수 있습니다.
    <details>
    <summary>여기를 눌러주세요</summary>
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
    
6. 상품 상세
  - 상품의 상세 정보를 제공합니다.
    <details>
    <summary>여기를 눌러주세요</summary>
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
    
7. 검색 상세
  - 검색 결과를 제공합니다.
    <details>
    <summary>여기를 눌러주세요</summary>
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
