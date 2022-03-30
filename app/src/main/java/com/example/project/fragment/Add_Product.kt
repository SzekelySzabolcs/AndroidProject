package com.example.project.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
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
    private val model: Modell by activityViewModels()
    private lateinit var binding: FragmentAddProductBinding
    private val price_type = arrayOf("RON", "RUB", "EUR", "GBP", "USD")
    private val amount_type = arrayOf("kg", "g", "t")
    private var true_false:Boolean=false
    private var a_type=""
    private var p_type=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddProductBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //xml adatok beilesztese
        binding.username.setText(model.name)
        binding.email.setText(model.email)
        binding.phoneNumber.setText(model.phone_number)

        //switch active or inactive
        binding.switchButton.setOnCheckedChangeListener { button, b ->
            if (b) {

                binding.textSetting.setText("Active")
                true_false=true
            } else {

                binding.textSetting.setText("Inactive")
                true_false=false
            }
        }


        //spinner
        binding.spinnerPrice.adapter =
            context?.let {
                ArrayAdapter<String>(
                    it,
                    android.R.layout.simple_spinner_dropdown_item,
                    price_type
                )
            }

        binding.spinnerAmount.adapter =
            context?.let {
                ArrayAdapter<String>(
                    it,
                    android.R.layout.simple_spinner_dropdown_item,
                    amount_type
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
        binding.spinnerAmount.onItemSelectedListener = object
            : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                a_type=amount_type[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(context, "Please Select an Option", Toast.LENGTH_SHORT).show()
            }

        }


        Titlevalid()
        price()
        amount()
        description()

        binding.Launch.setOnClickListener {
            submitForm()
        }

    }

    private fun submitForm(){
        binding.titleInputLayout.helperText=Title_Test()
        binding.descriptionLayout.helperText =description_test()
        binding.priceLayout.helperText=price_test()
        binding.amountLayout.helperText=amount_test()

        val title_test=binding.titleInputLayout.helperText == null
        val description_test=binding.descriptionLayout.helperText == null
        val price_unit_test=binding.priceLayout.helperText== null
        val units_test=binding.amountLayout.helperText==null
        val is_activate=true_false
        val rating=0.0
        val amount_type=a_type
        val price_type=p_type
        val token=model.token

        if (title_test && description_test && price_unit_test && units_test ){
            Log.d("tester","title "+title_test)
            val title=binding.titleEditText.text.toString()
            val description=binding.descriptionText.text.toString()
            val price_unit=binding.priceInputText.text.toString()
            val units=binding.amountText.text.toString()
            Log.d("tester","1")
            val request= ApiClient.buildService(RetroService::class.java)
            val call = request.AddProd(token,title,description,price_unit,units,is_activate,rating,amount_type,price_type)
            call.enqueue(object :Callback<productAdd>{
                override fun onResponse(call: Call<productAdd>, response: Response<productAdd>) {
                    Log.d("tester","2")


                }

                override fun onFailure(call: Call<productAdd>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
            Log.d("tester","3")
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainerView,MyMarket.newInstance())
                ?.commit()
        }
        else{
            Toast.makeText(context,"nincs jol ki toltve",Toast.LENGTH_LONG).show()
        }
    }









    private fun description() {
        binding.descriptionText.setOnFocusChangeListener { view, b ->
            if (!b) {
                binding.descriptionLayout.helperText = description_test()
            }
        }
    }

    private fun description_test(): CharSequence? {
        val text = binding.descriptionText.text.toString()
        if (text.length > 150) {
            return "Max 150"

        }
        return null

    }

    private fun amount() {
        binding.amountText.setOnFocusChangeListener { view, b ->
            if (!b) {
                binding.amountLayout.helperText = amount_test()
            }

        }
    }

    private fun amount_test(): CharSequence? {
        val text = binding.amountText.text.toString()
        if (text.length > 10) {
            return "Max 10"

        }
        return null

    }

    private fun price() {
        binding.priceInputText.setOnFocusChangeListener { view, b ->
            if (!b) {
                binding.priceLayout.helperText = price_test()
            }
        }
    }

    private fun price_test(): CharSequence? {
        val text = binding.priceInputText.text.toString()
        if (text.length > 10) {
            return "Max 10"

        }
        return null
    }

    private fun Titlevalid() {
        binding.titleEditText.setOnFocusChangeListener { view, b ->
            if (!b) {
                binding.titleInputLayout.helperText = Title_Test()
            }
        }
    }

    private fun Title_Test(): CharSequence? {
        val text = binding.titleEditText.text.toString()
        if (text.length > 30) {
            return "Max 30"

        }
        return null
    }



    companion object {

        @JvmStatic
        fun newInstance() = Add_Product()
    }
}