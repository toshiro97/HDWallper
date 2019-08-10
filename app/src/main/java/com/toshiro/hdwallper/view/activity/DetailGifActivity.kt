package com.toshiro.hdwallper.view.activity

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import com.toshiro.hdwallper.R
import com.toshiro.hdwallper.model.giftImage.GIFItem
import kotlinx.android.synthetic.main.activity_detail_gif.*
import kotlinx.android.synthetic.main.activity_detail_gif.view.*

class DetailGifActivity : AppCompatActivity(),View.OnClickListener {

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
        setContentView(R.layout.activity_detail_gif)

        setSupportActionBar(toolbar)
        toolbar.imgBack.setOnClickListener { onBackPressed() }

        val gifItem = intent.extras.get("imageDetail") as GIFItem

//        Picasso.get()
//                .load(gifItem.gifImage)
//                .placeholder(R.drawable.placeholder_wall)
//                .into(imgDetail)

        Glide.with(this).asGif().load(gifItem.gifImage).into(imgDetail)

        changeStatusBarColor()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this@DetailGifActivity, R.color.statusbar)
        }

    }
}
