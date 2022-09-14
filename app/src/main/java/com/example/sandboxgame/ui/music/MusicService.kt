package com.example.sandboxgame.ui.music

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.widget.Toast
import com.example.sandboxgame.R
import com.omegar.libs.omegalaunchers.createServiceLauncher

class MusicService: Service() {

    companion object {

        fun createLauncher() = createServiceLauncher()
    }

    private var player = MediaPlayer()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Toast.makeText(this, "My Service Created", Toast.LENGTH_LONG).show()
        val player = MediaPlayer.create(this, R.raw.music_for_bk)
        player.isLooping = true
        player.setVolume(30F, 30F)
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(this, "My Service Stopped", Toast.LENGTH_LONG).show()
        player.stop()
    }

    @Deprecated("Deprecated in Java")
    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        Toast.makeText(this, "My Service Started", Toast.LENGTH_LONG).show()
        player.start()
    }

}