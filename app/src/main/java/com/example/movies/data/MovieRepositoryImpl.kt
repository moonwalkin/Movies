package com.example.movies.data

import com.example.movies.domain.Movie
import com.example.movies.domain.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val cloudDataSource: CloudDataSource,
    private val localDataSource: LocalDataSource
) :
    MovieRepository {
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
        return localDataSource.fetchFavoriteMovies().map { movies ->
            movies.map {
                it.map()
            }
        }
    }

    override suspend fun addMovieToFavorite(movie: Movie) {
        localDataSource.addMovie(
            MovieDto(
                movie.adult,
                movie.backdrop_path,
                movie.id,
                movie.original_language,
                movie.original_title,
                movie.overview,
                movie.popularity,
                movie.poster_path,
                movie.release_date,
                movie.title,
                movie.video,
                movie.vote_average,
                movie.vote_count
            )
        )
    }

    override suspend fun deleteMovieFromFavorite(movie: Movie) {
        localDataSource.deleteMovie(MovieDto(
            movie.adult,
            movie.backdrop_path,
            movie.id,
            movie.original_language,
            movie.original_title,
            movie.overview,
            movie.popularity,
            movie.poster_path,
            movie.release_date,
            movie.title,
            movie.video,
            movie.vote_average,
            movie.vote_count
        ))
    }
}