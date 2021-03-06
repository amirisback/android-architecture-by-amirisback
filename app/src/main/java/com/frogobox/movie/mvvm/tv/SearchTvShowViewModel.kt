package com.frogobox.movie.mvvm.tv

import android.app.Application
import com.frogobox.base.BaseViewModel
import com.frogobox.base.source.model.TvShow
import com.frogobox.base.source.DataSource
import com.frogobox.base.source.Repository
import com.frogobox.base.util.SingleLiveEvent

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * movie
 * Copyright (C) 16/11/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.movie.viewmodel
 *
 */
class SearchTvShowViewModel (private val context: Application, private val repository: Repository) :
    BaseViewModel(context) {

    var tvShowListLive = SingleLiveEvent<List<TvShow>>()

    fun searchTvShow(query: String){
        repository.searchTvShow(query, object : DataSource.GetRemoteCallback<List<TvShow>>{
            override fun onEmpty() {

            }

            override fun onShowProgressDialog() {
                eventShowProgress.postValue(true)
            }

            override fun onHideProgressDialog() {
                eventShowProgress.postValue(false)
            }

            override fun onSuccess(data: List<TvShow>) {
                tvShowListLive.postValue(data)
            }

            override fun onFinish() {

            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {

            }
        })

    }

}