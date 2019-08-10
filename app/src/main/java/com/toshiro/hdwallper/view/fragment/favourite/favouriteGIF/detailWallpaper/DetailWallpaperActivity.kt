package com.toshiro.hdwallper.view.fragment.favourite.favouriteGIF.detailWallpaper

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.toshiro.hdwallper.R
import com.toshiro.hdwallper.model.singleWallpaper.SingleWALLPAPERItem
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_detail_wallpaper.*
import kotlinx.android.synthetic.main.activity_detail_wallpaper.view.*

class DetailWallpaperActivity : AppCompatActivity() ,View.OnClickListener, DetailWallpaperViewInterface {
    override fun onLoadSuccess(singleImage: SingleWALLPAPERItem) {
        Picasso.get()
                .load(singleImage.wallpaperImage)
                .placeholder(R.drawable.placeholder_wall)
                .into(imgDetail)
    }

    override fun onLoadFailed(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showProgressBar() {
        pg_detail_wallpaper.visibility = View.VISIBLE
    }

    override fun hiddenProgressBar() {
        pg_detail_wallpaper.visibility = View.INVISIBLE
    }


    lateinit var compositeDisposable: CompositeDisposable
    lateinit var detailWallpaperPresenter: DetailWallpaperPresenter


    override fun onClick(v: View?) {
        when(v!!.id) {
            R.id.menu_item_set -> {
                Toast.makeText(this,"abnscsjdfasdf", Toast.LENGTH_SHORT).show()
                menu.close(true)
            }
            R.id.menu_item_save -> {/* you can omit the braces if there is only a single expression */}
            R.id.menu_item_share -> {}
            R.id.menu_item_favourite -> {}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_wallpaper)

        setSupportActionBar(toolbar)
        toolbar.imgBack.setOnClickListener {
            onBackPressed()
        }

        compositeDisposable = CompositeDisposable()

        detailWallpaperPresenter = DetailWallpaperPresenter(compositeDisposable,this, this)

        val idImage = intent.extras.get("idImage")

        detailWallpaperPresenter.loadImageDetail(idImage as String)


    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}
