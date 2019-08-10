package com.toshiro.hdwallper.view.fragment.latest

import android.content.Context
import com.toshiro.hdwallper.apiHelper.IAPIWallpaper
import com.toshiro.hdwallper.apiHelper.RetrofitClient
import com.toshiro.hdwallper.until.Common
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LatestPresenter(var compositeDisposable : CompositeDisposable, var latestViewInterface: LatestViewInterface, var context: Context) : LatestPresenterInterface {
    override fun loadImage() {
        latestViewInterface.showProgressBar()
        if (Common(context).isConnectedToInternet(context)) {
            compositeDisposable.add(RetrofitClient.getClient.create(IAPIWallpaper::class.java).getLatest()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { responseImageList ->
                        if (responseImageList.hDWALLPAPER!!.isNotEmpty()) {
                            latestViewInterface.hiddenProgressBar()
                            latestViewInterface.onLoadSuccess(responseImageList.hDWALLPAPER)
                        } else {
                            latestViewInterface.onLoadFailed("No data found")
                            latestViewInterface.hiddenProgressBar()
                        }
                    }
            )
        }else{
            latestViewInterface.onLoadFailed("No data found")
            latestViewInterface.hiddenProgressBar()
        }
    }
}