package com.example.project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.project.Modell
import com.example.project.R
import com.example.project.databinding.FragmentProductDetailPreviewBinding


class Product_detail_preview : Fragment() {
    private lateinit var binding: FragmentProductDetailPreviewBinding
    val model: Modell by activityViewModels()
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString("id")
            param2 = it.getString("username")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentProductDetailPreviewBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    companion object {

        fun newInstance(param1: String, param2: String) =
            Product_detail_preview().apply {
                arguments = Bundle().apply {
                    putString("id", param1)
                    putString("username", param2)
                }
            }
    }
}