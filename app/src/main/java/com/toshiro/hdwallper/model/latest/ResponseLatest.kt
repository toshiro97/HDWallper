package com.toshiro.hdwallper.model.latest

import com.google.gson.annotations.SerializedName
import com.toshiro.hdwallper.model.HDWALLPAPERItem

data class ResponseLatest(

	@field:SerializedName("HD_WALLPAPER")
	val hDWALLPAPER: List<HDWALLPAPERItem>? = null
)