package com.example.jakdangmodok

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jakdangmodok.databinding.ItemMemberWaitingBinding

class MemberWaitingViewHolder(val binding: ItemMemberWaitingBinding): RecyclerView.ViewHolder(binding.root)

class MemberWaitingAdapter(val memberList: ArrayList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = memberList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder
            = MemberWaitingViewHolder(ItemMemberWaitingBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MemberWaitingViewHolder).binding

        binding.profileImage.setImageResource(R.drawable.ic_launcher_foreground)
        binding.profileName.setText(memberList[position])
    }

}