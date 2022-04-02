package com.example.project.fragment

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.project.Model.RegisterRequest
import com.example.project.Model.RegisterRespons

import com.example.project.R
import com.example.project.Retrofit.ApiClient
import com.example.project.Retrofit.RetroService
import com.example.project.databinding.FragmentRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register : Fragment() {

    private lateinit var binding:FragmentRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding= FragmentRegisterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        UsernameFocusListenre()
        LastnameFocusListenre()
        EmailFocusListenre()
        PasswordFocusListenre()

        binding.loginButton.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragment_container_view,Login.newInstance())
                ?.commit()
        }
        binding.registerButton.setOnClickListener {
            submitForm()
        }
    }

    private fun submitForm() {
        val validUserName=binding.textInputLayoutName.helperText == null
        val validLastName=binding.textInputLayoutPhone.helperText == null
        val validEmail=binding.textInputLayoutEmail.helperText == null
        val validPassword=binding.textInputLayoutPassword.helperText == null
        Log.d("tester",""+validUserName)
        Log.d("tester",""+validLastName)
        Log.d("tester",""+validEmail)
        Log.d("tester",""+validPassword)
        if (validUserName && validLastName && validEmail && validPassword){
            Log.d("tester","dsadasdas")
            val body = RegisterRequest(
                binding.testInputEditTextName.text.toString(),
                binding.testInputEditTextPassword.text.toString(),
                binding.testInputEditTextEmail.text.toString(),
                binding.testInputEditTextPhone.text.toString()
            )
            Log.d("tester",""+ binding.testInputEditTextName.text.toString())
            Log.d("tester",""+binding.testInputEditTextPassword.text.toString())
            Log.d("tester",""+binding.testInputEditTextEmail.text.toString())
            Log.d("tester",""+ binding.testInputEditTextPhone.text.toString())
            val request= ApiClient.buildService(RetroService::class.java)
            val call =request.registerUser(body)
            call.enqueue(object :Callback<RegisterRespons>{
                override fun onResponse(
                    call: Call<RegisterRespons>,
                    response: Response<RegisterRespons>
                ) {
                    Log.d("hiba",""+response.code().toString())
                    if(response.isSuccessful){
                        Toast.makeText(context,"Siker",Toast.LENGTH_LONG).show()
                        Toast.makeText(requireContext(),"Siker",Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<RegisterRespons>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
                  }
        else{
            invalidForm()
        }
    }

    private fun invalidForm() {
        if (binding.textInputLayoutName.helperText != null){
            Toast.makeText(context,"UserName clear",Toast.LENGTH_LONG).show()
        }
        if (binding.textInputLayoutPhone.helperText == null){
            Toast.makeText(context,"LastName clear",Toast.LENGTH_LONG).show()
        }
        if (binding.textInputLayoutEmail.helperText == null){
            Toast.makeText(context,"Invalid Email",Toast.LENGTH_LONG).show()
        }
        if (binding.textInputLayoutPassword.helperText == null){
            Toast.makeText(context,"Minimum 5 Character Password ",Toast.LENGTH_LONG).show()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = Register()
    }

    private fun UsernameFocusListenre() {
        binding.testInputEditTextName.setOnFocusChangeListener { _, b ->
            if(!b){
                binding.textInputLayoutName.helperText=validName()
            }
        }
    }

    private fun validName(): CharSequence? {
        val nameText=binding.testInputEditTextName.text.toString()
        if (nameText.isEmpty()){
            return "Clear"
        }

        return null
    }

    private fun LastnameFocusListenre() {
        binding.testInputEditTextPhone.setOnFocusChangeListener { _, b ->
            if(!b){
                binding.textInputLayoutPhone.helperText=validLastName()
            }
        }
    }

    private fun validLastName(): CharSequence? {
        val nameText=binding.testInputEditTextPhone.text.toString()
        if (nameText.isEmpty()){
            return "Clear"
        }
        return null
    }


    private fun EmailFocusListenre() {
        binding.testInputEditTextEmail.setOnFocusChangeListener { _, b ->
            if(!b){
                binding.textInputLayoutEmail.helperText=validEmail()
            }
        }
    }

    private fun validEmail(): CharSequence? {
        val nameText=binding.testInputEditTextEmail.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(nameText).matches()){
            return "Invalid Email"
        }
        return null
    }
    private fun PasswordFocusListenre() {
        binding.testInputEditTextPassword.setOnFocusChangeListener { _, b ->
            if(!b){
                binding.textInputLayoutPassword.helperText=validPassword()
            }
        }
    }

    private fun validPassword(): CharSequence? {
        val nameText=binding.testInputEditTextPassword.text.toString()
        if (nameText.length<5){
            return "Minimum 5 Character Password "
        }
        return null
    }
}