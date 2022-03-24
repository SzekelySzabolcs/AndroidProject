package com.example.project.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project.R
import com.example.project.databinding.FragmentSplashBinding


class Splash : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private var SPLASH_SCREEN_TIME :Long=3500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSplashBinding.inflate(layoutInflater)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler(Looper.myLooper()!!).postDelayed({
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragment_container_view, Login.newInstance())
                ?.commit()

        },SPLASH_SCREEN_TIME)

    }
    companion object {

        @JvmStatic
        fun newInstance() = Splash()
    }
}