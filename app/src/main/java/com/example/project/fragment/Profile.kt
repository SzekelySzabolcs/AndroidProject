package com.example.project.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.example.project.Modell
import com.example.project.R
import com.example.project.Retrofit.ApiClient
import com.example.project.Retrofit.RetroService
import com.example.project.User_Data.mUser
import com.example.project.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Profile : Fragment() {

    val model:Modell by activityViewModels()
    private var param1: String? = null
    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString("username")
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
        binding.include.textView.setText("Profile")
        binding.include.backButton.setOnClickListener {
            activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.fragmentContainerView,Timeline.newInstance())
                    ?.commit()
        }
        val request= ApiClient.buildService(RetroService::class.java)
        val call = request.user(param1)
        call.enqueue(object: Callback<mUser>{
            override fun onResponse(call: Call<mUser>, response: Response<mUser>) {
                if(response.isSuccessful){
                    val data=response.body()?.getData()
                    if(data?.get(0)?.getUsername().equals(model.name)==true){
                        binding.email.setText(model.email)
                        binding.username.setText(model.name)
                        binding.name.setText(model.name)
                        binding.PhoneNumber.setText(model.phone_number)
                        binding.PublishButton.isVisible=true
                    }
                    else{
                        binding.email.setText(data?.get(0)?.getEmail())
                        binding.name.setText(data?.get(0)?.getUsername())
                        binding.username.setText(data?.get(0)?.getUsername())
                        binding.PhoneNumber.setText(data?.get(0)?.getPhoneNumber().toString())
                        binding.PublishButton.isVisible=false
                    }


                }
            }

            override fun onFailure(call: Call<mUser>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


        binding.PublishButton.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragmentContainerView,Settings.newInstance())
                ?.commit()
        }
    }
    companion object {

        fun newInstance(param1: String) = Profile().apply {
            arguments = Bundle().apply {
                putString("username", param1)

            }
        }
    }
}