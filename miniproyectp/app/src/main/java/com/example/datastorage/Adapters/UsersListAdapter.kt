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
import com.example.datastorage.R
import com.example.datastorage.Modelos.User

class UsersListAdapter(private val activity: Activity, usersList: List<User>?) : BaseAdapter(){
    private var usersList = ArrayList<User>()

    init {
        this.usersList = usersList as ArrayList<User>
    }

    override fun getCount(): Int {
        return usersList.size
    }

    override fun getItem(i: Int): Any {
        return usersList[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    fun getName(i: Int): String? {
        return usersList[i].name
    }

    override fun getView(i: Int, convertView: View?, viewGroup: ViewGroup): View {
        var vi: View
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        vi = inflater.inflate(R.layout.row_item, null)
        val nombre = vi.findViewById<TextView>(R.id.nombre_movie)
        val edad = vi.findViewById<TextView>(R.id.genero)
        val imagen=vi.findViewById<ImageView>(R.id.movieImage)
        nombre.text = usersList[i].name
        edad.text = usersList[i].age.toString()
        imagen.setImageURI(Uri.parse(usersList[i].photo))
        return vi
    }
}