package com.ashutosh.atm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ashutosh.atm.databinding.RowAtmTranBinding
import com.ashutosh.atm.model.AtmModel
import com.ashutosh.atm.model.AtmTransactionModel

class AtmTransactionAdapter(private val itemListData: List<AtmTransactionModel>) : RecyclerView.Adapter<AtmTransactionAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowAtmTranBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemListData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(itemListData[position])
    }



    class ViewHolder(private val binding: RowAtmTranBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItems(dataitem: AtmTransactionModel) {
            binding.data = dataitem
        }
    }

}