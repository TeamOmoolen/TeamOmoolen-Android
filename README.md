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
    <td><img width="200" src="https://user-images.githubusercontent.com/49470328/126744328-731c89f5-5298-443c-8483-6c044ce69017.gif"></td>
    <td><img width="200" src="https://user-images.githubusercontent.com/49470328/126743469-2a88990e-101e-430c-a03d-b5996ecf8d94.gif"></td>
  </tr>
  <tr>
    <td align="center"><b>Splash</b></td>
    <td align="center"><b>카카오톡 로그인</b></td>

  </tr>
</table>
<table>
  <tr>
    <td><img width="200" src="https://user-images.githubusercontent.com/49470328/126744388-2fb5d1f6-bf6c-4081-adff-0226aa925293.gif"></td>
    <td><img width="200" src="https://user-images.githubusercontent.com/49470328/126755649-e8ae3014-38b0-41a4-af0c-962a5dde20f4.gif"></td>
    <td><img width="200" src="https://user-images.githubusercontent.com/49470328/126744448-913d9716-2b92-48ed-b26d-2abe3965b7b8.gif"></td>
  </tr>
  <tr>
    <td align="center"><b>온보딩1,2</b></td>
    <td align="center"><b>온보딩3</b></td>
    <td align="center"><b>온보딩4</b></td>
  </tr>
</table>
<table>
  <tr>
    <td><img width="200" src="https://user-images.githubusercontent.com/49470328/126754638-4a1f319a-5015-42da-9c69-7f56442b9ee3.gif"></td>
    <td><img width="200" src="https://user-images.githubusercontent.com/49470328/126754914-b23c4ed9-b866-4cbf-9287-6f9e35c8c249.gif"></td>
    <td><img width="200" src="https://user-images.githubusercontent.com/49470328/126755103-43987851-5979-447e-bf70-de286016a310.gif"></td>
  </tr>
  <tr>
    <td align="center"><b>홈</b></td>
    <td align="center"><b>발견</b></td>
    <td align="center"><b>제품 상세</b></td>
  </tr>
</table>
<table>
  <tr>
   <td><img width="200" src="https://user-images.githubusercontent.com/49470328/126755324-45dce107-fba5-4b64-9024-12058533ff47.gif"></td>
   <td><img width="200" src="https://user-images.githubusercontent.com/49470328/126744812-551cc494-49a7-4d8f-b6e6-c4ff15a76527.gif"></td>
  </tr>
  <tr>
    <td align="center"><b>최근 검색</b></td>
    <td align="center"><b>필터 검색</b></td>
  </tr>
</table>
<br><br>



## 💬 기능 상세
### **1. Kakaotalk Login**
  - 카카오톡을 이용하여 소셜 로그인을 합니다.
    <details>
    <summary>✨Show Details✨</summary>
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
    <summary>✨Show Details✨</summary>
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
    <summary>✨Show Details✨</summary>
    <div markdown="1">       

      ✔ 구현 방법        
      ---

      ◾ Home 화면 

    * 저장되어있는 사용자의 토큰을 이용해 viewModel에서 서버 통신, 사용자가 입력한 정보에 대한 맞춤 정보를 담은 렌즈 데이터를 각각 RecommendationBySeason, RecommendationBySituation, RecommendationByUser, Giudes, DeadlineEvent, LastestEvent, NewLens라는 데이터 객체로 받아, 이를 RecyclerView로 구성해 보여주었습니다. 

    * 이때 viewModel에서 통신해 받은 데이터의 경우, fragment에서 observe를 통해 관찰하고 있다가, 데이터에 변화가 생길 경우 이를 알려주어 업데이트를 진행합니다. 

    * 위의 렌즈 데이터 객체들 중에서 otherColors라는 색깔 배열을 받는 객체의 경우, 중첩  recyclerView로 표현. _ 이는 외부 RecyclerView_ Adapter의 ViewHolder에서 bind 시 내부 RecyclerView의 Adapter를 설정함으로서 구현.

      

    🧾 CuratingListAdapter.kt

    ```kotlin
    class CuratingListAdapter:RecyclerView.Adapter<CuratingListAdapter.CuratingViewHolder>() {
    
        private var curateList = emptyList<RecommendationByUser>()
    
        class CuratingViewHolder(
            private val binding : ItemOneCuratingBinding
        ): RecyclerView.ViewHolder(binding.root){
            fun bind(curatingInfo: RecommendationByUser){
                binding.curatingInfo = curatingInfo
    
                //자식 RecyclerView Adapter 설정
                val listForColor = LensColorListAdapter()
                listForColor.setColoring(curatingInfo.otherColorList as List<String>)
                binding.rvOneCuratingColor.adapter = listForColor
    
            }
        }
    
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuratingViewHolder {
            val binding = ItemOneCuratingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
    
            return CuratingViewHolder(binding)
        }
    
        override fun onBindViewHolder(holder: CuratingViewHolder, position: Int) {
            holder.bind(curateList[position])
            holder.itemView.setOnClickListener {
                itemClickListener.onClick(it, position)
            }
        }
    
        override fun getItemCount(): Int = curateList.size
    
        fun setCurating(curateList : List<RecommendationByUser>){
            this.curateList = curateList
            notifyDataSetChanged()
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
    
    }
    
    ```

    * OneHomeFragment에서 각 요소 클릭 시 ...

    * RecommendationBySeason, RecommendationBySituation, RecommendationByUser 의 경우, RecyclerView의 item 클릭 시 해당 렌즈의 상세 페이지로 이동.
       _ 렌즈의 상품 id를 넘겨줌.
    * 각 RecyclerView 위에 있는 '더보기>' 클릭 시 발견의 관련 탭으로 이동.
      _ 계절 관련 아이템 추천의 더보기를 클릭 시 발견 탭의 4번째 탭인 계절 탭으로 이동.
    * 상단의 검색바 클릭 시 검색 페이지로 이동.

    🧾 OneHomeFragment.kt

    ```kotlin
    class OneHomeFragment : Fragment() {
        private val handler: Handler = Handler(Looper.getMainLooper())
        private var _binding: FragmentHomeOneBinding? = null
        private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    
        private val oneHomeViewModel: OneHomeViewModel by activityViewModels()
        private lateinit var situLayoutManager : RecyclerView.LayoutManager
        private lateinit var seasonLayoutManager : RecyclerView.LayoutManager
    
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = FragmentHomeOneBinding.inflate(inflater, container, false)
            binding.lifecycleOwner = viewLifecycleOwner
    
            initLayout()
            setClickListener()
    
            oneHomeViewModel.getHome()
    
            setCuratingAdapter()
            setCuratingObserve()
    
            setRecommend1Adapter()
            setRecommend1Observe()
    
            setRecommend2Adapter()
            setRecommend2Observe()
    
            setEventAdapter()
            setEventObserve()
            setEventIndicator()
    
            setAdAdapter()
            setAdObserve()
            setAdIndicator()
    
            setTipAdapter()
            setTipObserve()
    
            setNewAdapter()
            setNewObserve()
    
    
            return binding.root
        }
    
         override fun onStart() {
            super.onStart()
            oneHomeViewModel.situation.observe(viewLifecycleOwner) {
                if(oneHomeViewModel.situation.value.equals("일상")) {
                    binding.tvHomeRecommend.text = oneHomeViewModel.situation.value + "에서 끼지 좋은 렌즈"
                }
                else {
                    binding.tvHomeRecommend.text  = oneHomeViewModel.situation.value + "할때 끼지 좋은 렌즈"
                }
            }
            oneHomeViewModel.userName.observe(viewLifecycleOwner) {
                binding.tvHomeCurating.text = oneHomeViewModel.userName.value + "님 이 렌즈 어떠세요?"
            }
    
        }
        
        //RecyclerView 아이템 사이 마진 지정 관련 코드 생략...
        
        private fun setCuratingAdapter(){
            val curatingListAdapter = CuratingListAdapter()
            curatingListAdapter.setItemClickListener(object: CuratingListAdapter.OnItemClickListener{
                override fun onClick(v: View, position: Int) {
                    val rbu :RecommendationByUser = oneHomeViewModel.recommendationByUserList.get(position)
                    val intent = Intent(requireContext(), DetailActivity::class.java)
                    intent.putExtra("itemId", rbu.id)
                    startActivity(intent)
                }
            })
    
            binding.rvHomeCurating.adapter = curatingListAdapter
        }
        private fun setCuratingObserve(){
            oneHomeViewModel.recommendationByUserList.observe(viewLifecycleOwner){
                curatingList -> with(binding.rvHomeCurating.adapter as CuratingListAdapter){
                    setCurating(curatingList)
                }
            }
        }
    
        private fun setEventAdapter(){
            binding.vpHomeEvent.adapter = EventViewPagerAdapter()
        }
    
        private fun setEventObserve(){
            oneHomeViewModel.deadlineEventList.observe(viewLifecycleOwner){ eventList ->
                with(binding.vpHomeEvent.adapter as EventViewPagerAdapter){
                    setEvent(eventList)
                }
            }
        }
        private fun setEventIndicator() {
            TabLayoutMediator(binding.tabHomeEvent, binding.vpHomeEvent) { tab, position -> }.attach()
        }
        
        //이 외 5개의 데이터 객체에 대한 RecyclerView의 Adapter와 Observe 코드 생략. 
    
        private fun setClickListener(){
    
            binding.tvOneSearch.setOnClickListener {
                val intent = Intent(context, SearchActivity::class.java)
                startActivity(intent)
            }
    
            binding.clHomeCuratingMore.setOnClickListener{
    
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.nav_host_home, TwoHomeFragment()
                        .apply {
                            arguments = Bundle().apply {
                                putInt("setIdx", 1)
                            }
                        }, "home->foryou")
                    ?.commit()
    
                (activity as HomeActivity).setBottomChecked(1)
            }
    
            binding.clHomeRecommendMore.setOnClickListener{
    
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.nav_host_home, TwoHomeFragment().apply {
                        arguments = Bundle().apply {
                            putInt("setIdx", 2)
                        }
                    },"home->situ")
                    ?.commit()
    
                (activity as HomeActivity).setBottomChecked(1)
            }
    
    
            binding.clHomeSeasonMore.setOnClickListener{
    
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.nav_host_home, TwoHomeFragment().apply {
                        arguments = Bundle().apply {
                            putInt("setIdx", 4)
                        }
                    }, "home->saeson")
                    ?.commit();
    
                (activity as HomeActivity).setBottomChecked(1)
    
            }
    
            binding.clHomeNewMore.setOnClickListener {
    
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.nav_host_home, TwoHomeFragment().apply {
                        arguments = Bundle().apply {
                            putInt("setIdx", 3)
                        }
                    }, "home->saeson")
                    ?.commit();
    
                (activity as HomeActivity).setBottomChecked(1)
    
            }
    
    
        }
    
    }
    ```

    </div>
    </details>
### **4. 발견**
  - 사용자 맞춤 큐레이션을 한눈에 모아볼 수 있습니다.
    <details>
    <summary>✨Show Details✨</summary>
    <div markdown="1">       

      ✔ 구현 방법        
      ---

      ◾ 기본적인 구현 방식은 Home에서 RecyclerView를 이용하여 구현한 것과 크게 차이는 없습니다. 사용자 토큰을 사용하여 _ 서버에서 데이터를 받아올 경우, 해당 데이터를 각 탭에서 테마에 맞게 RecyclerView를 이용하여 정보를 보여줍니다. 색깔을 중첩 recyclerView를 이용하였고, 각 아이템을 클릭 시 상세 페이지로 이동합니다.  검색바를 클릭 시 검색 페이지로 이동합니다.

    * 차이점 : 발견 fragment 위에 다시 4개의 fragment를 tabLayout과 viewPager2를 이용한 탭이 올라가짐. 이를 통해 발견 탭에서는 다시 상세 4개의 탭이 보여지며, 이를 스와이프를 통해 이동할 수 있음. 
    * 각 상세 탭은 For you, 계절, 상황, 신제품 정보를 onBoarding 과정에서 입력한 정보를 기반으로 보여줍니다. 또한, 각 탭에는 특정 아이콘 클릭 시 해당 탭의 정보를 알려주는 다이얼로그와, 정렬 관련 다이얼로그가 있습니다. ( 정렬의 경우, 다이얼로그만 보여지고, 실제로 정렬이 되는 부분은 미구현됨. )
    * 발견 탭의 로그 클릭 시 홈으로 이동.


      ✔ 구현 코드
      ---

      ◾ 

      🧾 TwoHomeFragment.kt
      ```kotlin
      class TwoHomeFragment : Fragment() {
          private var _binding: FragmentHomeTwoBinding? = null
          private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
      
          private val homeViewModel: TwoHomeViewModel by viewModels() //위임초기화
          private lateinit var mContext: Context
      
          private  var idx : Int? = null
          override fun onCreateView(
              inflater: LayoutInflater,
              container: ViewGroup?,
              savedInstanceState: Bundle?
          ): View? {
              _binding = FragmentHomeTwoBinding.inflate(inflater, container, false)
              binding.lifecycleOwner = viewLifecycleOwner
              mContext = requireContext()
              setClickListener()
      
      
              homeViewModel.getSuggestData()
      
              return binding.root
          }
      
      
          override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
              super.onViewCreated(view, savedInstanceState)
          }
      
          //ViewPager2와 tabLayout Init
          override fun onActivityCreated(savedInstanceState: Bundle?) {
              super.onActivityCreated(savedInstanceState)
      
              val pagerAdapter = PagerFragmentStateAdapter(requireActivity())
              pagerAdapter.addFragment(TwoHomeForYouFragment())
              pagerAdapter.addFragment(TwoHomeSituFragment())
              pagerAdapter.addFragment(TwoHomeNewFragment())
              pagerAdapter.addFragment(TwoHomeSeasonFragment())
      
              binding.vpHomeTwo.adapter = pagerAdapter
      
              binding.vpHomeTwo.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                  override fun onPageSelected(position: Int) {
                      super.onPageSelected(position)
                  }
              })
      
              TabLayoutMediator(binding.findTabLayout, binding.vpHomeTwo) { tab, position ->
                      when (position) {
                          0 -> { tab.text = homeViewModel.tabItem1}
                          1 -> { tab.text = homeViewModel.tabItem2}
                          2 -> { tab.text = homeViewModel.tabItem3}
                          3 -> { tab.text = homeViewModel.tabItem4}
                      }
              }.attach()
      
      
              idx = arguments?.getInt("setIdx")
              if(idx != null) {
                  val tabLayout = binding.findTabLayout
                  val tab = tabLayout.getTabAt(idx!! - 1)
                  tab!!.select()
      
                  pagerAdapter.createFragment(idx!! - 1)
              }
          }
      
          private fun setClickListener() {
      
              binding.tvTwoSearch.setOnClickListener {
                  val intent = Intent(context, SearchActivity::class.java)
                  startActivity(intent)
              }
              
              binding.ivTwoLogo.setOnClickListener{
                  activity?.supportFragmentManager
                      ?.beginTransaction()
                      ?.replace(
                          R.id.nav_host_home, OneHomeFragment(), "home->foryou")
                      ?.commit()
      
                  (activity as HomeActivity).setBottomChecked(0)
              }
          }
      
      }
      ```
    ​    🧾 PagerFragmentAdapter.kt _ viewPager할 fragment를 지정.

    ```kotlin
    class PagerFragmentStateAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    
        var fragments : ArrayList<Fragment> = ArrayList()
    
        override fun getItemCount(): Int {
            return fragments.size
        }
    
        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    
        fun addFragment(fragment: Fragment) {
            fragments.add(fragment)
            notifyItemInserted(fragments.size-1)
        }
    
        fun removeFragment() {
            fragments.removeLast()
            notifyItemRemoved(fragments.size)
        }
    
    }
    ```

    🧾 TwoHomeForYouFragment.kt

    ```kotlin
    class TwoHomeForYouFragment : Fragment() {
    
        companion object {
            fun newInstance() = TwoHomeForYouFragment()
        }
    
        private var _binding: FragmentHomeTwoForyouBinding? = null
        private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    
        private val viewModel: TwoHomeViewModel by activityViewModels()
        private val fragmentViewModel: TwoHomeForYouViewModel by viewModels()
    
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = FragmentHomeTwoForyouBinding.inflate(inflater, container, false)
            binding.lifecycleOwner = viewLifecycleOwner
    
            //데이터 setting
            viewModel.getSuggestData()
            setForYouAdapter()
            setForYouObserve()
    
    
            //정렬 클릭 시
            binding.ivForYouSort.setOnClickListener{
                val findSortPriceFragment = FindSortPriceFragment()
    
                findSortPriceFragment.setButtonClickListener(object: FindSortPriceFragment.OnButtonClickListener {
                    override fun onLowPriceClicked() {
                        //여기서 정렬
                        Log.d("click", "low price")
                        fragmentViewModel.getForyou(1,"price","asc")
                    }
    
                    override fun onHighPriceClicked() {
                        // 여기서 정렬
                        Log.d("click", "high price")
                        fragmentViewModel.getForyou(1,"price","desc")
                    }
                })
                findSortPriceFragment.show(childFragmentManager, "CustomDialog")
            }
    
            binding.ivFindQuestion1.setOnClickListener{
                val findQuestionFragment = FindQuestionFragment(1)
                findQuestionFragment.show(childFragmentManager, "CustomDialog2")
    
            }
    
            return binding.root
        }
        //... 아래는 홈의 OneHomeFragment.kt와 유사.
    }
    ```

    <br>

    </div>
    </details>    

### **5. 상품 상세**
  - 상품의 상세 정보를 제공합니다.
    <details>
    <summary>✨Show Details✨</summary>
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
    <summary>✨Show Details✨</summary>
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
    <summary>✨Show Details✨</summary>
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
        <td><a href="https://github.com/You-jeong136">이유정</a></td>
        <td><a href="https://github.com/cjsjizzu">차지수</a></td>
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

