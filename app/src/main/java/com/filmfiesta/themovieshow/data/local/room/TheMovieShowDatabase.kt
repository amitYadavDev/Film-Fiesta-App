package com.filmfiesta.themovieshow.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.filmfiesta.themovieshow.data.local.dao.TheMovieShowDao
import com.filmfiesta.themovieshow.data.local.entity.movie.FavoriteMovieEntity
import com.filmfiesta.themovieshow.data.local.entity.tvshow.FavoriteTvShowEntity
import com.filmfiesta.themovieshow.utils.CommonConstants

@Database(
    entities = [FavoriteMovieEntity::class, FavoriteTvShowEntity::class],
    version = CommonConstants.DB_VERSION,
    exportSchema = false
)
abstract class TheMovieShowDatabase : RoomDatabase() {
    abstract fun getFavoriteTheMovieShowDao(): TheMovieShowDao
}