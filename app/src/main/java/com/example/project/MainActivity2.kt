package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation

import androidx.navigation.ui.setupWithNavController
import com.example.project.Model.tokenRefresh
import com.example.project.Retrofit.ApiClient
import com.example.project.Retrofit.RetroService

import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

class MainActivity2 : AppCompatActivity() {

    lateinit var model: Modell
    private lateinit var handler: Handler

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

        model.name=name.toString()
        model.token=token.toString()
        model.email=email.toString()
        model.phone_number=phone_number.toString()
        model.creation_time=creation_time.toString()
        model.refresh_time=refresh_time.toString()

        call  = request.tokenr(model.token, model.name)



        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navController = Navigation.findNavController(this,R.id.fragmentContainerView)
        bottomNavigationView.setupWithNavController(navController)
        

        refreshToken()

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