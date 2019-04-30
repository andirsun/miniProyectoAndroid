package com.example.datastorage.Adapters

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.datastorage.Modelos.Movie
import com.example.datastorage.R

class MoviesListAdapter(private val activity: Activity, moviesList: List<Movie>?) : BaseAdapter(){
    private var moviesList = ArrayList<Movie>()

    init {
        this.moviesList = moviesList as ArrayList<Movie>
    }

    override fun getCount(): Int {
        return moviesList.size
    }

    override fun getItem(i: Int): Any {
        return moviesList[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    fun getName(i: Int): String? {
        return moviesList[i].name
    }

    override fun getView(i: Int, convertView: View?, viewGroup: ViewGroup): View {
        var vi: View
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        vi = inflater.inflate(R.layout.row_item_movie, null)
        val name = vi.findViewById<TextView>(R.id.nombre_movie)
        val duration = vi.findViewById<TextView>(R.id.duracion)
        val genero = vi.findViewById<TextView>(R.id.genero)
        val imagen=vi.findViewById<ImageView>(R.id.movieImage)
        val puntuacion = vi.findViewById<TextView>(R.id.puntuacion)
        name.text = moviesList[i].name.toString()
        duration.text = moviesList[i].duration.toString()
        genero.text =moviesList[i].genre.toString()
        puntuacion.text= moviesList[i].score.toString()
        imagen.setImageURI(Uri.parse(moviesList[i].photo))
        return vi
    }
}