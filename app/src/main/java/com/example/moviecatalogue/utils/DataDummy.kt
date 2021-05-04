package com.example.moviecatalogue.utils

import com.example.moviecatalogue.data.source.local.MovieEntity
import com.example.moviecatalogue.data.source.local.TvShowEntity
import com.example.moviecatalogue.data.source.remote.response.*

object DataDummy {

    fun getDummyListMovie(): List<MovieEntity> {
        val result = ArrayList<MovieEntity>()
        result.add(
            MovieEntity(
                title = "Harry Potter and the Deathly Hallows: Part 1",
                description = "Harry, Ron and Hermione walk away from their last year at Hogwarts to find and destroy the remaining Horcruxes, putting an end to Voldemort's bid for immortality. But with Harry's beloved Dumbledore dead and Voldemort's unscrupulous Death Eaters on the loose, the world is more dangerous than ever.",
                rating = 7.8,
                imgPoster = "/iGoXIpQb7Pot00EEdwpwPajheZ5.jpg",
                releaseDate = "2010-10-17",
                status = "Released",
                id = 12444
            )
        )
        result.add(
            MovieEntity(
                title = "Harry Potter and the Deathly Hallows: Part 2",
                description = "Harry, Ron and Hermione continue their quest to vanquish the evil Voldemort once and for all. Just as things begin to look hopeless for the young wizards, Harry discovers a trio of magical objects that endow him with powers to rival Voldemort's formidable skills.",
                rating = 8.1,
                imgPoster = "/da22ZBmrDOXOCDRvr8Gic8ldhv4.jpg",
                releaseDate = "2011-07-07",
                status = "Released",
                id = 12445
            )
        )
        return result
    }

    fun getDummyListTvShow(): List<TvShowEntity> {
        val result = ArrayList<TvShowEntity>()
        result.add(
            TvShowEntity(
                title = "Animal Fight Night",
                description = "Featuring battles between some of the biggest and baddest fighters of the animal kingdom and some of the most surprising, revealing the extraordinary motivations and strategies that fuel each incredible brawl.",
                imgPoster = "/82BySkOlP2jNsswNr7lwHCwWi7F.jpg",
                rating = 9.0,
                id = 71906,
                releaseDate = "2013-10-06",
                status = "Returning Series"
            )
        )
        return result
    }

    fun getDummyMovieResponse(): MovieResponse {
        return MovieResponse(
            results = mutableListOf(
                MovieItems(
                    "Harry Potter and the Deathly Hallows: Part 1",
                    "/iGoXIpQb7Pot00EEdwpwPajheZ5.jpg",
                    12444
                ),
                MovieItems(
                    "Harry Potter and the Deathly Hallows: Part 2",
                    "/da22ZBmrDOXOCDRvr8Gic8ldhv4.jpg",
                    12445
                )
            )
        )
    }

    fun getDummyTvShowResponse(): TvShowResponse {
        return TvShowResponse(
            results = mutableListOf(
                TvShowItems("/82BySkOlP2jNsswNr7lwHCwWi7F.jpg", "Animal Fight Night", 71906)
            )
        )
    }

    fun getDummyDetailMovieResponse(): DetailMovieResponse {
        return DetailMovieResponse(
            title = "Harry Potter and the Deathly Hallows: Part 1",
            overview = "Harry, Ron and Hermione walk away from their last year at Hogwarts to find and destroy the remaining Horcruxes, putting an end to Voldemort's bid for immortality. But with Harry's beloved Dumbledore dead and Voldemort's unscrupulous Death Eaters on the loose, the world is more dangerous than ever.",
            voteAverage = 7.8,
            posterPath = "/iGoXIpQb7Pot00EEdwpwPajheZ5.jpg",
            releaseDate = "2010-10-17",
            status = "Released",
            id = 12444
        )
    }

    fun getDummyDetailTvShowResponse(): DetailTvShowResponse {
        return DetailTvShowResponse(
            name = "Animal Fight Night",
            overview = "Featuring battles between some of the biggest and baddest fighters of the animal kingdom and some of the most surprising, revealing the extraordinary motivations and strategies that fuel each incredible brawl.",
            posterPath = "/82BySkOlP2jNsswNr7lwHCwWi7F.jpg",
            voteAverage = 9.0,
            id = 71906,
            firstAirDate = "2013-10-06",
            status = "Returning Series"
        )
    }

}