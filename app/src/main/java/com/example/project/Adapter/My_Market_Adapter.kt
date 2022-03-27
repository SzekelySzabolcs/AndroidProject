package com.example.project.Adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.MainActivity
import com.example.project.Products.product_class
import com.example.project.R
import com.example.project.fragment.Product_detail_preview

class My_Market_Adapter(var data:ArrayList<product_class>, val context: Context, val onClickDelete:(Int, String)->Unit)
    : RecyclerView.Adapter<My_Market_Adapter.ViewHolder>(){
    private lateinit var image_place: View
    private lateinit var price: TextView
    private lateinit var username: TextView
    private lateinit var prod_name: TextView
    private lateinit var active: TextView
    private lateinit var icon: ImageView
    private lateinit var delete: ImageButton
    private lateinit var button: LinearLayout


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_layout_my_market, parent, false)
        return ViewHolder(v)
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(dat:product_class, index:Int){

            // image_place=itemView.findViewById(R.id.image_prod)
            price=itemView.findViewById(R.id.price)
            username=itemView.findViewById(R.id.username)
            prod_name=itemView.findViewById(R.id.prod_name)
            icon=itemView.findViewById(R.id.active_icon)
            active=itemView.findViewById(R.id.active_)
            delete =itemView.findViewById(R.id.delete)
            button =itemView.findViewById(R.id.button)

            delete.setOnClickListener {
                onClickDelete(index,dat.product_id)
            }

            if(dat.is_active.equals("true")){
                active.setText("Active")
                active.setTextColor(Color.parseColor("#00B5C0"))
                icon.setBackgroundResource(R.drawable.vector_icon)
            }
            else{
                active.setText("Inactive")
                active.setTextColor(Color.GRAY)
                icon.setBackgroundResource(R.drawable.union_icon)
            }
            price.text=dat.price_per_unit+" "+dat.price_type+"/"+dat.amount_type
            username.text=dat.username
            prod_name.text=dat.title

            Log.d("user_adapter_m",""+dat.username)
            Log.d("user_adapter_m",""+dat.product_id)

            button.setOnClickListener(){
                (context as MainActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, Product_detail_preview.newInstance(dat.product_id,dat.username))
                    .commit()
            }
        }

    }

    override fun onBindViewHolder(holder: My_Market_Adapter.ViewHolder, position: Int) {
        holder.bind(data[position],position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun setItem(items:ArrayList<product_class>){
        data=items
        notifyDataSetChanged()
    }
}