# 오늘은 무슨 렌즈? - Om👀len
<img src="https://user-images.githubusercontent.com/49470328/125881901-5c857059-403a-4ba3-8d14-bacf11ba8f0b.png" width="300px"/>   


<br>

<br>

<br>

### 콘택트렌즈 사용자를 위한 맞춤 렌즈 추천 및 오프라인 픽업 예약 서비스

👀 국내 모든 렌즈 정보, 리뷰 부터 의료 커뮤니티와 오프라인 픽업 예약 까지! "오늘 무슨 렌즈끼지?" 고민될 땐, 오무렌! 👀

> Om👀Roid  
> SOPT 28th APPJAM  
> 2021.06.26 ~ 2021.07.16   

<br>

<br>

<br>

### 👋 Specification   
------
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

### 📦 Package Structure
------
```bash
omoolen
└─omooroid
├─databinding
├─detail
│  ├─popular
│  └─recommend
├─home
│  └─fragments
│      ├─five
│      ├─four
│      ├─one
│      │  ├─curating
│      │  ├─event
│      │  ├─networkApi
│      │  ├─newItem
│      │  ├─recommend
│      │  └─tip
│      ├─three
│      └─two
│          ├─foryou
│          ├─newItem
│          ├─season
│          └─situation
├─login_signup
│  └─login
│      └─loginApi
├─onboarding
│  └─fragments
│      ├─four
│      │  ├─brand
│      │  └─when
│      ├─one
│      │  └─recycle
│      │      ├─age
│      │      └─gender
│      ├─three
│      │  └─recycle
│      │      ├─effect
│      │      └─period
│      └─two
│          └─recycle
│              ├─color
│              └─what
├─search
│  ├─data
│  ├─fragment
│  │  ├─one
│  │  │  └─recycle
│  │  │      ├─popular
│  │  │      └─recent
│  │  └─two
│  │      ├─api
│  │      └─recycle
│  │          ├─brand
│  │          ├─color
│  │          ├─diameter
│  │          └─period
│  └─search_result
├─splash
└─util
├─api
└─firebase
``` 

<br>

### 🔍  Main Function
------

1. Kakaotalk Login
  - 카카오톡을 이용하여 소셜 로그인을 합니다.
    <details>
    <summary>여기를 눌러주세요</summary>
    <div markdown="1">       

    😎숨겨진 내용😎
      
      ## 구현 방법
      🍝 Login 
      
      ## 구현 코드
      🍝 Login
      <br>
        ❤ UserClient.kt
      <br>
      
        ```kotlin
      
          object UserClient {
          private const val BASE_URL = "http://ec2-15-165-235-44.ap-northeast-2.compute.amazonaws.com/"

          val getApi = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(UserService::class.java)}```
      

    </div>
    </details>
  
2. Onboarding
  - 사용자 맞춤 큐레이션을 제공하기 위해 온보딩 과정을 수행합니다.
    <details>
    <summary>여기를 눌러주세요</summary>
    <div markdown="1">       

    😎숨겨진 내용😎

    </div>
    </details>
    
3. Home
  - 사용자 맞춤 큐레이션, 이벤트, 상식 등을 간략히 모아볼 수 있습니다. 
    <details>
    <summary>여기를 눌러주세요</summary>
    <div markdown="1">       

    😎숨겨진 내용😎
      구현한 내용 : recyclerView와 viewPager2를 이용 _ 각 정보를 데이터바인딩을 통해 표시해줌. 
                    상단바에 검색창을 고정하기 위해 collapsingToolbarLayout 이용.
    </div>
    </details>
    
4. 검색(키워드,필터)
  - 키워드를 이용하여 검색을 수행합니다.
  - 브랜드, 컬러, 직경, 주기를 이용하여 필터 검색을 수행합니다.
    <details>
    <summary>여기를 눌러주세요</summary>
    <div markdown="1">       

    😎숨겨진 내용😎

    </div>
    </details>
    
5. 발견
  - 사용자 맞춤 큐레이션을 한눈에 모아볼 수 있습니다.
    <details>
    <summary>여기를 눌러주세요</summary>
    <div markdown="1">       

    😎숨겨진 내용😎
         구현한 내용 : 화면 스와이프가 되는 탭 레이아웃과 각 주제의 탭 별로 리사이클러뷰로 큐레이션 아이템 정보를 보여줌.
    </div>
    </details>  
    
6. 상품 상세
  - 상품의 상세 정보를 제공합니다.
    <details>
    <summary>여기를 눌러주세요</summary>
    <div markdown="1">       

    😎숨겨진 내용😎

    </div>
    </details>  
    
7. 검색 상세
  - 검색 결과를 제공합니다.
    <details>
    <summary>여기를 눌러주세요</summary>
    <div markdown="1">       

    😎숨겨진 내용😎

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
