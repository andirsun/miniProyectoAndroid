package com.example.datastorage.Servicios

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.datastorage.Modelos.MoviexUser

class ReservationDBServices(context: Context) : SQLiteOpenHelper(context, "ReservationDBServices", null, 1 ), IUReservationServices{

    override fun onCreate(db: SQLiteDatabase?) {
        val sql : String = "CREATE TABLE reservations(idMoviexUser int primarykey ," +
                "nameUser text," +
                "nameMovie text)"
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
        TODO("Sin implementación")
    }

    override fun verifyReservation(reservation: MoviexUser) : Boolean
    {
        val sql : String = "SELECT nameUser, nameMovie FROM reservations" +
                " where nameUser='${reservation.nameUser}' && nameMovie ='${reservation.nameMovie}' "

        val result : Cursor = this.executeQuery(sql, this.writableDatabase)
        var returnValue : Boolean = false

        if(result.moveToFirst())
        {
            if (reservation.nameUser.equals(result.getString(0)) && (reservation.nameMovie.equals(result.getString(1))))
            {
                returnValue = true
            }
        }

        this.close()
        return returnValue
    }

    override fun saveReservation(reservation: MoviexUser)

    {
        var localReservation = ContentValues()
        localReservation.put("nameUser", reservation.nameUser)
        localReservation.put("nameMovie", reservation.nameMovie)

        this.executeModification(localReservation)
    }

    override fun consultReservation(): List<MoviexUser>
    {
        val sql : String = "SELECT idMoviexUser, nameUser, nameMovie FROM reservations"
        val result : Cursor = this.executeQuery(sql, this.writableDatabase)
        var listReservation : MutableList<MoviexUser>? = ArrayList<MoviexUser>()
        result.moveToFirst()

        while(!result.isAfterLast)
        {

            var reservation : MoviexUser=  MoviexUser(
                result.getInt(0),
                result.getString(1),
                result.getString(2)
            )

            listReservation?.add(reservation)
            result.moveToNext()
        }

        return listReservation as List<MoviexUser>
    }

    private fun executeQuery(sql: String, bd : SQLiteDatabase) : Cursor
    {
        val consulta : Cursor = bd.rawQuery(sql,null)
        return consulta
    }

    private fun executeModification(reservation: ContentValues)
    {
        val bd = this.writableDatabase
        bd.insert("reservations", null, reservation)
        bd.close()
    }
}

