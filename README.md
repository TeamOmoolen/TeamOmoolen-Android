# AndroidPractices

## Firebase PushAlarm

```
MyFirebaseMessagingService에서 기기의 token을 받아온 후 이를 기기의 sharedPreferences 혹은 서버의 user정보에 저장한다. (미구현) 
서버로부터 메세지를 받게 되면 받은 remoteMessage를 data/notification별로 title,message등을 전달 받고 
이를 notoficationBuilder를 이용하여 push alarm을 구현한다. 
해당 푸시알람을 클릭하게 되면 intent 이벤트를 이용하여 HomeActivity로 이동하는데, 
현 구조의 경우 Jetpack을 이용한 navigation fragment로 구현해 놓았기 때문에, 
HomeActivity에서 intent가 Null이 아닐 경우 fragment를 전환해주는 이벤트를 추가했다.
또, firebaseGetToken()을 통해 토큰의 최초 생성 외에도 토큰을 받아올 수 있게 하였다.
```

* MyFirebaseMessagingService

```

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) { //기기 token값 받아와서 서버에 전송하기
        Log.d(TAG, "new Token: $token")
        sendRegistrationToServer(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage != null) {
            Log.d(TAG, "From: " + remoteMessage.data)
            Log.i("notice title: ", remoteMessage.data["title"].toString())
            Log.i("notice message: ", remoteMessage.data["message"].toString())
            sendNotification(remoteMessage)
            sendMainNotification(remoteMessage)
        } else {
            Log.i("notice 수신에러: ", "data가 비어있습니다. 메시지를 수신하지 못했습니다.")
            Log.i("notice data 값: ", remoteMessage.data.toString())
        }
    }

    private fun sendMainNotification(remoteMessage: RemoteMessage) {
        val uniId = remoteMessage.sentTime.toInt()

        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("test", "test!")
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(
            this, uniId, intent, PendingIntent.FLAG_ONE_SHOT
        )

        val channelId = "Notification Message"

        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder =
            NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(remoteMessage.data["title"])
                .setContentText(remoteMessage.data["message"])
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, "Notice", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(uniId, notificationBuilder.build())
    }


    private fun sendNotification(remoteMessage: RemoteMessage) {
        val uniId = remoteMessage.sentTime.toInt()

        val intent = Intent(this, MyFirebaseMessagingService::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        //intent의 실행 권한을 외부의 어플리케이션에 위임
        val pendingIntent = PendingIntent.getActivity(
            this, uniId, intent, PendingIntent.FLAG_ONE_SHOT
        )

        val channelId = "노티피케이션 메시지"

        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder =
            NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle(remoteMessage.data["title"])
                .setContentText(remoteMessage.data["message"])
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, "Notice", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(uniId, notificationBuilder.build())
    }

    private fun sendRegistrationToServer(token: String) {
        Log.d(TAG, "sendRegistrationTokenToServer($token)")
    }

    companion object {
        const val TAG = "FirebasePractice"
    }
}
```

* HomeActivity
```
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initNavController()

        firebasePushalarmCallback()
        firebaseGetToken()
    }

    private fun firebasePushalarmCallback(){
        var intent = getIntent()
        if (intent != null) { //푸시알림을 선택해서 실행한것이 아닌경우 예외처리
            val notificationData = intent.getStringExtra("test")
            if (notificationData != null) {
                changeFragment(fragmentTwo)//fragment 전환
                binding.bnvMain.selectedItemId = R.id.locationFragment //bottom navigation 선택 바꾸기
            }
        }
    }
    private fun firebaseGetToken(){
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(
                    "FirebasePractice.TAG",
                    "Fetching FCM registration token failed",
                    task.exception
                )
                return@OnCompleteListener
            } else {
                val token = task.result
                val msg = getString(R.string.msg_token_fmt, token)
                Log.d("fbPractice.Success", msg)
            }
        })
    }
```

* Postman Sending
```
{
	"to":"fpBC-7ICRVukhWDyTmKu3L:APA91bEH5PGdEx1em9-ALZ0-QhIhZF6RlPTVGPUNj0zDheGLU_ckMqMFyNQ-DlheF7wXora1Ju7-DLOwUbtOYbdxzD21hzX-Q4n6-aCuhIaD8o-PoIHe5dkvMe63RBs6qDDGm0BTc-CZ",
	"priority":"high",
	"data":{
		"title":"first Notification",
		"message":"first message",
		"test":"it is a test"
	}
}
```
