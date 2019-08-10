package com.toshiro.hdwallper.view.activity.listByCategory

import com.toshiro.hdwallper.model.HDWALLPAPERItem
import com.toshiro.hdwallper.view.ViewGeneral

interface ListByCategoryViewInterface : ViewGeneral {
    fun onLoadSuccess(listHDWALLPAPERItem: List<HDWALLPAPERItem>)
    fun onLoadFailed(message : String)
}