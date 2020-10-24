package com.alis.lotcion.ui.fragments.home

import android.app.Activity
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.navigation.Navigation
import androidx.viewpager.widget.ViewPager
import com.alis.lotcion.R
import com.alis.lotcion.extensions.showToastShort
import com.alis.lotcion.ui.fragments.home.lots.LotsFragment
import com.alis.lotcion.utils.SimpleTabSelectedListener
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.tabLayout_home
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {

    companion object {
        fun start(activity: Activity, action: Int) {
            Navigation
                .findNavController(activity, R.id.nav_host_fragment)
                .navigate(action)
        }
    }

    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overrideOnBackPressed()
    }

    private fun overrideOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createLotsPager()
        setupListeners()
    }

    private fun createLotsPager() {
        pager_home.apply {
            adapter = HomePagerAdapter(childFragmentManager)
            offscreenPageLimit = 9
            currentItem = 1
        }
        tabLayout_home.setupWithViewPager(pager_home)
    }

    private fun setupListeners() {
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

    class HomePagerAdapter(fragmentManager: FragmentManager) :
        FragmentPagerAdapter(
            fragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
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