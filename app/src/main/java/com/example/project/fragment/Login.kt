package com.example.project.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.project.MainActivity2
import com.example.project.Model.LoginRequest
import com.example.project.Model.LoginResponse
import com.example.project.Modell

import com.example.project.R
import com.example.project.Retrofit.ApiClient
import com.example.project.Retrofit.RetroService
import com.example.project.databinding.FragmentLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Login : Fragment() {
    private lateinit var binding:FragmentLoginBinding

     val model:Modell by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentLoginBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonSignUp.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragment_container_view,Register.newInstance())
                ?.commit()

        }
        binding.forgotPass.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragment_container_view,Forgot_password.newInstance())
                ?.commit()
        }
        UsernameFocusListenre()
        PasswordFocusListenre()

        binding.loginButton.setOnClickListener {
            submitForm()
        }
    }

    private fun submitForm() {
        val validUserName=binding.textInputLayout.helperText == null
        val validPassword=binding.textInputLayoutPassword.helperText == null

        if (validUserName  && validPassword){
            val body = LoginRequest(
                binding.testInputEditText.text.toString(),
                binding.testInputEditTextPassword.text.toString()
            )
            val request=ApiClient.buildService(RetroService::class.java)
            val call = request.login(body)
            call.enqueue(object: Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {

                    if(response.isSuccessful){

                        val name = response.body()?.username.toString()
                        val email = response.body()?.email.toString()
                        val phone_number = response.body()?.phone_number
                        val token = response.body()?.token.toString()
                        val creation_time = response.body()?.creation_time
                        val refresh_time = response.body()?.refresh_time

                        val intent = Intent(context,MainActivity2::class.java)
                        intent.putExtra("token",token)
                        intent.putExtra("username",name)
                        intent.putExtra("email",email)
                        intent.putExtra("phone_number",phone_number)
                        intent.putExtra("creation_time",creation_time)
                        intent.putExtra("refresh_time",refresh_time)

                        startActivity(intent)


                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {

                }

            })

        }
        else{
            invalidForm()
        }
    }

    private fun invalidForm() {
        if (binding.textInputLayout.helperText != null){
            Toast.makeText(context,"UserName clear", Toast.LENGTH_LONG).show()
        }

        if (binding.textInputLayoutPassword.helperText == null){
            Toast.makeText(context," Password Clear", Toast.LENGTH_LONG).show()
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() = Login()
    }

    private fun UsernameFocusListenre() {
        binding.testInputEditText.setOnFocusChangeListener { _, b ->
            if(!b){
                binding.textInputLayout.helperText=validName()
            }
        }
    }

    private fun validName(): CharSequence? {
        val nameText=binding.testInputEditText.text.toString()
        if (nameText.isEmpty()){
            return "Clear"
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
        if (nameText.isEmpty()){
            return "Clear"
        }
        return null
    }
}