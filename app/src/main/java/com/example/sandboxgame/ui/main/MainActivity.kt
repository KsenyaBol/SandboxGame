package com.example.sandboxgame.ui.main

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.core.rule.ui.database.SpaceWithPlanetAndFood
import com.example.core.rule.ui.objects.space.Space
import com.example.core.rule.ui.objects.space.SpaceObject
import com.example.sandboxgame.R
import com.example.sandboxgame.di.App
import com.example.sandboxgame.di.App.Companion.database
import com.example.sandboxgame.ui.base.BaseActivity
import com.example.sandboxgame.ui.game.GameActivity
import com.example.sandboxgame.ui.music.MusicService
import com.example.sandboxgame.ui.music.ScreenReceiver
import com.omegar.libs.omegalaunchers.createActivityLauncher
import com.omegar.mvp.ktx.providePresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

open class MainActivity : BaseActivity(R.layout.activity_main), MainView {

    companion object {

        fun createLauncher() = createActivityLauncher()
    }
    override val presenter: MainPresenter by providePresenter()
    private val buttonStart: Button by bind(R.id.button_start)
    private val buttonContinue: Button by bind(R.id.button_continue)
    private val buttonSetting: Button by bind(R.id.button_settings)

    private val gameActivity: GameActivity = GameActivity()
    lateinit var space: Space
    private val spaceObject: SpaceObject = SpaceObject(gameActivity.id)
    private val spaceWithPlanetAndFood: SpaceWithPlanetAndFood = SpaceWithPlanetAndFood()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val filter = IntentFilter(Intent.ACTION_SCREEN_ON)
        filter.addAction(Intent.ACTION_SCREEN_OFF)
        val receiver: BroadcastReceiver = ScreenReceiver()
        registerReceiver(receiver, filter)


        val soundButtonClick = MediaPlayer.create(this, R.raw.sound_for_button)

        startService(Intent(this, MusicService::class.java))
        stopService(Intent(this, MusicService::class.java))

        buttonStart.setOnClickListener {
            presenter.onButtonStartClicked()
            soundButtonClick.start()
        }

        buttonContinue.setOnClickListener {
            presenter.onButtonContinueClicked()
            soundButtonClick.start()
        }

        buttonSetting.setOnClickListener {
            presenter.onButtonSettingsClicked()
            soundButtonClick.start()
        }

        GlobalScope.launch {

            if(database?.spaceDao?.getSpaceWithPlanetAndFood() == null) {
                //nothing
            } else {
               var db = App.instance?.getDatabase()
            }
            withContext(Dispatchers.Main) {
//                space.myPlanetList = spaceWithPlanetAndFood.planet!!
//                space.myFoodList = spaceWithPlanetAndFood.food!!
            }
        }

//        requestForPermission()

    }

//    val EXTERNAL_PERMS = arrayOf<String>(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//    val EXTERNAL_REQUEST = 138
//
//    fun hasPermission(perm: String): Boolean {
//        return PackageManager.PERMISSION_GRANTED == ContextCompat.checkSelfPermission(this, perm)
//    }
//
//    fun canAccessExternalSd(): Boolean {
//        return hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
//    }
//
//    fun requestForPermission(): Boolean {
//        var isPermissionOn = true
//        val version = Build.VERSION.SDK_INT
//        if (version >= 23) {
//            if (!canAccessExternalSd()) {
//                isPermissionOn = false
//                ActivityCompat.requestPermissions(this, EXTERNAL_PERMS, EXTERNAL_REQUEST)
//            }
//        }
//        return isPermissionOn
//    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        stopService(Intent(this, MusicService::class.java))
    }

    override fun onClickView(view: View) {
        super.onClickView(view)
    }

}