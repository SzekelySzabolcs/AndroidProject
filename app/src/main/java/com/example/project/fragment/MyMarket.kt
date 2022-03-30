package com.example.project.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.Adapter.My_Market_Adapter
import com.example.project.Modell
import com.example.project.Products.Login_product
import com.example.project.Products.product_class
import com.example.project.R
import com.example.project.Retrofit.ApiClient
import com.example.project.Retrofit.RetroService
import com.example.project.databinding.FragmentMyMarketBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyMarket : Fragment() {

    val viewmodel: Modell by activityViewModels()
    var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var adapter: My_Market_Adapter

    private lateinit var binding: FragmentMyMarketBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("faszom","test2")
        binding= FragmentMyMarketBinding.inflate(layoutInflater)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addProd.setOnClickListener {
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.fragmentContainerView,Add_Product.newInstance())
                ?.commit()

        }
        Log.d("faszom","test1")
        val request= ApiClient.buildService(RetroService::class.java)
        val call = request.Myproduct(viewmodel.token,"{\"username\":\"${viewmodel.name}\"}")
        call.enqueue(object :Callback<Login_product>{
            override fun onResponse(call: Call<Login_product>, response: Response<Login_product>) {

                val prod_add: ArrayList<product_class> = ArrayList()


                if (response.isSuccessful) {
                    val item_c = response.body()?.getItemCount().toString()
                    val times = response.body()?.getTimestamp().toString()

                    val data = response.body()
                    viewmodel.myMarket.clear()
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

                        prod_add.add(prod)


                        viewmodel.myMarket.add(prod)

                    }

                }
            }

            override fun onFailure(call: Call<Login_product>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        Log.d("testfile",""+viewmodel.myMarket.size.toString())
        layoutManager = LinearLayoutManager(context)
        binding.recycler.layoutManager = layoutManager
        adapter = My_Market_Adapter(viewmodel.myMarket, requireContext()){index,str->deleteItem(index,str)}
        binding.recycler.adapter = adapter
    }

    private fun deleteItem(index: Int,str:String) {
        viewmodel.myMarket.removeAt(index)

        Toast.makeText(context,""+str, Toast.LENGTH_LONG).show()
        val request= ApiClient.buildService(RetroService::class.java)
        val call = request.delete(viewmodel.token,str)
        call.enqueue(object :Callback<Login_product>{
            override fun onResponse(call: Call<Login_product>, response: Response<Login_product>) {

            }

            override fun onFailure(call: Call<Login_product>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
        Log.d("testfile",""+viewmodel.myMarket.size.toString())
        adapter.setItem(viewmodel.myMarket)
    }

    companion object {

        fun newInstance() = MyMarket()
    }
}