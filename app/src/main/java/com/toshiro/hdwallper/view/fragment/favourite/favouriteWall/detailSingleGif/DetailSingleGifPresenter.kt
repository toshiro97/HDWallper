package com.toshiro.hdwallper.view.fragment.favourite.favouriteGIF.detailWallpaper

import android.content.Context
import com.toshiro.hdwallper.apiHelper.IAPIWallpaper
import com.toshiro.hdwallper.apiHelper.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailSingleGifPresenter(var compositeDisposable : CompositeDisposable
                               , var detailSingleGifViewInterface: DetailSingleGifViewInterface
                               , var context: Context) : DetailSingleGifPresenterInterface {
    override fun loadImageDetail(id : String) {
        detailSingleGifViewInterface.showProgressBar()
        compositeDisposable.add(RetrofitClient.getClient.create(IAPIWallpaper::class.java).getSingleGifImage(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { responseSingleWallpaper ->
                    if (responseSingleWallpaper.hDWALLPAPER!!.isNotEmpty()) {
                        detailSingleGifViewInterface.hiddenProgressBar()
                        detailSingleGifViewInterface.onLoadSuccess(responseSingleWallpaper.hDWALLPAPER[0])
                    } else {
                        detailSingleGifViewInterface.onLoadFailed("No data found")
                        detailSingleGifViewInterface.hiddenProgressBar()
                    }
                }
        )
    }
}