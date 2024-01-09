package com.example.jakdangmodok

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.jakdangmodok.databinding.FragmentSubscribeBinding
import com.google.android.material.tabs.TabLayoutMediator

class SubscribeFragment : Fragment() {
    private val binding by lazy { FragmentSubscribeBinding.inflate(layoutInflater) }
    private val tabMenuList = listOf("책", "모임")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    class SubscribePagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        val fragments: List<Fragment>

        init {
            fragments = listOf(BookFragment(), GroupFragment())
        }

        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding.viewPager.adapter = SubscribePagerAdapter(this)
        binding.viewPager.isSaveEnabled = false

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabMenuList[position]
        }.attach()

        return binding.root
    }

    /*
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SubscribeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    */
}