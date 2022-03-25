package com.example.project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project.R
import com.example.project.databinding.FragmentCreateYourFareBinding


class Create_your_fare : Fragment() {
    private lateinit var binding: FragmentCreateYourFareBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCreateYourFareBinding.inflate(layoutInflater)
        return binding.root

    }

    companion object {


        @JvmStatic
        fun newInstance() = Create_your_fare()
    }
}