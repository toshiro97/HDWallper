package com.toshiro.hdwallper.view.fragment.category

import com.toshiro.hdwallper.model.categoryList.CategoryItem
import com.toshiro.hdwallper.view.ViewGeneral

interface CategoryViewInterface : ViewGeneral {
    fun onLoadSuccess(listCategory: List<CategoryItem>)
    fun onLoadFailed(message : String)
}