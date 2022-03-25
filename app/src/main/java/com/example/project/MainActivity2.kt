package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.project.Model.tokenRefresh
import com.example.project.Retrofit.ApiClient
import com.example.project.Retrofit.RetroService
import com.example.project.fragment.Timeline
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeUnit

class MainActivity2 : AppCompatActivity() {

    lateinit var model:Modell

    lateinit var handler: Handler
    val request= ApiClient.buildService(RetroService::class.java)
    val call = request.tokenr(model.token, model.name)
    private val runnable: Runnable = Runnable {
        call.enqueue(object:
            Callback<tokenRefresh> {
            override fun onResponse(call: Call<tokenRefresh>, response: Response<tokenRefresh>) {
                model.token = response.body()?.token.toString()
            }

            override fun onFailure(call: Call<tokenRefresh>, t: Throwable) {
                Log.d("BaseFragment", "Token update failed", t)
            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        model= ViewModelProvider(this).get(Modell::class.java)
        Log.d("asd","siker")


        val bundle=Bundle()
        val name = bundle.getString("username")
        val token= bundle.getString("token")
        val email= bundle.getString("email")
        val phone_number=bundle.getString("phoneNumber")
        val creation_time =bundle.getString("creationTime")
        val refresh_time=bundle.getString("refreshTime")
        Log.d("test",""+email.toString())
        model.name = name.toString()
        model.email=email.toString()
        model.token=token.toString()
        model.phone_number=phone_number.toString()
        model.creation_time=creation_time.toString()
        model.refresh_time=refresh_time.toString()


        val bottomNavigationView=findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navController=findNavController(R.id.fragmentContainerView)
        bottomNavigationView.setupWithNavController(navController)

        refreshToken()
    }


    private fun refreshToken() {
        handler = Handler()
        handler.postDelayed(runnable, TimeUnit.MINUTES.toMillis(2))
    }
    override fun onDestroy() {
        handler.removeCallbacks(runnable)
        super.onDestroy()
    }
    }
