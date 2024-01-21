package com.example.jakdangmodok

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jakdangmodok.databinding.ItemGroupBinding

class GroupViewHolder(val binding: ItemGroupBinding): RecyclerView.ViewHolder(binding.root) {
    init {
        binding.root.setOnClickListener {
            val intent = Intent(binding.root.context, GroupDetailsActivity::class.java)
            binding.root.context.startActivity(intent)
        }
    }
}

class GroupAdapter(val groupList: ArrayList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //override fun getItemCount(): Int = groupList.size
    override fun getItemCount(): Int = 10   // 데이터 받아오기 전 테스트용

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder
            = GroupViewHolder(ItemGroupBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as GroupViewHolder).binding

        binding.groupTitle.text = "모임 %d".format(position)
        binding.groupPlace.text = "종합운동장 %d번 출구".format(position)
        binding.groupTime.text = "오후 %d시 %d분".format(position, position+5)
        binding.groupMemberCount.text = "%d/%d".format(position, position+10)
        //binding.groupImage.setImageResource(R.drawable.group)
    }

}