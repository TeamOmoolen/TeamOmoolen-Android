package com.omoolen.omooroid.util.firebase

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.omoolen.omooroid.R
import com.omoolen.omooroid.home.HomeActivity


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
                .setContentTitle(remoteMessage.data["title"]) //구조에 따라 달라짐
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
                .setContentTitle(remoteMessage.data["title"]) //구조에 따라 달라짐
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