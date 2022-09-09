package com.ashutosh.atm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ashutosh.atm.databinding.RowAtmBalanceBinding
import com.ashutosh.atm.databinding.RowAtmTranBinding
import com.ashutosh.atm.model.AtmModel

class AtmAdapter(private val itemListData: List<AtmModel>) : RecyclerView.Adapter<AtmAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowAtmBalanceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemListData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(itemListData[position])
        holder.setIsRecyclable(false)
    }



    class ViewHolder(private val binding: RowAtmBalanceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItems(dataitem: AtmModel) {
            binding.data = dataitem
        }
    }

}