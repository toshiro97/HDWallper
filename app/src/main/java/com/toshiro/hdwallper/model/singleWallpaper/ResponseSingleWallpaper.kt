package com.toshiro.hdwallper.model.singleWallpaper

import com.google.gson.annotations.SerializedName

data class ResponseSingleWallpaper(

	@field:SerializedName("HD_WALLPAPER")
	val hDWALLPAPER: List<SingleWALLPAPERItem>? = null
)