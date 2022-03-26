package com.example.project.fragment

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.project.Modell
import com.example.project.R
import com.example.project.databinding.FragmentProductDetailPreviewBinding
import kotlin.random.Random


class Product_detail_preview : Fragment() {
    private lateinit var binding: FragmentProductDetailPreviewBinding
    val model: Modell by activityViewModels()
    private var param1: String? = null
    private var param2: String? = null
    var asd=""

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
        for (i in model.result1){
            if (i.product_id.equals(param1)){
                binding.price.setText(""+i.price_per_unit+"/"+i.price_type)
                binding.prodName.setText(i.title)
                binding.description.setText(i.description)
                binding.totalItemsAmountType.setText(i.amount_type)
                binding.pricePerUnit.setText(i.price_per_unit)
                binding.priceType.setText(i.price_type)
                binding.usernameName.setText(i.username)
                val randurl= Random.nextInt(4)
                //Glide.with(this).load(url[randurl]).apply(RequestOptions.centerCropTransform()).into(binding.imageProd)

                if(i.is_active.equals("true")){
                    binding.active.setText("Active")
                    binding.active.setTextColor(Color.parseColor("#00B5C0"))
                    binding.activeIcon.setBackgroundResource(R.drawable.vector_icon)
                }
                else{
                    binding.active.setText("Inactive")
                    binding.active.setTextColor(Color.GRAY)
                   // binding.activeIcon.setBackgroundResource(R.drawable.union_icon)
                }
            }
        }

        asd=param2.toString()
        binding.avatar.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragmentContainerView,Profile.newInstance(asd))
                ?.commit()
        }

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