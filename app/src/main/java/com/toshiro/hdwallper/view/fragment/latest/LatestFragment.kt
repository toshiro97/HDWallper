package com.toshiro.hdwallper.view.fragment.latest

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.toshiro.hdwallper.R
import com.toshiro.hdwallper.adapter.AdapterImageLatest
import com.toshiro.hdwallper.listener.RecyclerViewClickListener
import com.toshiro.hdwallper.model.HDWALLPAPERItem
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_latest.view.*
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.animation.OvershootInterpolator
import com.toshiro.hdwallper.view.activity.DetailImageActivity
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.fragment_latest.*


class LatestFragment : Fragment(),LatestViewInterface {
    override fun onLoadSuccess(listHDWALLPAPERItem: List<HDWALLPAPERItem>) {
        adapterImageLatest = AdapterImageLatest(this.context!!,listHDWALLPAPERItem,object : RecyclerViewClickListener{
            override fun onClick(position: Int) {
                val intent = Intent(context,DetailImageActivity::class.java)
                intent.putExtra("imageDetail",listHDWALLPAPERItem[position])
                startActivity(intent)
            }
        },fragment_home_view)
        val adapterAnim = ScaleInAnimationAdapter(adapterImageLatest)
        adapterAnim.setFirstOnly(true)
        adapterAnim.setDuration(1000)
        adapterAnim.setInterpolator(OvershootInterpolator(.9f))
        viewRoot.rv_home_latest.adapter = adapterAnim
    }

    override fun onLoadFailed(message: String) {
        viewRoot.rv_home_latest.visibility = View.INVISIBLE
        viewRoot.tv_empty_home_latest.visibility = View.VISIBLE
    }

    override fun showProgressBar() {
        viewRoot.progress_home.visibility = View.VISIBLE
    }

    override fun hiddenProgressBar() {
        viewRoot.progress_home.visibility = View.INVISIBLE
    }

    lateinit var linearLayoutManager : RecyclerView.LayoutManager
    lateinit var compositeDisposable: CompositeDisposable
    lateinit var latestPresenter: LatestPresenter
    lateinit var adapterImageLatest: AdapterImageLatest
    lateinit var viewRoot : View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewRoot = inflater.inflate(R.layout.fragment_latest,container,false)
        linearLayoutManager = GridLayoutManager(context, 2)
        viewRoot.rv_home_latest.setHasFixedSize(true)
        viewRoot.rv_home_latest.layoutManager = linearLayoutManager

        compositeDisposable = CompositeDisposable()

        latestPresenter = LatestPresenter(compositeDisposable,this, this.context!!)
        latestPresenter.loadImage()

        return viewRoot
    }


}