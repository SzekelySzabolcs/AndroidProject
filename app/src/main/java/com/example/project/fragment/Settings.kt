package com.example.project.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.project.Model.UserUpdate
import com.example.project.Modell
import com.example.project.R
import com.example.project.Retrofit.ApiClient
import com.example.project.Retrofit.RetroService
import com.example.project.User_Data.UpdateUser
import com.example.project.User_Data.UpdatedData
import com.example.project.databinding.FragmentSettingsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        binding.settingButton.setOnClickListener {
            val body=UserUpdate(
                binding.username.text.toString(),
                binding.phoneNumber.text.toString(),
                binding.emailText.text.toString()
            )


            val request= ApiClient.buildService(RetroService::class.java)
            val call = request.UserUpdate(viewmodel.token,body)

            call.enqueue(object :Callback<UpdateUser>{
                override fun onResponse(call: Call<UpdateUser>, response: Response<UpdateUser>) {
                    Log.d("hiba",""+response.code().toString())
                   if(response.isSuccessful){

                       val name=response.body()
                       viewmodel.name=name?.getUpdatedData()?.getUsername().toString()
                       viewmodel.phone_number=name?.getUpdatedData()?.getPhoneNumber().toString()
                       viewmodel.email=name?.getUpdatedData()?.getEmail().toString()
                       viewmodel.token=name?.getUpdatedData()?.getToken().toString()
                       val username=name?.getUpdatedData()?.getUsername().toString()
                       //Log.d("cccc",""+name?.getUpdatedData()?.getUsername().toString())
                       activity?.supportFragmentManager
                           ?.beginTransaction()
                           ?.replace(R.id.fragmentContainerView,Profile.newInstance(username))
                           ?.commit()

                   }
                }

                override fun onFailure(call: Call<UpdateUser>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    companion object {

        fun newInstance() = Settings()
    }
}