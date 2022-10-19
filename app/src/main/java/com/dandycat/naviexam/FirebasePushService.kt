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
     * 명시적 딥링크를 사용하게 될 시 지정된 화면으로의 이동이 가능하다.
     * 하지만 해당 부분을 사용하게 될 경우, 기존 유지되는 백스택이 전부 제거후 새로운 스택으로 진행 되어진다.
     * 해당 동작에 대해 생각을 해보았을 경우, 외부 위젯을 이용한 동작에 효율적일 것 같다.
     * 내부적으로 해서 사용하게 될 시, 신중을 가하여 사용하도록 하자.
     */
    private fun createNaviPendingIntent() : PendingIntent {
        val bundle = Bundle().apply {
            putString("TEST","PUSH")
        }

        return NavDeepLinkBuilder(this).setGraph(R.navigation.nav_main_graph)
            //.addDestination(R.id.fragment_splash,null)
            //.addDestination(R.id.fragment_main,null)
            .setDestination(R.id.fragment_notice,bundle)
            .setComponentName(MainActivity::class.java)
            .createPendingIntent()
    }
}