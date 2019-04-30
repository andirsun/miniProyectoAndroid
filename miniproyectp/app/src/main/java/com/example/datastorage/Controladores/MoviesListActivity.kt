package com.example.datastorage.Controladores

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.example.datastorage.Adapters.MoviesListAdapter
import com.example.datastorage.Modelos.Movie
import com.example.datastorage.R
import com.example.datastorage.Servicios.MovieDBServices


class MoviesListActivity : AppCompatActivity() {
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)

        val listPosts: List<Movie>? = MovieDBServices(this).consultMovies()
        listView = findViewById(R.id.listMovies) as ListView
        val adapter = MoviesListAdapter(this, listPosts)
        listView.adapter = adapter


        listView.setClickable(true)
        listView.setOnItemClickListener { adapterView, view, i, l ->
            Toast.makeText(this, "Nombre" + adapter.getName(i) + "id : " + adapter.getItemId(i), Toast.LENGTH_SHORT)
                .show()
            val name = adapter.getName(i)
            val intent = Intent(this, ProfileMovieActivity::class.java)
            intent.putExtra("name", name)
            startActivity(intent)

        }
    }

    fun registrarMovie(view: View) {
        var intento = Intent(this, RegisterMovieActivity::class.java)
        startActivity(intento)

    }

    fun loadUsuarios(view: View) {
        var intento = Intent(this, UsersListActivity::class.java)
        startActivity(intento)
    }
}
