package com.example.datastorage.Controladores

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log.d
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.datastorage.Modelos.Movie
import com.example.datastorage.Modelos.MoviexUser
import com.example.datastorage.Modelos.User
import com.example.datastorage.R
import com.example.datastorage.Servicios.MovieDBServices
import com.example.datastorage.Servicios.ReservationDBServices
import kotlinx.android.synthetic.main.activity_profile_movie.*

class ProfileMovieActivity: AppCompatActivity() {

    private lateinit var registerService : MovieDBServices
    private lateinit var user : String
    private lateinit var name : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_movie)
        name =  intent.getStringExtra("name")
        user= intent.getStringExtra ("user")
        Toast.makeText(this, "Nombre pelicula" + name , Toast.LENGTH_SHORT).show()
        Toast.makeText(this, "Nombre usuario" + user , Toast.LENGTH_SHORT).show()
        llenarPerfilPelicula(name)
    }
    fun llenarPerfilPelicula(nombre: String)
    {

        //this.registerService.saveUser(user)
        val lista: List<Movie>?  = MovieDBServices(this).buscarMovie(nombre)
        d("tag", lista.toString())
        var tempo=lista!![0].toString()
        image_mov.setImageURI(Uri.parse(lista!![0].photo))
        nombre_pelicula.setText(lista!![0].name)
        Año_pelicula.setText(lista!![0].year)
        director_pelicula.setText(lista!![0].director)
        resumen_pelicula.setText(lista!![0].synopsis)
        Nada.setText("Sinopsios")
       //Toast.makeText(this, tempo,Toast.LENGTH_SHORT).show()

    }
    fun reservarButtonClicked(view : View){
        //Toast.makeText(this, "reserva",Toast.LENGTH_SHORT).show()
        //Toast.makeText(this,user,Toast.LENGTH_SHORT).show()
        //Toast.makeText(this,name,Toast.LENGTH_SHORT).show()
        reservar(user, name)
    }
    fun reservar(usuario: String, pelicula: String) : Boolean{
        val reserva = MoviexUser(null,usuario,pelicula)
        ReservationDBServices(this).saveReservation(reserva)
        val intent = Intent(this, statusReservationActivity::class.java)
        startActivity(intent)
        Toast.makeText(this, "aqui se hizo la reserva", Toast.LENGTH_SHORT).show()
        return true
    }
}