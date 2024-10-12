package com.example.webtooninfo.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.webtooninfo.data.Webtoon
import com.example.webtooninfo.data.WebtoonDatabase
import com.example.webtooninfo.data.WebtoonRepository
import kotlinx.coroutines.launch

class WebtoonViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: WebtoonRepository
    val allWebtoons: LiveData<List<Webtoon>>
    val favoriteWebtoons: LiveData<List<Webtoon>>

    init {
        val webtoonDao = WebtoonDatabase.getDatabase(application).webtoonDao()
        repository = WebtoonRepository(webtoonDao)
        allWebtoons = repository.allWebtoons
        favoriteWebtoons = repository.favoriteWebtoons

        viewModelScope.launch {
            repository.populateInitialData()
        }
    }

    fun toggleFavorite(webtoon: Webtoon) = viewModelScope.launch {
        repository.toggleFavorite(webtoon)
    }

    fun updateRating(webtoon: Webtoon, rating: Float) = viewModelScope.launch {
        repository.updateRating(webtoon, rating)
    }
}