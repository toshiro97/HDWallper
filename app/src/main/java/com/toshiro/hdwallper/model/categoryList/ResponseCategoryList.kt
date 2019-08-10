package com.toshiro.hdwallper.model.categoryList

import com.google.gson.annotations.SerializedName

data class ResponseCategoryList(

	@field:SerializedName("HD_WALLPAPER")
	val hDWALLPAPER: List<CategoryItem>? = null
)