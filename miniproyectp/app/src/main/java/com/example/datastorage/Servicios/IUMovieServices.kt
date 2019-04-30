package com.example.datastorage.Servicios

import com.example.datastorage.Modelos.Movie

interface IUMovieServices {
    fun verifyMovie(movie: Movie) : Boolean
    fun saveMovie(movie: Movie)
    fun consultMovies() : List<Movie>?
}