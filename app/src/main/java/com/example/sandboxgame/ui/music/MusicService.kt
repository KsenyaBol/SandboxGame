package com.example.sandboxgame.ui.music

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.media.MediaPlayer
import android.os.IBinder
import com.example.sandboxgame.R


class MusicService: Service(), MediaPlayer.OnCompletionListener {

    private var player = MediaPlayer()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        val filter = IntentFilter(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        val receiver: BroadcastReceiver = ScreenReceiver()
        registerReceiver(receiver, filter)
        player = MediaPlayer.create(this, R.raw.music_for_bk)
        player.isLooping = true
        player.setVolume(50F, 50F)
    }

    @Deprecated("Deprecated in Java")
    override fun onStart(intent: Intent, startId: Int) {
        val screenOn = intent.getBooleanExtra("screen_state", false)
        if (!screenOn) {
            player.start()
        } else {
            player.stop()
        }
    }

    override fun onDestroy() {
        player.stop()
    }
//
//    override fun onResume() {
//        if (ScreenReceiver.screenOff == true) {
//            player.stop()
//        } else {
//            player.start()
//        }
//    }

//    override fun onDestroy() {
//        Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show()
//        if (player.isPlaying) {
//            player.stop()
//        }
//        player.release()
//    }
//
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show()
//        if (!player.isPlaying) {
//            player.start()
//        }
//        return START_STICKY
//    }
//
    override fun onCompletion(mp: MediaPlayer?) {
        stopSelf()
    }

//    override fun onPause() {
//        if (!player .isPlaying) {
//            player.pause()
//        }
//    }
//
//    override fun onResume() {
//        if (!player .isPlaying) {
//            player.start()
//        }
//    }



}