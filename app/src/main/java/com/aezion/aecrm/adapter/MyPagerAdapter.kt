package com.aezion.aecrm.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.aezion.aecrm.fragment.tapLayout.ActivityFragment
import com.aezion.aecrm.fragment.tapLayout.ProposalAndDealFragment
import com.aezion.aecrm.fragment.tapLayout.TaskFragment

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                TaskFragment()
            }
            1 -> ProposalAndDealFragment()
            else -> {
                return ActivityFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "TASKS"
            1 -> "DEALS & PROPOSALS"
            else -> {
                return "ACTIVITY"
            }
        }
    }
}