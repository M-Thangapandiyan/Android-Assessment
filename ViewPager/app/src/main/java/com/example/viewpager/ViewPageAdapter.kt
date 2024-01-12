package com.example.viewpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewPageAdapter() : RecyclerView.Adapter<ViewPageAdapter.ViewPageViewHolder>(){
    inner class ViewPageViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
       var tvText: TextView = itemView.findViewById(R.id.tv_viewpage)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_pager,parent,false)
        return ViewPageViewHolder(view)
    }
    override fun getItemCount(): Int {
      return 2
    }
    override fun onBindViewHolder(holder: ViewPageViewHolder, position: Int) {
        holder.tvText.text =  position.toString()
    }
}