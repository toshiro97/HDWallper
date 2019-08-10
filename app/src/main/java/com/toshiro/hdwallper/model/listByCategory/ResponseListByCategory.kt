package com.toshiro.hdwallper.model.listByCategory

import com.google.gson.annotations.SerializedName
import com.toshiro.hdwallper.model.HDWALLPAPERItem

data class ResponseListByCategory(

	@field:SerializedName("HD_WALLPAPER")
	val hDWALLPAPER: List<HDWALLPAPERItem>? = null
)