package com.toshiro.hdwallper.view.activity.listByCategory

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import com.toshiro.hdwallper.R
import com.toshiro.hdwallper.adapter.AdapterImageLatest
import com.toshiro.hdwallper.listener.RecyclerViewClickListener
import com.toshiro.hdwallper.model.HDWALLPAPERItem
import com.toshiro.hdwallper.view.activity.DetailImageActivity
import io.reactivex.disposables.CompositeDisposable
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.activity_list_by_category.*
import kotlinx.android.synthetic.main.activity_list_by_category.view.*


class ListByCategoryActivity : AppCompatActivity(),ListByCategoryViewInterface {
    override fun onLoadSuccess(listHDWALLPAPERItem: List<HDWALLPAPERItem>) {
        adapterImageLatest = AdapterImageLatest(this,listHDWALLPAPERItem,object : RecyclerViewClickListener {
            override fun onClick(position: Int) {
                val intent = Intent(this@ListByCategoryActivity,DetailImageActivity::class.java)
                intent.putExtra("imageDetail",listHDWALLPAPERItem[position])
                startActivity(intent)
            }
        },containerList)
        val adapterAnim = ScaleInAnimationAdapter(adapterImageLatest)
        adapterAnim.setFirstOnly(true)
        adapterAnim.setDuration(1000)
        adapterAnim.setInterpolator(OvershootInterpolator(.9f))
        rv_list_by_category.adapter = adapterAnim
    }

    override fun onLoadFailed(message: String) {
        Toast.makeText(this,"Load Failed", Toast.LENGTH_SHORT)
    }

    override fun showProgressBar() {
        progress_list_by_category.visibility = View.VISIBLE
    }

    override fun hiddenProgressBar() {
        progress_list_by_category.visibility = View.INVISIBLE
    }

    lateinit var linearLayoutManager : RecyclerView.LayoutManager
    lateinit var compositeDisposable: CompositeDisposable
    lateinit var listByCategoryPresenter: ListByCategoryPresenter
    lateinit var adapterImageLatest: AdapterImageLatest

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_by_category)

        setSupportActionBar(toolbar)
        toolbar.imgBack.setOnClickListener { onBackPressed() }

        val categoryID = intent.extras.get("categoryID").toString()

        linearLayoutManager = GridLayoutManager(this, 2)
        rv_list_by_category.setHasFixedSize(true)
        rv_list_by_category.layoutManager = linearLayoutManager

        compositeDisposable = CompositeDisposable()

        listByCategoryPresenter = ListByCategoryPresenter(compositeDisposable,this, this)
        listByCategoryPresenter.loadImage(categoryID)
    }
}
