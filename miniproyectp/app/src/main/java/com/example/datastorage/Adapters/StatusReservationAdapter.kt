package com.example.datastorage.Adapters

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.datastorage.Modelos.MoviexUser
import com.example.datastorage.R

class StatusReservationAdapter(private val activity: Activity, statuslist : List<MoviexUser>?) : BaseAdapter() {
    private var statuslist= ArrayList<MoviexUser>()

    init {
        this.statuslist = statuslist as ArrayList<MoviexUser>
    }

    override fun getCount(): Int {
        return statuslist.size
    }

    override fun getItem(i: Int): Any {
        return statuslist[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    fun getName(i: Int): String? {
        return statuslist[i].nameUser
    }

    override fun getView(i: Int, convertView: View?, viewGroup: ViewGroup): View {
        var vi: View
        val inflater = activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        vi = inflater.inflate(R.layout.row_item_status_reservation, null)
        val nameUser = vi.findViewById<TextView>(R.id.quien_reserva)
        val nameMovie =vi.findViewById<TextView>(R.id.peli_reservada)
        nameUser.text = statuslist[i].nameUser.toString()
        nameMovie.text = statuslist[i].nameMovie.toString()
        return vi
    }
}