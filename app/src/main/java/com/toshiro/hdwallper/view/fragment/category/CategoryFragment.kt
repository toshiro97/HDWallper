package com.toshiro.hdwallper.view.fragment.category

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import com.toshiro.hdwallper.R
import com.toshiro.hdwallper.adapter.AdapterImageCategory
import com.toshiro.hdwallper.listener.RecyclerViewClickListener
import com.toshiro.hdwallper.model.categoryList.CategoryItem
import com.toshiro.hdwallper.view.activity.listByCategory.ListByCategoryActivity
import io.reactivex.disposables.CompositeDisposable
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.fragment_categories.*
import kotlinx.android.synthetic.main.fragment_categories.view.*

class CategoryFragment : Fragment(),CategoryViewInterface {

    override fun onLoadSuccess(listCategory: List<CategoryItem>) {
        adapterImageCategory = AdapterImageCategory(this.context!!,listCategory, RecyclerViewClickListener { position ->
            val intent = Intent(context, ListByCategoryActivity::class.java)
            intent.putExtra("categoryID",listCategory[position].cid)
            startActivity(intent)
        })
        val adapterAnim = ScaleInAnimationAdapter(adapterImageCategory)
        adapterAnim.setFirstOnly(true)
        adapterAnim.setDuration(1000)
        adapterAnim.setInterpolator(OvershootInterpolator(.9f))
        viewRoot.rv_cat.adapter = adapterAnim
    }

    override fun onLoadFailed(message: String) {
        viewRoot.rv_cat.visibility = View.INVISIBLE
        viewRoot.tv_empty_cat.visibility = View.VISIBLE
    }

    override fun showProgressBar() {
        viewRoot.progress_category.visibility = View.VISIBLE
    }

    override fun hiddenProgressBar() {
        viewRoot.progress_category.visibility = View.INVISIBLE
    }

    lateinit var viewRoot : View
    lateinit var linearLayoutManager : LinearLayoutManager
    lateinit var compositeDisposable: CompositeDisposable
    lateinit var categoryPresenter: CategoryPresenter
    lateinit var adapterImageCategory: AdapterImageCategory

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewRoot = inflater.inflate(R.layout.fragment_categories,container,false)
        linearLayoutManager = LinearLayoutManager(context)

        viewRoot.rv_cat.setHasFixedSize(true)
        viewRoot.rv_cat.layoutManager = linearLayoutManager

        compositeDisposable = CompositeDisposable()

        categoryPresenter = CategoryPresenter(compositeDisposable,this, this.context!!)
        categoryPresenter.loadImage()


        return viewRoot
    }
}