package com.guilhermecallandprojects.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash_screen.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fadeInPokedex()
    }

    private fun fadeInPokedex() {
        ll_pokedex_body.alpha = 0f
        ll_pokedex_body.animate().setDuration(2000).alpha(1f)
    }
}