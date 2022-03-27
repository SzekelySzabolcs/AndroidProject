package com.example.project.fragment

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.example.project.Model.productAdd
import com.example.project.Modell
import com.example.project.R
import com.example.project.Retrofit.ApiClient
import com.example.project.Retrofit.RetroService
import com.example.project.databinding.FragmentAddProductBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Add_Product : Fragment() {
    private lateinit var binding: FragmentAddProductBinding

    private val price_type = arrayOf("RON", "RUB", "EUR", "GBP", "USD")
    private val amount_type = arrayOf("kg", "g", "t")
    private var true_false:Boolean=false
    private var a_type=""
    private var p_type=""

    private val model: Modell by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAddProductBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.username.setText(model.name)
        binding.email.setText(model.email)
        binding.phoneNumber.setText(model.phone_number)

        binding.switchButton.setOnCheckedChangeListener { button, b ->
            if (b) {

                binding.textSetting.setText("Active")
                true_false=true
            } else {

                binding.textSetting.setText("Inactive")
                true_false=false
            }
        }

        binding.spinnerPrice.adapter =
            context?.let {
                ArrayAdapter<String>(
                    it,
                    android.R.layout.simple_spinner_dropdown_item,
                    price_type
                )
            }
        binding.spinnerPrice.onItemSelectedListener = object
            : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                p_type=price_type[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(context, "Please Select an Option", Toast.LENGTH_SHORT).show()
            }

        }


        binding.spinnerAmount.adapter =
            context?.let {
                ArrayAdapter<String>(
                    it,
                    android.R.layout.simple_spinner_dropdown_item,
                    amount_type
                )
            }
        binding.spinnerAmount.onItemSelectedListener = object
            : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                a_type=amount_type[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(context, "Please Select an Option", Toast.LENGTH_SHORT).show()
            }

        }

            binding.Launch.setOnClickListener {

                val token=model.token
                val title=binding.titleEditText.text.toString()
                val descr=binding.descriptionText.text.toString()
                val price_un=binding.priceInputText.text.toString()
                val units=binding.amountText.text.toString()
                val is_activate=true_false
                val rating=0.0
                val amount_type=a_type
                val price_type=p_type

                val request= ApiClient.buildService(RetroService::class.java)
                val call = request.AddProd(token,title,descr,price_un,units,is_activate,rating,amount_type,price_type)
                call.enqueue(object :Callback<productAdd>{
                    override fun onResponse(call: Call<productAdd>, response: Response<productAdd>) {
                        TODO("Not yet implemented")
                    }

                    override fun onFailure(call: Call<productAdd>, t: Throwable) {
                        TODO("Not yet implemented")
                    }

                })

            }

            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragmentContainerView,MyMarket.newInstance())
                ?.commit()


        binding.Preview.setOnClickListener {

        }

//image
        val imageUp=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result->
            val utiImage=result.data?.data
            binding.imageUp.setImageURI(utiImage)
        }
        binding.imageUp.setOnClickListener {
            val intente= Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            imageUp.launch(intente)
        }


    }



    companion object {

        fun newInstance() = Add_Product()
    }
}