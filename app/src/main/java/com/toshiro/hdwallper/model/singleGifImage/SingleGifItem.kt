package com.toshiro.hdwallper.model.singleGifImage


import com.google.gson.annotations.SerializedName

data class SingleGifItem(

	@field:SerializedName("gif_image")
	val gifImage: String? = null,

	@field:SerializedName("total_views")
	val totalViews: String? = null,

	@field:SerializedName("id")
	val id: String? = null
)