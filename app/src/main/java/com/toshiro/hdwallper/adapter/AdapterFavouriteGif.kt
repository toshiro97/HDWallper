package com.toshiro.hdwallper.adapter

import android.content.Context
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.like.LikeButton
import com.like.OnLikeListener
import com.makeramen.roundedimageview.RoundedImageView
import com.squareup.picasso.Picasso
import com.toshiro.hdwallper.R
import com.toshiro.hdwallper.database.Database
import com.toshiro.hdwallper.listener.RecyclerViewClickListener
import com.toshiro.hdwallper.model.giftImage.GIFItem

class AdapterFavouriteGif(private val context: Context, var arrayList: MutableList<GIFItem>, private val recyclerViewClickListener: RecyclerViewClickListener, private val view : View) : RecyclerView.Adapter<GifFavouriteHoler>() {

    private val database = Database(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifFavouriteHoler {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_gif, parent, false)
        return GifFavouriteHoler(itemView)
    }

    override fun onBindViewHolder(holder: GifFavouriteHoler, position: Int) {

        holder.tvLike.text = arrayList[position].totalViews
        Picasso.get()
                .load(arrayList[position].gifImage)
                .placeholder(R.drawable.placeholder_wall)
                .into(holder.imageView)

        holder.likeButton.setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton) {

                database.addToGif(arrayList[position])
                holder.likeButton.isLiked = true
                val snackbar = Snackbar.make(view, "Đã thêm vào danh mục ưa thích",
                        Snackbar.LENGTH_LONG).setAction("Action", null)
                snackbar.show()
            }

            override fun unLiked(likeButton: LikeButton) {

                database.removeFromGif(arrayList[position].id!!)
                holder.likeButton.isLiked = false
                val snackbar = Snackbar.make(view, "Đã xóa khỏi mục ưu thích",
                        Snackbar.LENGTH_LONG).setAction("Action", null)
                arrayList.removeAt(position)
                notifyDataSetChanged()
                snackbar.show()
            }
        })

        holder.likeButton.isLiked = database.isFavoriteGif(arrayList[position].id!!)
        holder.imageView.setOnClickListener { recyclerViewClickListener.onClick(holder.adapterPosition) }

    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}
class GifFavouriteHoler (view: View) : RecyclerView.ViewHolder(view) {

    val imageView: RoundedImageView = view.findViewById(R.id.iv_gif)
    val likeButton: LikeButton = view.findViewById(R.id.button_gif_fav)
    val tvLike: TextView = view.findViewById(R.id.tv_view_gif)

}