package cn.com.liaozp.alarmclock.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.IBinder
import android.os.Vibrator
import android.support.annotation.Nullable
import android.support.annotation.RequiresApi
import cn.com.liaozp.alarmclock.R
import cn.com.liaozp.alarmclock.RingActivity
import cn.com.liaozp.alarmclock.application.App.Companion.CHANNEL_ID
import cn.com.liaozp.alarmclock.broadcastreceiver.AlarmBroadcastReceiver.Companion.TITLE

class AlarmService : Service() {
    private var mediaPlayer: MediaPlayer? = null
    private var vibrator: Vibrator? = null
    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.alarm)
        mediaPlayer!!.isLooping = true
        vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val notificationIntent = Intent(this, RingActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        val alarmTitle = String.format("%s Alarm", intent.getStringExtra(TITLE))
        val notification: Notification = Notification.Builder(this, CHANNEL_ID)
                .setContentTitle(alarmTitle)
                .setContentText("Ring Ring .. Ring Ring")
                .setContentIntent(pendingIntent)
                .build()
        mediaPlayer!!.start()
        val pattern = longArrayOf(0, 100, 1000)
        vibrator!!.vibrate(pattern, 0)
        startForeground(1, notification)
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer!!.stop()
        vibrator!!.cancel()
    }

    @Nullable
    override fun onBind(intent: Intent): IBinder? {
        return null
    }
}