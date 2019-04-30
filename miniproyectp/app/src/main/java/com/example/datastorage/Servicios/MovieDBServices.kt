package com.example.datastorage.Servicios

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.datastorage.Modelos.Movie



class MovieDBServices(context: Context) : SQLiteOpenHelper(context, "MovieDBService", null, 1), IUMovieServices
{
    override fun onCreate(db: SQLiteDatabase?) {
        val sql : String = "CREATE TABLE movies(idMovie int primarykey ," +
                "name text," +
                "duration text," +
                "photo text," +
                "synopsis text,"+
                "genre text,"+
                "year text,"+
                "director text,"+
                "score text)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
        TODO("Sin implementación")
    }

    override fun verifyMovie(movie: Movie) : Boolean
    {
        val sql : String = "SELECT name,id FROM movies" +
                " where name='${movie.name}' &&id ='${movie.idMovie}' "

        val result : Cursor = this.executeQuery(sql, this.writableDatabase)
        var returnValue : Boolean = false

        if(result.moveToFirst())
        {
            if (movie.name.equals(result.getString(0)) && movie.idMovie == (result.getString(1)).toInt())
            {
                returnValue = true
            }
        }

        this.close()
        return returnValue
    }

    override fun saveMovie(movie: Movie)

    {
        var localMovie = ContentValues()
        localMovie.put("name", movie.name)
        localMovie.put("duration", movie.duration)
        localMovie.put("photo",movie.photo)
        localMovie.put("synopsis", movie.synopsis)
        localMovie.put("genre", movie.genre)
        localMovie.put("year",movie.year)
        localMovie.put("director",movie.director)
        localMovie.put("score",movie.score)

        this.executeModification(localMovie)
    }

    override fun consultMovies(): List<Movie>?
    {
        val sql : String = "SELECT idMovie, name, duration, photo, synopsis, genre, year,director, score FROM movies"
        val result : Cursor = this.executeQuery(sql, this.writableDatabase)
        var listMovie : MutableList<Movie>? = ArrayList<Movie>()
        result.moveToFirst()

        while(!result.isAfterLast)
        {

            var movie : Movie=  Movie(
                result.getInt(0),
                result.getString(1),
                result.getString(2),
                result.getString(3),
                result.getString(4),
                result.getString(5),
                result.getString(6),
                result.getString(7),
                result.getString(8)
                )

            listMovie?.add(movie)
            result.moveToNext()
        }

        return listMovie
    }

    fun buscarMovie(nombre: String ): List<Movie>?
    {

        val sql : String = "SELECT idMovie, name,duration,photo,synopsis,genre,year,director,score FROM movies WHERE name='"+nombre+"'"//i.toString()
        val result : Cursor = this.executeQuery(sql, this.writableDatabase)
        var listMovie : MutableList<Movie>? = ArrayList<Movie>()
        result.moveToFirst()
        while(!result.isAfterLast)
        {

            var movie : Movie = Movie(
                result.getInt(0),
                result.getString(1),
                result.getString(2),
                result.getString(3),
                result.getString(4),
                result.getString(5),
                result.getString(6),
                result.getString(7),
                result.getString(8)
            )

            listMovie?.add(movie)
            result.moveToNext()
        }

        return listMovie
    }

    private fun executeQuery(sql: String, bd : SQLiteDatabase) : Cursor
    {
        val consulta : Cursor = bd.rawQuery(sql,null)
        return consulta
    }

    private fun executeModification(movie: ContentValues)
    {
        val bd = this.writableDatabase
        bd.insert("movies", null, movie)
        bd.close()
    }
}