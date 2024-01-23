package com.example.retrofit

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private var user: List<User>,private val context: Context, private val listener: UserListener) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView = itemView.findViewById(R.id.tv_id)
        var userId: TextView = itemView.findViewById(R.id.tv_user_id)
        var title: TextView = itemView.findViewById(R.id.tv_title)
        var body: TextView = itemView.findViewById(R.id.tv_body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        return UserViewHolder(itemView)
    }
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.id.text = context.getString(R.string.id) + " : ${user[position].id}"
        holder.userId.text = context.getString(R.string.user_id) + " : ${user[position].userId}"
        holder.title.text = context.getString(R.string.title) + " : ${user[position].title}"
        holder.body.text = context.getString(R.string.body) + " : ${user[position].body}"
        holder.body.setOnClickListener {
            listener.onClick(user[position])
        }



    }

    override fun getItemCount() = user.size

}

