package com.example.movies.data.repository

import com.example.movies.data.mapper.MovieMapper
import com.example.movies.data.sources.local.LocalDataSource
import com.example.movies.data.sources.remote.CloudDataSource
import com.example.movies.domain.State
import com.example.movies.domain.entity.Cast
import com.example.movies.domain.entity.Movie
import com.example.movies.domain.repository.CommonRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CommonRepositoryImpl @Inject constructor(
    private val cloudDataSource: CloudDataSource,
    private val localDataSource: LocalDataSource,
    private val mapper: MovieMapper
) : CommonRepository {

    private val listOfPopularMovies = mutableListOf<Movie>()
    var page = 1

    override suspend fun fetchPopularMovies(): State<List<Movie>> {
        return try {
            if (page < 100) {
                listOfPopularMovies.addAll(cloudDataSource.fetchPopularMovies(page).movies.map {
                    mapper.reverseMap(it)
                })
                page++
            }
            State.Success(listOfPopularMovies)
        } catch (e: Exception) {
            State.Error("Something went wrong...")
        }
    }

    override suspend fun fetchNowPlayingMovies(): State<List<Movie>> {
        return try {
            State.Success(
                cloudDataSource.fetchNowPlayingMovies()
                    .movies.map {
                        mapper.reverseMap(it)
                    })
        } catch (e: Exception) {
            State.Error("Something went wrong.")
        }
    }

    override fun fetchFavoritesMovies(): Flow<State<List<Movie>>> {
        return try {
            localDataSource.fetchFavoriteMovies().map { movies ->
                State.Success(
                    movies.map {
                        mapper.reverseMap(it)
                    })
            }
        } catch (e: Exception) {
            flow {
                emit(State.Error("Something went wrong."))
            }
        }
    }

    override suspend fun addMovieToFavorite(movie: Movie) {
        localDataSource.addMovie(
            mapper.map(movie)
        )
    }

    override suspend fun deleteMovieFromFavorite(movie: Movie) {
        localDataSource.deleteMovie(
            mapper.map(movie)
        )
    }

    override suspend fun fetchMovieTrailerById(id: Int): State<String> {
        return try {
            State.Success(
                cloudDataSource.fetchMovieTrailerById(id).results[1].key
            )
        } catch (e: Exception) {
            State.Error("Something went wrong.")
        }
    }

    override suspend fun fetchActorsCast(id: Int): State<List<Cast>> {
        return try {
            State.Success(cloudDataSource.fetchActorsCast(id).cast.map {
                it.map()
            })
        } catch (e: Exception) {
            State.Error("Something went wrong.")
        }
    }
}