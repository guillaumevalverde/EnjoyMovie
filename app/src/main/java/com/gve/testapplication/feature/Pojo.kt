package com.gve.testapplication.feature

/**
 * Created by gve on 14/12/2017.
 */

data class Movie(val id: Long,
                 val name: String,
                 val vote: Float,
                 val url: String)

data class MovieDetail(val adult: Boolean,
                       val backdrop_path: String,
                       val budget: Int,
                       val homepage: String?,
                       val original_language: String,
                       val original_title: String,
                       val overview: String?,
                       val popularity: Float,
                       val poster_path:String?,
                       val release_date: String,
                       val revenue: Int,
                       val id: Long,
                       val vote_count: Long,
                       val video: Boolean,
                       val vote_average: Float)

data class MovieRaw(val poster_path: String,
                    val adult: Boolean,
                    val overview: String,
                    val release_date: String,
                    val genre_ids: List<Long>,
                    val id: Long,
                    val original_title: String,
                    val backdrop_path: String,
                    val popularity: Float,
                    val vote_count: Long,
                    val video: Boolean,
                    val vote_average: Float)

data class MoviesPage(val page: Int,
                      val results: List<MovieRaw>,
                      val total_results: Int,
                      val total_pages: Int)