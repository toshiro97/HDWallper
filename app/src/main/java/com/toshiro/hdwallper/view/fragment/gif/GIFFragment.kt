package com.toshiro.hdwallper.view.fragment.gif

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import com.toshiro.hdwallper.R
import com.toshiro.hdwallper.adapter.AdapterGif
import com.toshiro.hdwallper.listener.RecyclerViewClickListener
import com.toshiro.hdwallper.model.giftImage.GIFItem
import com.toshiro.hdwallper.view.activity.DetailGifActivity
import io.reactivex.disposables.CompositeDisposable
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.fragment_gif.*
import kotlinx.android.synthetic.main.fragment_gif.view.*

class GIFFragment : Fragment(),GIFViewInterface {
    override fun onLoadSuccess(listGIF: List<GIFItem>) {
        adapterGif = AdapterGif(this.context!!,listGIF,object : RecyclerViewClickListener {
            override fun onClick(position: Int) {
                val intent = Intent(context, DetailGifActivity::class.java)
                intent.putExtra("imageDetail",listGIF[position])
                startActivity(intent)
            }
        },fragment_gif_view)
        val adapterAnim = ScaleInAnimationAdapter(adapterGif)
        adapterAnim.setFirstOnly(true)
        adapterAnim.setDuration(1000)
        adapterAnim.setInterpolator(OvershootInterpolator(.9f))
        viewRoot.rv_gif.adapter = adapterAnim
    }

    override fun onLoadFailed(message: String) {
        viewRoot.rv_gif.visibility = View.INVISIBLE
        viewRoot.tv_empty_home_latest.visibility = View.VISIBLE
    }

    override fun showProgressBar() {
        viewRoot.progress_gif.visibility = View.VISIBLE
    }

    override fun hiddenProgressBar() {
        viewRoot.progress_gif.visibility = View.INVISIBLE
    }

    lateinit var linearLayoutManager : RecyclerView.LayoutManager
    lateinit var compositeDisposable: CompositeDisposable
    lateinit var gifPresenter: GIFPresenter
    lateinit var adapterGif: AdapterGif
    lateinit var viewRoot : View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewRoot = inflater.inflate(R.layout.fragment_gif,container,false)
        linearLayoutManager = GridLayoutManager(context, 2)

        viewRoot.rv_gif.setHasFixedSize(true)
        viewRoot.rv_gif.layoutManager = linearLayoutManager

        compositeDisposable = CompositeDisposable()

        gifPresenter = GIFPresenter(compositeDisposable,this, this.context!!)
        gifPresenter.loadGif()




        return viewRoot
    }
}