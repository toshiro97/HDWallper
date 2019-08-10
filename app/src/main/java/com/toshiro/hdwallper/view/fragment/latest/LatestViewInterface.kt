package com.toshiro.hdwallper.view.fragment.latest

import com.toshiro.hdwallper.model.HDWALLPAPERItem
import com.toshiro.hdwallper.view.ViewGeneral

interface LatestViewInterface : ViewGeneral {
    fun onLoadSuccess(listHDWALLPAPERItem: List<HDWALLPAPERItem>)
    fun onLoadFailed(message : String)
}