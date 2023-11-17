package com.project.sameer.Adaptor

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.sameer.Constant
import com.project.sameer.DataClass.Drink
import com.project.sameer.R
import com.project.sameer.databinding.ItemlayoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchByNameAdaptor : RecyclerView.Adapter<SearchByNameAdaptor.MyViewHolder>() {
    var clickListener: onPositionSelected? = null
    private var drinkingData:List<Drink> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemlayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.binding.apply {
               Glide.with(holder.itemView.rootView).load(drinkingData[position]?.strDrinkThumb).into(img)
                title.text= drinkingData[position]?.strDrink
                discription.text= drinkingData[position]?.strInstructions
                if (drinkingData[position].strAlcoholic.equals("Alcoholic")){
                    alcholCheck.isChecked=true
                }
                    CoroutineScope(Dispatchers.IO).launch {

                if (Constant.database.userDao().getDrinkById(drinkingData[position].idDrink)!=null){
                    withContext(Dispatchers.Main){


                        Glide.with(holder.itemView.rootView).load(R.drawable.star).into(fvt)
                    }
                    }else{
                        withContext(Dispatchers.Main) {
                            Glide.with(holder.itemView.rootView).load(R.drawable.uncheckstar)
                                .into(fvt)
                        }
                }
                    }
                fvt.setOnClickListener {
                    Glide.with(holder.itemView.rootView).load(R.drawable.star)
                        .into(fvt)
                    //Every time ids add data into db because of test project otherwise
                    // it will have a check
                    clickListener?.sendingPostion(position,drinkingData)
                }

            }
    }

    override fun getItemCount(): Int {
        return drinkingData.size
    }

    fun setData(data: List<Drink>) {
        drinkingData=data
        notifyDataSetChanged()
    }

    class MyViewHolder(val binding: ItemlayoutBinding) : RecyclerView.ViewHolder(binding.root)

    interface onPositionSelected {
        fun sendingPostion(position: Int, drinkingData: List<Drink>)
    }
}