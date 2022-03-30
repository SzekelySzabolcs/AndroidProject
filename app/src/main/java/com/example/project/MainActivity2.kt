package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI

import androidx.navigation.ui.setupWithNavController
import com.example.project.Model.tokenRefresh
import com.example.project.Retrofit.ApiClient
import com.example.project.Retrofit.RetroService
import com.example.project.fragment.MyFares
import com.example.project.fragment.MyMarket
import com.example.project.fragment.Timeline

import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

class MainActivity2 : AppCompatActivity() {

    lateinit var model: Modell
    private lateinit var handler: Handler

    private val timeline=Timeline.newInstance()
    private val myMarket=MyMarket.newInstance()
    private val myFares=MyFares.newInstance()

    val request = ApiClient.buildService(RetroService::class.java)
    lateinit var call: Call<tokenRefresh>
    private val runnable: Runnable = Runnable {
        call.enqueue(object :
            Callback<tokenRefresh> {
            override fun onResponse(call: Call<tokenRefresh>, response: Response<tokenRefresh>) {
                model.token = response.body()?.token.toString()

            }

            override fun onFailure(call: Call<tokenRefresh>, t: Throwable) {
                Log.d("asd", "Token update failed", t)
            }

        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        model = ViewModelProvider(this).get(Modell::class.java)


        val token = intent.extras?.get("token")
        val name = intent.extras?.get("username")
        val email = intent.extras?.get("email")
        val phone_number = intent.extras?.get("phone_number")
        val creation_time = intent.extras?.get("creation_time")
        val refresh_time = intent.extras?.get("refresh_time")
        val password = intent.extras?.get("password")

        model.name=name.toString()
        model.token=token.toString()
        model.email=email.toString()
        model.phone_number=phone_number.toString()
        model.creation_time=creation_time.toString()
        model.refresh_time=refresh_time.toString()
        model.password=password.toString()
        call  = request.tokenr(model.token, model.name)

        replaceFragment(timeline)
        val btn:BottomNavigationView = findViewById(R.id.bottom_nav)
        btn.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.timeline-> replaceFragment(timeline)
                R.id.myMarket ->replaceFragment(myMarket)
                R.id.myFares ->replaceFragment(myFares)
            }
            true
        }



        refreshToken()

    }
    private fun replaceFragment(fragment: Fragment){
            if(fragment !=null){
                val transaction=supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentContainerView,fragment)
                transaction.commit()
            }
    }

    private fun refreshToken() {

        handler = Handler()
        handler.postDelayed(runnable, TimeUnit.SECONDS.toMillis(2))

    }

    override fun onDestroy() {
        handler.removeCallbacks(runnable)
        super.onDestroy()
    }
}