package com.example.project.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.project.MainActivity
import com.example.project.MainActivity2
import com.example.project.Products.product_class
import com.example.project.R
import com.example.project.fragment.Product_detail_preview
import kotlin.random.Random

class CustomAdapter(var data:ArrayList<product_class>, val context: Context):RecyclerView.Adapter<CustomAdapter.ViewHolder>(){

    private lateinit var image_place: View
    private lateinit var price: TextView
    private lateinit var username: TextView
    private lateinit var prod_name: TextView
    private lateinit var button_buy: ImageButton

    val url= listOf<String>("https://cdn.pixabay.com/photo/2018/01/14/23/12/nature-3082832__340.jpg",
        "https://media.hazipatika.com/cikkek/main/32/2632//alma_alma.jpg",
        "https://www.pcbolt.eu/shop_ordered/1357/pic/slimpc.jpg",
        "https://media.hazipatika.com/cikkek/main//0/szolo_n_2-m.jpg?1565786262762"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout, parent, false)
        Log.d("adapter","siker0")
        return ViewHolder(v)
    }
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(dat:product_class, index:Int){
            Log.d("adapter","siker1")
            image_place=itemView.findViewById(R.id.image_place)
            price=itemView.findViewById(R.id.price)
            username=itemView.findViewById(R.id.username)
            prod_name=itemView.findViewById(R.id.prod_name)
            val randurl= Random.nextInt(4)

            Glide.with(context).load(url[randurl]).apply(RequestOptions.centerCropTransform()).into(image_place as ImageView)


            button_buy=itemView.findViewById(R.id.button_buy)
            price.text=dat.price_per_unit+" "+dat.price_type+"/"+dat.amount_type
            username.text=dat.username
            prod_name.text=dat.title

            Log.d("user_adapter",""+dat.username)
            Log.d("user_adapter",""+dat.username)
            button_buy.setOnClickListener(){
                (context as MainActivity2).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, Product_detail_preview.newInstance(dat.product_id,dat.username))
                    .commit()
            }
        }

    }
    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder.bind(data[position],position)
        Log.d("adapter","siker2")
    }

    override fun getItemCount(): Int {
        return data.size
    }


}