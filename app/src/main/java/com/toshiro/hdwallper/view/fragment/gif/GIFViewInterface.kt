package com.toshiro.hdwallper.view.fragment.gif

import com.toshiro.hdwallper.model.giftImage.GIFItem
import com.toshiro.hdwallper.view.ViewGeneral

interface GIFViewInterface : ViewGeneral{
    fun onLoadSuccess(listGIF: List<GIFItem>)
    fun onLoadFailed(message : String)
}