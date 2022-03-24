package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.project.fragment.Timeline
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity2 : AppCompatActivity() {
  lateinit var viewmodel:Modell
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        Log.d("asd","siker")
        viewmodel= ViewModelProvider(this).get(Modell::class.java)

        val bundle=Bundle()
        val name = bundle.getString("username")
        val token= bundle.getString("token")
        val email= bundle.getString("email")
        val phone_number=bundle.getString("phoneNumber")
        val creation_time =bundle.getString("creationTime")
        val refresh_time=bundle.getString("refreshTime")
        Log.d("test",""+email.toString())
        viewmodel.name = name.toString()
        viewmodel.email=email.toString()
        viewmodel.token=token.toString()
        viewmodel.phone_number=phone_number.toString()
        viewmodel.creation_time=creation_time.toString()
        viewmodel.refresh_time=refresh_time.toString()


        val bottomNavigationView=findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navController=findNavController(R.id.fragmentContainerView)
        bottomNavigationView.setupWithNavController(navController)
    }




    }
