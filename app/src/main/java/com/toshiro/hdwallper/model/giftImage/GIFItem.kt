package com.toshiro.hdwallper.model.giftImage

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GIFItem(

	@field:SerializedName("gif_image")
	val gifImage: String? = null,

	@field:SerializedName("total_views")
	val totalViews: String? = null,

	@field:SerializedName("id")
	val id: String? = null
) : Serializable