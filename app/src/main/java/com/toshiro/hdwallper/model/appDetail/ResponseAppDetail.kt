package com.toshiro.hdwallper.model.appDetail

import com.google.gson.annotations.SerializedName

data class ResponseAppDetail(

	@field:SerializedName("HD_WALLPAPER")
	val hDWALLPAPER: List<AboutItem?>? = null
)