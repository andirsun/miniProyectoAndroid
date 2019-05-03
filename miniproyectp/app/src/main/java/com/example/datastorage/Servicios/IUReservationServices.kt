package com.example.datastorage.Servicios

import com.example.datastorage.Modelos.MoviexUser

interface IUReservationServices {
    fun verifyReservation(reservation : MoviexUser): Boolean
    fun saveReservation(reservation  : MoviexUser)
    fun consultReservation(): List<MoviexUser>

}