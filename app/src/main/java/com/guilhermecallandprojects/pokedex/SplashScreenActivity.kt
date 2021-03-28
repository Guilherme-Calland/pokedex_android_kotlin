package com.guilhermecallandprojects.pokedex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_splash_screen.*

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        showIconAndStartApp()
    }

    private fun showIconAndStartApp() {
        ll_pokedex_logo.alpha = 0f
        ll_pokedex_logo.animate().setDuration(2000).alpha(1f)
            .withEndAction {
                gotoMainScreen()
            }

    }

    private fun gotoMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}