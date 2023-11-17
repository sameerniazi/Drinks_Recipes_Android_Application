package com.project.sameer.Adaptor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.sameer.Constant
import com.project.sameer.DataClass.Drink
import com.project.sameer.DataClass.RoomData
import com.project.sameer.R
import com.project.sameer.databinding.ItemlayoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FvtAdaptor : RecyclerView.Adapter<FvtAdaptor.MyViewHolder>() {
    var clickListener: SearchByNameAdaptor.onPositionSelected? = null
    private var drinkingData:List<RoomData> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemlayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.apply {
            Glide.with(holder.itemView.rootView).load(drinkingData[position]?.img).into(img)
            title.text= drinkingData[position]?.name
            discription.text= drinkingData[position]?.discription
            if (drinkingData[position].alchole.equals("Alcoholic")){
                alcholCheck.isChecked=true
            }
            Glide.with(holder.itemView.rootView).load(R.drawable.star).into(fvt)



        }
    }

    override fun getItemCount(): Int {
        return drinkingData.size
    }

    fun setData(data: List<RoomData>) {
        drinkingData=data
        notifyDataSetChanged()
    }

    class MyViewHolder(val binding: ItemlayoutBinding) : RecyclerView.ViewHolder(binding.root)

}