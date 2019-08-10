package com.toshiro.hdwallper.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import com.toshiro.hdwallper.R
import com.toshiro.hdwallper.listener.RecyclerViewClickListener
import com.toshiro.hdwallper.model.categoryList.CategoryItem

class AdapterImageCategory(private val context: Context, var arrayList: List<CategoryItem>, private val recyclerViewClickListener: RecyclerViewClickListener) : RecyclerView.Adapter<ImageCategoryViewHoler>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageCategoryViewHoler {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_image_category, parent, false)
        return ImageCategoryViewHoler(itemView)
    }

    override fun onBindViewHolder(holder: ImageCategoryViewHoler, position: Int) {

        holder.tvTotalImage.text = arrayList[position].totalWallpaper
        holder.tvCategoryName.text = arrayList[position].categoryName
        Picasso.get()
                .load(arrayList[position].categoryImageThumb)
                .placeholder(R.drawable.placeholder_wall)
                .into(holder.imageView)


        holder.imageView.setOnClickListener { recyclerViewClickListener.onClick(holder.adapterPosition) }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}

class ImageCategoryViewHoler (view: View) : RecyclerView.ViewHolder(view) {

    val imageView: ImageView = view.findViewById(R.id.imgCategory)
    val tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)
    val tvTotalImage: TextView = view.findViewById(R.id.tvTotalImage)

}