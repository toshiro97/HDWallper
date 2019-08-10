package com.toshiro.hdwallper.model.giftImage


import com.google.gson.annotations.SerializedName

data class ResponseGifImage(

	@field:SerializedName("HD_WALLPAPER")
	val hDWALLPAPER: List<GIFItem>? = null
)