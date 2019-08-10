package com.toshiro.hdwallper.view.fragment.favourite.favouriteGIF

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.toshiro.hdwallper.R
import com.toshiro.hdwallper.adapter.AdapterFavouriteImage
import com.toshiro.hdwallper.adapter.AdapterImageLatest
import com.toshiro.hdwallper.database.Database
import com.toshiro.hdwallper.listener.RecyclerViewClickListener
import com.toshiro.hdwallper.model.HDWALLPAPERItem
import com.toshiro.hdwallper.view.activity.DetailImageActivity
import com.toshiro.hdwallper.view.fragment.favourite.favouriteGIF.detailWallpaper.DetailWallpaperActivity
import kotlinx.android.synthetic.main.fragment_fav_wallpaper.*
import kotlinx.android.synthetic.main.fragment_fav_wallpaper.view.*

class FragmentFavWall : Fragment() {

    lateinit var viewRoot : View
//    lateinit var adapterImageLatest: AdapterImageLatest
    lateinit var adapterFavouriteImage: AdapterFavouriteImage
    lateinit var linearLayoutManager : RecyclerView.LayoutManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewRoot = inflater.inflate(R.layout.fragment_fav_wallpaper,container,false)

        val database = Database(context!!)

        linearLayoutManager = GridLayoutManager(context, 2)
        viewRoot.rv_fav_wallpaper.setHasFixedSize(true)
        viewRoot.rv_fav_wallpaper.layoutManager = linearLayoutManager

        adapterFavouriteImage = AdapterFavouriteImage(this.context!!, database.getAllFavoritesImage() as MutableList<HDWALLPAPERItem>,object : RecyclerViewClickListener {
            override fun onClick(position: Int) {
                val intent = Intent(context, DetailWallpaperActivity::class.java)
                intent.putExtra("idImage",database.getAllFavoritesImage()[position].id)
                startActivity(intent)
            }
        },viewRoot.ll_fav_wallpaper)

        viewRoot.rv_fav_wallpaper.adapter = adapterFavouriteImage
        adapterFavouriteImage.notifyDataSetChanged()

        return viewRoot
    }
}