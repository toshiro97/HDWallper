package com.toshiro.hdwallper.view.fragment.favourite.favouriteWall.detailSingleGif

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.toshiro.hdwallper.R
import com.toshiro.hdwallper.model.singleGifImage.SingleGifItem
import com.toshiro.hdwallper.view.fragment.favourite.favouriteGIF.detailWallpaper.DetailSingleGifPresenter
import com.toshiro.hdwallper.view.fragment.favourite.favouriteGIF.detailWallpaper.DetailSingleGifViewInterface
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_detail_single_gif.*
import kotlinx.android.synthetic.main.activity_detail_single_gif.view.*


class DetailSingleGifActivity : AppCompatActivity(), View.OnClickListener,DetailSingleGifViewInterface {
    override fun onLoadSuccess(singleImage: SingleGifItem) {
        Picasso.get()
                .load(singleImage.gifImage)
                .placeholder(R.drawable.placeholder_wall)
                .into(imgDetailGif)
    }

    override fun onLoadFailed(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgressBar() {
        pg_detail_gif.visibility = View.VISIBLE
    }

    override fun hiddenProgressBar() {
        pg_detail_gif.visibility = View.INVISIBLE
    }


    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.menu_item_set -> {
                Toast.makeText(this,"abnscsjdfasdf", Toast.LENGTH_SHORT).show()
                menuGif.close(true)
            }
            R.id.menu_item_save -> {/* you can omit the braces if there is only a single expression */}
            R.id.menu_item_share -> {}
            R.id.menu_item_favourite -> {}
        }
    }

    lateinit var compositeDisposable: CompositeDisposable
    lateinit var detailSingleGifPresenter: DetailSingleGifPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_single_gif)

        setSupportActionBar(toolbarGif)
        toolbarGif.imgBackGIf.setOnClickListener { onBackPressed() }

        val idGif = intent.extras.get("idGif")

        compositeDisposable = CompositeDisposable()

        detailSingleGifPresenter = DetailSingleGifPresenter(compositeDisposable,this, this)

        detailSingleGifPresenter.loadImageDetail(idGif as String)

    }
}
