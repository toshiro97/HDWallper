package com.toshiro.hdwallper.model.singleWallpaper

import com.google.gson.annotations.SerializedName

data class SingleWALLPAPERItem(

	@field:SerializedName("wallpaper_image_thumb")
	val wallpaperImageThumb: String? = null,

	@field:SerializedName("cat_id")
	val catId: String? = null,

	@field:SerializedName("wallpaper_image")
	val wallpaperImage: String? = null,

	@field:SerializedName("total_views")
	val totalViews: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)