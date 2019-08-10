package com.toshiro.hdwallper.view.fragment.favourite.favouriteGIF.detailWallpaper

import android.content.Context
import com.toshiro.hdwallper.apiHelper.IAPIWallpaper
import com.toshiro.hdwallper.apiHelper.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailWallpaperPresenter(var compositeDisposable : CompositeDisposable
                               , var detailWallpaperViewInterface: DetailWallpaperViewInterface
                               , var context: Context) : DetailSingleGifPresenterInterface {
    override fun loadImageDetail(id : String) {
        detailWallpaperViewInterface.showProgressBar()
        compositeDisposable.add(RetrofitClient.getClient.create(IAPIWallpaper::class.java).getSingleWallpaper(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { responseSingleWallpaper ->
                    if (responseSingleWallpaper.hDWALLPAPER!!.isNotEmpty()) {
                        detailWallpaperViewInterface.hiddenProgressBar()
                        detailWallpaperViewInterface.onLoadSuccess(responseSingleWallpaper.hDWALLPAPER[0])
                    } else {
                        detailWallpaperViewInterface.onLoadFailed("No data found")
                        detailWallpaperViewInterface.hiddenProgressBar()
                    }
                }
        )
    }
}