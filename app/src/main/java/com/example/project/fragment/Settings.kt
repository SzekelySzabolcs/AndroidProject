package com.example.project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.project.Modell
import com.example.project.R
import com.example.project.databinding.FragmentSettingsBinding

class Settings : Fragment() {
    val viewmodel: Modell by activityViewModels()
    private lateinit var binding: FragmentSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.username.setText(viewmodel.name)
        binding.phoneNumber.setText(viewmodel.phone_number)
        binding.emailText.setText(viewmodel.email)
        binding.password.setText(viewmodel.password)
    }

    companion object {

        fun newInstance() = Settings()
    }
}