package com.filmfiesta.themovieshow.data.source

import android.content.Context
import com.filmfiesta.themovieshow.data.local.dao.TheMovieShowDao
import com.filmfiesta.themovieshow.data.local.entity.movie.FavoriteMovieEntity
import com.filmfiesta.themovieshow.data.local.entity.tvshow.FavoriteTvShowEntity
import com.filmfiesta.themovieshow.model.favorite.SortFiltering
import com.filmfiesta.themovieshow.model.response.about.About
import com.filmfiesta.themovieshow.utils.UtilsData
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val favoriteDao: TheMovieShowDao
) {
    suspend fun addMovieToFavorite(favoriteMovieEntity: FavoriteMovieEntity) =
        favoriteDao.addMovieToFavorite(favoriteMovieEntity)

    fun getAllFavoriteMovies() = favoriteDao.getAllFavoriteMovies()

    fun getFavoriteMoviesByTitle() = favoriteDao.getFavoriteMoviesByTitle()

    fun getFavoriteMoviesByRelease() = favoriteDao.getFavoriteMoviesByRelease()

    fun getFavoriteMoviesByRating() = favoriteDao.getFavoriteMoviesByRating()

    suspend fun checkFavoriteMovie(movieId: Int) = favoriteDao.checkMovieFavorite(movieId)

    suspend fun removeMovieFromFavorite(movieId: Int) = favoriteDao.removeMovieFromFavorite(movieId)

    suspend fun addTvShowToFavorite(favoriteTvShowEntity: FavoriteTvShowEntity) =
        favoriteDao.addTvShowToFavorite(favoriteTvShowEntity)

    fun getAllFavoriteTvShows() = favoriteDao.getAllFavoriteTvShows()

    fun getFavoriteTvShowsByTitle() = favoriteDao.getFavoriteTvShowsByTitle()

    fun getFavoriteTvShowsByRelease() = favoriteDao.getFavoriteTvShowsByRelease()

    fun getFavoriteTvShowsByRating() = favoriteDao.getFavoriteTvShowsByRating()

    suspend fun checkFavoriteTvShow(tvShowId: Int) = favoriteDao.checkTvShowFavorite(tvShowId)

    suspend fun removeTvShowFromFavorite(tvShowId: Int) = favoriteDao.removeTvShowFromFavorite(tvShowId)

    fun getAboutAuthor(context: Context): List<About> = UtilsData.generateAboutAuthorData(context)

    fun getAboutApplication(context: Context): List<About> = UtilsData.generateAboutApplicationData(context)

    fun getSortFiltering(context: Context): List<SortFiltering> = UtilsData.generateSortFilteringData(context)
}