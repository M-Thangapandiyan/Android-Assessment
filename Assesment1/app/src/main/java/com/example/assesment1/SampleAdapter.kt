package com.example.assesment1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class SampleAdapter : RecyclerView.Adapter<SampleAdapter.SampleAdapterViewHolder>() {

    inner class SampleAdapterViewHolder(itemView: View) : ViewHolder(itemView) {
     val tvText: TextView = itemView.findViewById(R.id.text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleAdapterViewHolder {
        val viewHolder = LayoutInflater.from(parent.context).inflate(R.layout.sample_adapter,parent,false)
        return SampleAdapterViewHolder(viewHolder)
    }

    override fun getItemCount(): Int {
           return 3
    }

    override fun onBindViewHolder(holder: SampleAdapterViewHolder, position: Int) {
           holder.tvText.text =  position.toString()
    }

}