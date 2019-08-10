package com.toshiro.hdwallper.view.fragment.favourite.favouriteGIF.detailWallpaper

import com.toshiro.hdwallper.model.HDWALLPAPERItem
import com.toshiro.hdwallper.model.singleWallpaper.SingleWALLPAPERItem
import com.toshiro.hdwallper.view.ViewGeneral

interface DetailWallpaperViewInterface : ViewGeneral {
    fun onLoadSuccess(singleImage: SingleWALLPAPERItem)
    fun onLoadFailed(message : String)
}