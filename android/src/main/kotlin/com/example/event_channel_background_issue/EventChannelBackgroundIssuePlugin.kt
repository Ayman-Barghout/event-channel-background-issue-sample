package com.example.event_channel_background_issue

import android.os.Handler
import android.util.Log
import androidx.annotation.NonNull
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.EventChannel
import java.util.*

class EventChannelBackgroundIssuePlugin : FlutterPlugin {

    private var eventChannelOne: EventChannel? = null

    override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
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
                            handler.postDelayed(this, 1000)
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

    }
}
