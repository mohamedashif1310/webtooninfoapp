package com.example.webtooninfo.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WebtoonRepository(private val webtoonDao: WebtoonDao) {

    private val _allWebtoons = MutableLiveData<List<Webtoon>>()
    val allWebtoons: LiveData<List<Webtoon>> = _allWebtoons

    private val _favoriteWebtoons = MutableLiveData<List<Webtoon>>()
    val favoriteWebtoons: LiveData<List<Webtoon>> = _favoriteWebtoons

    suspend fun refreshWebtoons() {
        withContext(Dispatchers.IO) {
            _allWebtoons.postValue(webtoonDao.getAllWebtoons())
            _favoriteWebtoons.postValue(webtoonDao.getFavoriteWebtoons())
        }
    }

    suspend fun toggleFavorite(webtoon: Webtoon) {
        withContext(Dispatchers.IO) {
            webtoon.isFavorite = !webtoon.isFavorite
            webtoonDao.updateWebtoon(webtoon)
            refreshWebtoons()
        }
    }

    suspend fun updateRating(webtoon: Webtoon, rating: Float) {
        withContext(Dispatchers.IO) {
            webtoon.rating = rating
            webtoonDao.updateWebtoon(webtoon)
            refreshWebtoons()
        }
    }

    suspend fun populateInitialData() {
        withContext(Dispatchers.IO) {
            if (webtoonDao.getAllWebtoons().isEmpty()) {
                val initialWebtoons = listOf(
                    Webtoon(1, "Lore Olympus", "A modern retelling of the Persephone and Hades myth", "https://example.com/lore_olympus.jpg"),
                    Webtoon(2, "Tower of God", "A boy enters a mysterious tower to chase after his friend", "https://example.com/tower_of_god.jpg"),
                    // Add more initial webtoons here
                )
                initialWebtoons.forEach { webtoonDao.insertWebtoon(it) }
            }
            refreshWebtoons()
        }
    }
}