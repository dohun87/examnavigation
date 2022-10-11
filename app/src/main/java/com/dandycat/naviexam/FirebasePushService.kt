package com.dandycat.naviexam

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkBuilder
import com.dandycat.naviexam.util.Logger
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebasePushService : FirebaseMessagingService() {

    companion object {
        private const val CHANNEL_ID = "examnavi"
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Logger.d("현재 토큰 : $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Logger.d("message : $message")
        message.notification?.let { noti ->
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)

            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentText(noti.body)
                .setContentTitle(noti.title)
                .setOngoing(false)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSound(defaultSoundUri)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.ic_baseline_home_24)
                .setContentIntent(createNaviPendingIntent())

            //채널 생성
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val channel = NotificationChannel(CHANNEL_ID,getString(R.string.app_name),NotificationManager.IMPORTANCE_HIGH)
                channel.description = "description"
                notificationManager.createNotificationChannel(channel)
            }
            notificationManager.notify(1,notification.build())
        }
    }

    /**
     * NavDeepLinkBuilder를 이용한 명시적 딥링크 예제 코드
     * 하지만 이 방법을 쓰게 될 시 기존 스택을 초기화 하고 다시 동작 되는 문제가 발생한다.
     * 해당 부분에 대해 저 조사 해보자
     */
    private fun createNaviPendingIntent() : PendingIntent {
        return NavDeepLinkBuilder(this).setGraph(R.navigation.nav_main_graph)
            .setDestination(R.id.fragment_main)
            .setComponentName(MainActivity::class.java)
            .setArguments(Bundle().apply {
                putString("TEST","PUSH")
            })
            .createPendingIntent()
    }
}