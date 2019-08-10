package com.toshiro.hdwallper.view.fragment.favourite

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.toshiro.hdwallper.R
import kotlinx.android.synthetic.main.fragment_favourite.view.*

class FavouriteFragment : Fragment() {


    lateinit var viewRoot : View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewRoot = inflater.inflate(R.layout.fragment_favourite, container, false)


        val adapter = ViewPagerAdapter(childFragmentManager)
        viewRoot.viewpager_fav.adapter = adapter
        viewRoot.tabs_fav.setupWithViewPager(viewRoot.viewpager_fav);

        viewRoot.viewpager_fav.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                viewRoot.tabs_fav.setScrollPosition(position, 0F,true)
                viewRoot.tabs_fav.isSelected = true


            }
        })

        viewRoot.tabs_fav.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewRoot.viewpager_fav.currentItem = tab!!.position
            }

        })

        return viewRoot
    }



}