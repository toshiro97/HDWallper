package com.toshiro.hdwallper.model
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HDWALLPAPERItem(

	@field:SerializedName("category_image")
	val categoryImage: String? = null,

	@field:SerializedName("category_name")
	val categoryName: String? = null,

	@field:SerializedName("category_image_thumb")
	val categoryImageThumb: String? = null,

	@field:SerializedName("wallpaper_image_thumb")
	val wallpaperImageThumb: String? = null,

	@field:SerializedName("cat_id")
	val catId: String? = null,

	@field:SerializedName("wallpaper_image")
	val wallpaperImage: String? = null,

	@field:SerializedName("total_views")
	val totalViews: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("cid")
	val cid: String? = null
) : Serializable