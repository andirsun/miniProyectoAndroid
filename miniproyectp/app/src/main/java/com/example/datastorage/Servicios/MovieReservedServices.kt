package com.example.datastorage.Servicios

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import com.example.datastorage.Modelos.Movie
import com.google.gson.Gson

class MovieReservedServices(context: Context) : AppCompatActivity(),IUMovieServices {
    override fun consultMovies(): List<Movie>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var preferencias = context.getSharedPreferences("peliculas", 0)

    override fun verifyMovie(movie: Movie): Boolean
    {
        var returnValue : Boolean = false
        var result = preferencias.getString(movie.name, null)

        if(result != null)
        {
            var localMovie = Gson().fromJson(result, Movie::class.javaObjectType)
            if (movie.name.equals(localMovie.name) && movie.idMovie==localMovie.idMovie)
            {
                returnValue = true
            }
        }

        return returnValue
    }

    override fun saveMovie(movie: Movie)
    {
        val editor = preferencias.edit()
        var jsonMovie = Gson().toJson(movie)
        editor.putString(movie.name, jsonMovie)
        editor.commit()
        finish()
    }

}