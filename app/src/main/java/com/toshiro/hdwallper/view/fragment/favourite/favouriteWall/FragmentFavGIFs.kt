package com.toshiro.hdwallper.view.fragment.favourite.favouriteWall

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.toshiro.hdwallper.R
import com.toshiro.hdwallper.adapter.AdapterFavouriteGif
import com.toshiro.hdwallper.database.Database
import com.toshiro.hdwallper.listener.RecyclerViewClickListener
import com.toshiro.hdwallper.model.giftImage.GIFItem
import com.toshiro.hdwallper.view.fragment.favourite.favouriteWall.detailSingleGif.DetailSingleGifActivity
import kotlinx.android.synthetic.main.fragment_fav_gif.view.*

class FragmentFavGIFs : Fragment() {

    lateinit var viewRoot : View
    lateinit var adapterGif: AdapterFavouriteGif
    lateinit var linearLayoutManager : RecyclerView.LayoutManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewRoot = inflater.inflate(R.layout.fragment_fav_gif,container,false)

        val database = Database(context!!)

        linearLayoutManager = GridLayoutManager(context, 2)
        viewRoot.rv_fav_gif.setHasFixedSize(true)
        viewRoot.rv_fav_gif.layoutManager = linearLayoutManager

        adapterGif = AdapterFavouriteGif(this.context!!, database.getAllFavoritesGif() as MutableList<GIFItem>,object : RecyclerViewClickListener {
            override fun onClick(position: Int) {
                val intent = Intent(context, DetailSingleGifActivity::class.java)
                intent.putExtra("idGif",database.getAllFavoritesGif()[position].id)
                startActivity(intent)
            }
        },viewRoot.ll_fav_gif)

        viewRoot.rv_fav_gif.adapter = adapterGif
        adapterGif.notifyDataSetChanged()

        return viewRoot
    }
}