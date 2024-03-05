package com.example.event_channel_background_issue

import android.app.*
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.util.Log
import androidx.annotation.NonNull
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.EventChannel
import java.util.*

class EventChannelBackgroundIssuePlugin : FlutterPlugin, ActivityAware, Service() {

    private val tag: String = "<<  EventChannel >>"
    private var eventChannelOne: EventChannel? = null

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        Log.i(tag, "onAttachedToEngine called..")
        eventChannelOne = EventChannel(flutterPluginBinding.binaryMessenger, "event_channel_one")
        eventChannelOne?.setStreamHandler(object : EventChannel.StreamHandler {
            override fun onListen(arguments: Any?, events: EventChannel.EventSink?) {
                val random = Random()
                if (events != null) {
                    val handler = Handler()
                    val runnableCode: Runnable = object : Runnable {
                        override fun run() {
                            val randomNumber: Int = random.nextInt()
                            events.success(randomNumber.toString())
                            Log.i("sample-tag", "$randomNumber sent by EventSink")
                            handler.postDelayed(this, 2000)
                        }
                    }
                    handler.post(runnableCode)
                }
            }

            override fun onCancel(arguments: Any?) {
            }
        })
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        Log.i(tag, "onDetachedFromEngine called..")
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.i(tag, "onBind called..")
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(tag, "onStartCommand called..")
        return START_STICKY
    }

    override fun onCreate() {
        Log.i(tag, "onCreate called..")
        val notificationChannel = "my_notification_channel"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                    notificationChannel,
                    "Dart In Background Notification Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager?.createNotificationChannel(serviceChannel)
        }

        val notification: Notification = NotificationCompat.Builder(this, notificationChannel)
                .setContentTitle("My Foreground Service")
                .setContentText("<-> My Foreground Service <->")
                .setContentIntent(PendingIntent.getActivity(this,
                        0, Intent(), PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT))
                .build()
        startForeground(1, notification)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        Log.i(tag, "onAttachedToActivity called..")
        ContextCompat.startForegroundService(
                binding.activity,
                Intent(binding.activity, EventChannelBackgroundIssuePlugin::class.java))
    }

    override fun onDetachedFromActivityForConfigChanges() {
        Log.i(tag, "onDetachedFromActivityForConfigChanges called..")
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        Log.i(tag, "onReattachedToActivityForConfigChanges called..")
    }

    override fun onDetachedFromActivity() {
        Log.i(tag, "onDetachedFromActivity called..")
    }
}
