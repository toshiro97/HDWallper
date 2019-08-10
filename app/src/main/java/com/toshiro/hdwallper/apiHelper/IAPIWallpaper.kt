package com.toshiro.hdwallper.apiHelper

import com.toshiro.hdwallper.model.categoryList.ResponseCategoryList
import com.toshiro.hdwallper.model.giftImage.ResponseGifImage
import com.toshiro.hdwallper.model.latest.ResponseLatest
import com.toshiro.hdwallper.model.listByCategory.ResponseListByCategory
import com.toshiro.hdwallper.model.singleGifImage.ResponseSingleGif
import com.toshiro.hdwallper.model.singleWallpaper.ResponseSingleWallpaper
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface IAPIWallpaper {
    @GET("/api.php?latest")
    fun getLatest() : Observable<ResponseLatest>

    @GET("/api.php?cat_list")
    fun getCategoryList() : Observable<ResponseCategoryList>

    @GET("/api.php")
    fun getListByCategory(@Query("cat_id") categoryID : String) : Observable<ResponseListByCategory>

    @GET("/api.php")
    fun getSingleWallpaper(@Query("wallpaper_id") wallpaperID : String) : Observable<ResponseSingleWallpaper>

    @GET("/api.php?gif_list")
    fun getGifImageList() : Observable<ResponseGifImage>

    @GET("/api.php")
    fun getSingleGifImage(@Query("gif_id") gifID : String) : Observable<ResponseSingleGif>
}