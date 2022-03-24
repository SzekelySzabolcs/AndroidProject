package com.example.project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project.databinding.FragmentMyFaresBinding


class MyFares : Fragment() {

    private lateinit var binding: FragmentMyFaresBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMyFaresBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {

        fun newInstance() = MyFares()

    }
}