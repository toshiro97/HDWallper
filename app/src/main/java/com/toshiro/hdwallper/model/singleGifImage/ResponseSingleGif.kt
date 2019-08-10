package com.toshiro.hdwallper.model.singleGifImage

import com.google.gson.annotations.SerializedName

data class ResponseSingleGif(

	@field:SerializedName("HD_WALLPAPER")
	val hDWALLPAPER: List<SingleGifItem>? = null
)