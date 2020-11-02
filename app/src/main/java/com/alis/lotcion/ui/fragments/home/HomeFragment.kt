package com.alis.lotcion.ui.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.alis.lotcion.R
import com.alis.lotcion.base.BaseFragment
import com.alis.lotcion.extensions.showToastShort
import com.alis.lotcion.ui.fragments.home.lots.LotsFragment
import com.alis.lotcion.utils.SimpleTabSelectedListener
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.tabLayout_home
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel>(R.layout.fragment_home) {

    override val viewModel by viewModel<HomeViewModel>()

    override fun initialize() {
        requireActivity().currentFocus?.clearFocus()

        createLotsPager()
    }

    private fun createLotsPager() {
        pager_home.apply {
            adapter = HomePagerAdapter(childFragmentManager)
            offscreenPageLimit = 9
            currentItem = 1
        }
        tabLayout_home.setupWithViewPager(pager_home)
    }

    override fun setupListeners() {
        addOnTabSelectedListener()
        addOnPageChangeListener()
    }

    private fun addOnTabSelectedListener() {
        tabLayout_home.addOnTabSelectedListener(object : SimpleTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                setTabColor()
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                showToastShort(requireContext(), "Reselected")
            }
        })
    }

    private fun addOnPageChangeListener() {
        pager_home.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                setTabColor()
            }
        })
    }

    private fun setTabColor() {
        if (tabLayout_home.selectedTabPosition == 0) {
            tabLayout_home.setTabTextColors(
                resources.getColor(R.color.Gray, null),
                resources.getColor(R.color.Red, null)
            )
        } else {
            tabLayout_home.setTabTextColors(
                resources.getColor(R.color.Gray, null),
                resources.getColor(R.color.Black, null)
            )
        }
    }

    override fun observe() {

    }

    class HomePagerAdapter(fragmentManager: FragmentManager) :
        FragmentPagerAdapter(
            fragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        ) {

        private val fragmentTitle = mutableListOf(
            "Избранные",
            "Все",
            "Исскуство",
            "Книги",
            "Рукоделие",
            "Недвижимость",
            "Транспорт",
            "Аксессуары",
            "Электроника",
            "Животные"
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