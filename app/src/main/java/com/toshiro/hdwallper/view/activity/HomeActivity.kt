package com.toshiro.hdwallper.view.activity

import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.WindowManager
import com.toshiro.hdwallper.R
import com.toshiro.hdwallper.view.fragment.*
import com.toshiro.hdwallper.view.fragment.category.CategoryFragment
import com.toshiro.hdwallper.view.fragment.favourite.FavouriteFragment
import com.toshiro.hdwallper.view.fragment.gif.GIFFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import com.toshiro.hdwallper.view.fragment.latest.LatestFragment


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        toolbar.title = "Latest"

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        selectDrawerItem(R.id.nav_home)
        changeStatusBarColor()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        selectDrawerItem(item.itemId)
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


    private fun selectDrawerItem(id: Int) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        var fragment : Fragment? = null
        when (id) {
            R.id.nav_home ->{
                fragment = LatestFragment()
                toolbar.title = "Latest"
            }
            R.id.nav_favourites -> {
                fragment = FavouriteFragment()
                toolbar.title = "Favourite"
            }

            R.id.nav_gifs -> {
                fragment = GIFFragment()
                toolbar.title = "GIFs"
            }
            R.id.nav_categories -> {
                fragment = CategoryFragment()
                toolbar.title = "Category"
            }
//            R.id.nav_shareApp -> {}
//            R.id.nav_about -> fragment = AboutFragment()

        }

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.container, fragment)
        ft.commit()

        drawer_layout.closeDrawer(GravityCompat.START)
    }


    private fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this@HomeActivity, R.color.statusbar)
        }

    }
}
