package com.example.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuoteAdapter(private var quotes: List<Quote>) :
    RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {
    inner class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView = itemView.findViewById(R.id.tv_id)
        var userId: TextView = itemView.findViewById(R.id.tv_user_id)
        var tittle: TextView = itemView.findViewById(R.id.tv_tittle)
        var body: TextView = itemView.findViewById(R.id.tv_body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return QuoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.id.text = quotes[position].id.toString()
        holder.userId.text = quotes[position].userId.toString()
        holder.tittle.text = quotes[position].tittle
        holder.body.text = quotes[position].body
    }

    override fun getItemCount() = quotes.size
}

