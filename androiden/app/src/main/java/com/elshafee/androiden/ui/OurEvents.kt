package com.elshafee.androiden.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.elshafee.androiden.R
import com.elshafee.androiden.databinding.ActivityOurEventsBinding
import com.elshafee.androiden.ui.utils.ViewPAgerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class OurEvents : Fragment() {

    private var _binding: ActivityOurEventsBinding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityOurEventsBinding.inflate(inflater, container,false)
        var view = binding.root

        var viewPager = view.findViewById<ViewPager2>(R.id.viewPagerOfEvents)
        var tabLayout = view.findViewById<TabLayout>(R.id.tablayoutOfEvents)
        val images = listOf(
            R.drawable.bo,
            R.drawable.welcom,
            R.drawable.onboarding,
            R.drawable.eventone,
            R.drawable.eventtwo,
        )
        val myAdapter = ViewPAgerAdapter(images)
        binding.viewPagerOfEvents.adapter = myAdapter

        TabLayoutMediator(tabLayout,viewPager){tab,position->
            tab.text = "Tab ${position + 1}"
        }.attach()

        binding.tablayoutOfEvents.addOnTabSelectedListener(object :
        TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
              Toast.makeText(activity, "Selected ${tab?.text}",Toast.LENGTH_SHORT).show()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Toast.makeText(activity, "ReSelected ${tab?.text}",Toast.LENGTH_SHORT).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Toast.makeText(activity, "UnSelected ${tab?.text}",Toast.LENGTH_SHORT).show()
            }
        })


        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}