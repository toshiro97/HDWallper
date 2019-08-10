package com.toshiro.hdwallper.view.fragment.favourite.favouriteGIF.detailWallpaper

import com.toshiro.hdwallper.model.HDWALLPAPERItem
import com.toshiro.hdwallper.model.singleGifImage.SingleGifItem
import com.toshiro.hdwallper.model.singleWallpaper.SingleWALLPAPERItem
import com.toshiro.hdwallper.view.ViewGeneral

interface DetailSingleGifViewInterface : ViewGeneral {
    fun onLoadSuccess(singleImage: SingleGifItem)
    fun onLoadFailed(message : String)
}