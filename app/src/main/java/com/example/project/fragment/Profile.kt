package com.example.project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.project.Modell
import com.example.project.R
import com.example.project.databinding.FragmentProfileBinding


class Profile : Fragment() {
    val viewmodel:Modell by activityViewModels()
    private lateinit var binding: FragmentProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context,""+viewmodel.name,Toast.LENGTH_SHORT).show()
        binding.username.text=viewmodel.name
        binding.email.text=viewmodel.email
        binding.PhoneNumber.text=viewmodel.phone_number

        binding.settingsButton.setOnClickListener{
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragmentContainerView,Settings.newInstance())
                ?.commit()
        }
    }
    companion object {

        fun newInstance() = Profile()
    }
}