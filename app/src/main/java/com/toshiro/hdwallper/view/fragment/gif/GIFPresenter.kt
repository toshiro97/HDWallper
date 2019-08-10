package com.toshiro.hdwallper.view.fragment.gif

import android.content.Context
import com.toshiro.hdwallper.apiHelper.IAPIWallpaper
import com.toshiro.hdwallper.apiHelper.RetrofitClient
import com.toshiro.hdwallper.until.Common
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class GIFPresenter(var compositeDisposable: CompositeDisposable, var gifViewInterface: GIFViewInterface, var context: Context) : GIFPresenterInterface {
    override fun loadGif() {
        gifViewInterface.showProgressBar()
        if (Common(context).isConnectedToInternet(context)) {
            compositeDisposable.add(RetrofitClient.getClient.create(IAPIWallpaper::class.java).getGifImageList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { responseImageList ->
                        if (responseImageList.hDWALLPAPER!!.isNotEmpty()) {
                            gifViewInterface.hiddenProgressBar()
                            gifViewInterface.onLoadSuccess(responseImageList.hDWALLPAPER)
                        } else {
                            gifViewInterface.onLoadFailed("No data found")
                            gifViewInterface.hiddenProgressBar()
                        }
                    }
            )
        }else{
            gifViewInterface.onLoadFailed("No data found")
            gifViewInterface.hiddenProgressBar()
        }

    }
}