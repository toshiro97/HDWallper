package com.toshiro.hdwallper.view.activity

import android.annotation.SuppressLint
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.toshiro.hdwallper.R
import com.toshiro.hdwallper.model.HDWALLPAPERItem
import kotlinx.android.synthetic.main.activity_detail_image.*
import kotlinx.android.synthetic.main.activity_detail_image.view.*
import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.design.widget.Snackbar
import java.io.IOException

class DetailImageActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var hdwallpaperItem: HDWALLPAPERItem

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.menu_item_set -> {
                Picasso.get()
                        .load(hdwallpaperItem.wallpaperImage)
                        .into(object : com.squareup.picasso.Target {
                            override fun onBitmapFailed(e: java.lang.Exception?, errorDrawable: Drawable?) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                            }

                            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                                val wallpaperManager = WallpaperManager.getInstance(this@DetailImageActivity)
                                try {
                                    wallpaperManager.setBitmap(bitmap)

                                    val snackbar = Snackbar.make(container_detail_image, "Đã cài làm hình nền",
                                            Snackbar.LENGTH_LONG).setAction("Action", null)
                                    snackbar.show()
                                    menu.close(true)
                                } catch (ex: IOException) {
                                    ex.printStackTrace()
                                }
                            }
                        })
            }

            R.id.menu_item_save -> {


            }
            R.id.menu_item_share -> {
            }
            R.id.menu_item_favourite -> {
            }
        }
    }


    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_image)

        setSupportActionBar(toolbar)
        toolbar.imgBack.setOnClickListener {
            onBackPressed()
        }



        hdwallpaperItem = intent.extras.get("imageDetail") as HDWALLPAPERItem

        Picasso.get()
                .load(hdwallpaperItem.wallpaperImage)
                .placeholder(R.drawable.placeholder_wall)
                .into(imgDetail)

        changeStatusBarColor()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this@DetailImageActivity, R.color.statusbar)
        }

    }
}
