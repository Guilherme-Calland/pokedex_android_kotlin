package com.guilhermecallandprojects.pokedex

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.FragmentManager
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.guilhermecallandprojects.pokedex.common.Common
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_splash_screen.*

class MainActivity : AppCompatActivity() {

    //create broadcast handle
    private val showDetail = object: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            if(intent!!.action!!.toString() == Common.KEY_ENABLE_HOME){

                //replace fragment
                val detailFragment = PokemonDetail.getInstance()
                val position = intent.getIntExtra("position", -1)
                val bundle = Bundle()
                bundle.putInt("position", position)
                detailFragment!!.arguments = bundle

                val fragmentTransaction = supportFragmentManager.beginTransaction()
                fragmentTransaction.replace(R.id.fv_pokemon_fragment, detailFragment)
                fragmentTransaction.addToBackStack("detail")
                fragmentTransaction.commit()
            }
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //register broadcast
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(showDetail, IntentFilter(Common.KEY_ENABLE_HOME))
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item!!.itemId){
            android.R.id.home -> {
                //clear all fragment in stack with name 'detail'
                supportFragmentManager.popBackStack("detail", FragmentManager.POP_BACK_STACK_INCLUSIVE)
            }

        }

        return true
    }

}