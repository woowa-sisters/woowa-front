package com.example.jakdangmodok

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.jakdangmodok.databinding.ItemNotificationBinding



class NotificationAdapter(val itemList: ArrayList<NotificationItem>) :
    RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false)
        return NotificationViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.n_message.text = itemList[position].text
        holder.n_date.text = itemList[position].date
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }


    class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val n_message  = itemView.findViewById<TextView>(R.id.notice_message)
        val n_date = itemView.findViewById<TextView>(R.id.notice_date)
    }


    }



