package com.toshiro.hdwallper.view.fragment.category

import android.content.Context
import com.toshiro.hdwallper.apiHelper.IAPIWallpaper
import com.toshiro.hdwallper.apiHelper.RetrofitClient
import com.toshiro.hdwallper.until.Common
import com.toshiro.hdwallper.view.fragment.latest.LatestPresenterInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CategoryPresenter(var compositeDisposable: CompositeDisposable, var categoryViewInterface: CategoryViewInterface, var context: Context) : LatestPresenterInterface {
    override fun loadImage() {
        categoryViewInterface.showProgressBar()
        if (Common(context).isConnectedToInternet(context)) {
            compositeDisposable.add(RetrofitClient.getClient.create(IAPIWallpaper::class.java).getCategoryList()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { responseImageList ->
                        if (responseImageList.hDWALLPAPER!!.isNotEmpty()) {
                            categoryViewInterface.hiddenProgressBar()
                            categoryViewInterface.onLoadSuccess(responseImageList.hDWALLPAPER)
                        } else {
                            categoryViewInterface.onLoadFailed("No data found")
                            categoryViewInterface.hiddenProgressBar()
                        }
                    }
            )
        }
        else{
            categoryViewInterface.onLoadFailed("No data found")
            categoryViewInterface.hiddenProgressBar()
        }
    }

}