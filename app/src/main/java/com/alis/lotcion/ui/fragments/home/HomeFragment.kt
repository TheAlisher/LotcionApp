package com.alis.lotcion.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.alis.lotcion.R
import com.alis.lotcion.base.BaseFragment
import com.alis.lotcion.extensions.showToastShort
import com.alis.lotcion.ui.fragments.home.lots.LotsFragment
import com.alis.lotcion.utils.SimpleTabSelectedListener
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.tabLayout_home
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment<HomeViewModel>(R.layout.fragment_home) {

    override val viewModel by inject<HomeViewModel>()

    override fun initialize() {
        createLotsPager()
    }

    private fun createLotsPager() {
        pager_home.apply {
            adapter = HomePagerAdapter(childFragmentManager)
            offscreenPageLimit = 3
        }
        tabLayout_home.setupWithViewPager(pager_home)
    }

    override fun setupListeners() {
        clickSelectCategory()
        addOnTabSelectedListener()
    }

    private fun clickSelectCategory() {
        button_home_select_category.setOnClickListener {

        }
    }

    private fun addOnTabSelectedListener() {
        tabLayout_home.addOnTabSelectedListener(object : SimpleTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                showToastShort(requireContext(), "Reselected")
            }
        })
    }

    override fun setupObservers() {

    }

    class HomePagerAdapter(fragmentManager: FragmentManager) :
        FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        private val fragmentTitle = mutableListOf(
            "В данный момент",
            "Избранные",
            "Мои аукционы",
        )

        override fun getCount(): Int = fragmentTitle.size

        override fun getItem(position: Int): Fragment {
            val bundle = Bundle()
            bundle.putInt(LotsFragment.ARG_TAB_POSITION, position)
            val lotsFragment = LotsFragment()
            lotsFragment.arguments = bundle
            return lotsFragment
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return fragmentTitle[position]
        }
    }
}