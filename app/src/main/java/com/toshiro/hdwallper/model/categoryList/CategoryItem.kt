package com.toshiro.hdwallper.model.categoryList

import com.google.gson.annotations.SerializedName

data class CategoryItem(

	@field:SerializedName("total_wallpaper")
	val totalWallpaper: String? = null,

	@field:SerializedName("category_image")
	val categoryImage: String? = null,

	@field:SerializedName("category_name")
	val categoryName: String? = null,

	@field:SerializedName("category_image_thumb")
	val categoryImageThumb: String? = null,

	@field:SerializedName("cid")
	val cid: String? = null


)