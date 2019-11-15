package com.frogobox.mvvm.viewmodel

import android.app.Application
import com.frogobox.base.BaseViewModel
import com.frogobox.base.modular.model.FavoriteMovie
import com.frogobox.base.modular.source.DataSource
import com.frogobox.base.modular.source.Repository
import com.frogobox.base.util.SingleLiveEvent

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * mvvm
 * Copyright (C) 16/11/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * com.frogobox.mvvm.viewmodel
 *
 */
class FavoriteMovieViewModel(private val context: Application, private val repository: Repository) :
    BaseViewModel(context) {

    var favMovieListLive = SingleLiveEvent<List<FavoriteMovie>>()

    fun getFavoriteMovie() {
        repository.getFavoriteMovies(object :
            DataSource.GetLocalCallBack<List<FavoriteMovie>> {
            override fun onShowProgressDialog() {
                eventShowProgress.postValue(true)
            }

            override fun onHideProgressDialog() {
                eventShowProgress.postValue(false)
            }

            override fun onSuccess(data: List<FavoriteMovie>) {
                eventIsEmpty.postValue(false)
                favMovieListLive.postValue(data)
            }

            override fun onFinish() {

            }

            override fun onEmpty() {
                eventIsEmpty.postValue(true)
            }

            override fun onFailed(statusCode: Int, errorMessage: String?) {

            }
        })
    }

}