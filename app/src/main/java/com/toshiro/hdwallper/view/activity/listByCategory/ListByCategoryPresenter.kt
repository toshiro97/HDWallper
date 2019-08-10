package com.toshiro.hdwallper.view.activity.listByCategory

import android.content.Context
import com.toshiro.hdwallper.apiHelper.IAPIWallpaper
import com.toshiro.hdwallper.apiHelper.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ListByCategoryPresenter(var compositeDisposable : CompositeDisposable
                              , var listByCategoryViewInterface: ListByCategoryViewInterface, var context: Context) : ListByCategoryPresenterInterface {
    override fun loadImage(categoryID: String) {
        listByCategoryViewInterface
        compositeDisposable.add(RetrofitClient.getClient.create(IAPIWallpaper::class.java).getListByCategory(categoryID)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { responseImageList ->
                    if (responseImageList.hDWALLPAPER!!.isNotEmpty()) {
                        listByCategoryViewInterface.hiddenProgressBar()
                        listByCategoryViewInterface.onLoadSuccess(responseImageList.hDWALLPAPER)
                    } else {
                        listByCategoryViewInterface.onLoadFailed("No data found")
                        listByCategoryViewInterface.hiddenProgressBar()
                    }
                }
        )
    }
}