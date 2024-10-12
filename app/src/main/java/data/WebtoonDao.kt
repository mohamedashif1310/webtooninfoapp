package com.example.webtooninfo.data

import androidx.room.*

@Dao
interface WebtoonDao {
    @Query("SELECT * FROM webtoons")
    fun getAllWebtoons(): List<Webtoon>

    @Query("SELECT * FROM webtoons WHERE isFavorite = 1")
    fun getFavoriteWebtoons(): List<Webtoon>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWebtoon(webtoon: Webtoon)

    @Update
    fun updateWebtoon(webtoon: Webtoon)
}