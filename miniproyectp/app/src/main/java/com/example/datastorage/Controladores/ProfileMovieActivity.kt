package com.example.datastorage.Controladores

import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log.d
import android.widget.Toast
import com.example.datastorage.Modelos.Movie
import com.example.datastorage.Modelos.User
import com.example.datastorage.R
import com.example.datastorage.Servicios.MovieDBServices
import kotlinx.android.synthetic.main.activity_profile_movie.*

class ProfileMovieActivity: AppCompatActivity() {

    private lateinit var registerService : MovieDBServices

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_movie)
        val name =  intent.getStringExtra("name")
        Toast.makeText(this, "Nombre" + name , Toast.LENGTH_SHORT).show()
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
       Toast.makeText(this, tempo,Toast.LENGTH_SHORT).show()

    }
}