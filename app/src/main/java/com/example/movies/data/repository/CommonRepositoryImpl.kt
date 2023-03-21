package com.example.movies.data.repository

import com.example.movies.data.Cast
import com.example.movies.data.MovieDto
import com.example.movies.data.sources.local.LocalDataSource
import com.example.movies.data.sources.remote.CloudDataSource
import com.example.movies.domain.Movie
import com.example.movies.domain.repository.CommonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommonRepositoryImpl @Inject constructor(
    private val cloudDataSource: CloudDataSource,
    private val localDataSource: LocalDataSource
) : CommonRepository {
    override suspend fun fetchPopularMovies(): List<Movie> {
        return try {
            cloudDataSource.fetchPopularMovies().movies.map {
                it.map()
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchNowPlayingMovies(): List<Movie> {
        return try {
            cloudDataSource.fetchNowPlayingMovies().movies.map {
                it.map()
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override fun fetchFavoritesMovies(): Flow<List<Movie>> {
        return try {
            localDataSource.fetchFavoriteMovies().map { movies ->
                movies.map {
                    it.map()
                }
            }
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun addMovieToFavorite(movie: Movie) {
        localDataSource.addMovie(
            MovieDto(
                movie.adult,
                movie.id,
                movie.overview,
                movie.posterPath,
                movie.releaseDate,
                movie.title,
                movie.voteAverage
            )
        )
    }

    override suspend fun deleteMovieFromFavorite(movie: Movie) {
        localDataSource.deleteMovie(
            MovieDto(
                movie.adult,
                movie.id,
                movie.overview,
                movie.posterPath,
                movie.releaseDate,
                movie.title,
                movie.voteAverage
            )
        )
    }

    override suspend fun fetchMovieTrailerById(id: Int): String {
        return try {
            cloudDataSource.fetchMovieTrailerById(id).results[1].key
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchActorsCast(id: Int): List<Cast> {
        return try {
            cloudDataSource.fetchActorsCast(id).cast
        } catch (e: Exception) {
            throw e
        }
    }
}