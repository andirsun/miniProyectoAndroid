package com.example.datastorage.Controladores

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import android.widget.Toast
import com.example.datastorage.Adapters.StatusReservationAdapter
import com.example.datastorage.Modelos.MoviexUser
import com.example.datastorage.R
import com.example.datastorage.Servicios.ReservationDBServices
import kotlinx.android.synthetic.main.row_item_status_reservation.*

class statusReservationActivity: AppCompatActivity() {
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_reservation)

        val listPosts: List<MoviexUser>? = ReservationDBServices(this).consultReservation()
        listView = findViewById<ListView>(R.id.liststatus) as ListView
        val adapter = StatusReservationAdapter(this, listPosts)
        listView.adapter = adapter
        //nReservas.setText("Lista de Reservas")

        listView.setClickable(true)
        listView.setOnItemClickListener { adapterView, view, i, l ->
            //Toast.makeText(this, "Nombre" + adapter.getName(i) + "id : "+adapter.getItemId(i) ,Toast.LENGTH_SHORT).show()
            val name=adapter.getName(i)
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("name",name)
            startActivity(intent)
        }
    }
}