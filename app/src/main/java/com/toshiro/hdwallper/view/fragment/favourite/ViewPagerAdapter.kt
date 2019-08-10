package com.toshiro.hdwallper.view.fragment.favourite

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.toshiro.hdwallper.view.fragment.favourite.favouriteGIF.FragmentFavWall
import com.toshiro.hdwallper.view.fragment.favourite.favouriteWall.FragmentFavGIFs


class ViewPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        if (position == 0) {
            fragment = FragmentFavWall()
        } else if (position == 1) {
            fragment = FragmentFavGIFs()
        }
        return fragment
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        if (position == 0) {
            title = "WALLPAPER"
        } else if (position == 1) {
            title = "GIFS"
        }
        return title
    }
}