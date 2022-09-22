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
        player.release()
    }

    override fun onCompletion(mp: MediaPlayer?) {
        stopSelf()
    }


}