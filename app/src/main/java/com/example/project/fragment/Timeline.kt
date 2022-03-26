package com.example.project.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.project.Modell
import com.example.project.Products.Login_product
import com.example.project.Products.product_class
import com.example.project.R
import com.example.project.Retrofit.ApiClient
import com.example.project.Retrofit.RetroService
import com.example.project.databinding.FragmentTimelineBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Timeline : Fragment() {
    private lateinit var binding: FragmentTimelineBinding
    val viewmodel: Modell by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentTimelineBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.include.profile.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragmentContainerView,Profile.newInstance(viewmodel.name))
                ?.commit()
        }
        val request= ApiClient.buildService(RetroService::class.java)
        val call = request.product(viewmodel.token,500)
        call.enqueue(object :Callback<Login_product>{
            override fun onResponse(call: Call<Login_product>, response: Response<Login_product>) {
                if (response.isSuccessful) {

                    val data = response.body()

                    for (result in data?.getProducts()!!) {

                        val rating = result?.getRating()?.toInt()
                        val amount_type = result?.getAmountType().toString()
                        val price_type = result?.getPriceType().toString()
                        val product_id = result?.getProductId().toString()
                        val username = result?.getUsername().toString()
                        val is_active = result?.getIsActive().toString()
                        val price_per_unit = result?.getPricePerUnit().toString()
                        val units = result?.getUnits().toString()
                        val description = result?.getDescription().toString()
                        val title = result?.getTitle().toString()
                        val creation_time = result?.getCreationTime().toString()

                        val prod = product_class(
                            rating, amount_type, price_type, product_id, username, is_active,
                            price_per_unit, units, description, title, creation_time
                        )

                        viewmodel.result1.add(prod)

                    }

                }
            }

            override fun onFailure(call: Call<Login_product>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }


    companion object {

        fun newInstance() = Timeline()
    }

}