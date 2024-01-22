package com.example.jakdangmodok

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.jakdangmodok.databinding.ItemBookBinding
import com.example.jakdangmodok.databinding.ItemMemberBinding

class MemberViewHolder(val binding: ItemMemberBinding): RecyclerView.ViewHolder(binding.root)

class MemberAdapter(val memberList: ArrayList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = memberList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder
            = MemberViewHolder(ItemMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MemberViewHolder).binding

        binding.profileImage.setImageResource(R.drawable.bookimage)
        binding.profileName.setText(memberList[position])

        if (position == 0) {
            binding.markGroupLeader.isVisible = true
        }
    }

}