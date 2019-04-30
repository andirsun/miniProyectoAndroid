package com.example.datastorage.Controladores

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.datastorage.Modelos.Movie
import com.example.datastorage.R
import com.example.datastorage.Servicios.MovieDBServices
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register_movie.*

class RegisterMovieActivity : AppCompatActivity() {

    private lateinit var registerService : MovieDBServices
    private lateinit var ruta : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_movie)
        registerService= MovieDBServices(this)
    }

    companion object {
        private val IMAGE_PICK_CODE = 1000;
        private val PERMISSION_CODE = 1001;
    }
    fun seleccionarImagen(view: View){
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
            val permisos = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            requestPermissions(permisos,PERMISSION_CODE)
        }
        else{
            cargarImagen()
        }
    }
    fun cargarImagen(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type="image/"
        startActivityForResult(intent, IMAGE_PICK_CODE)


    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE ->
                if(grantResults.size>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    cargarImagen()
                }
                else{
                    Toast.makeText(this,"No se genero el permiso", Toast.LENGTH_SHORT).show()
                }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode== Activity.RESULT_OK && requestCode== IMAGE_PICK_CODE){
            val path= arrayOf(MediaStore.Images.Media.DATA)
            val cursor  = this.contentResolver.query(data?.data,path,null,null,null)
            assert(cursor!=null)
            cursor.moveToFirst()
            ruta=cursor.getString(cursor.getColumnIndex(path[0]))
            cursor.close()
            selectImage_movie.setImageURI(Uri.parse(ruta))
        }
    }
    fun ingresarPelicula(view: View)
    {
        val nombre= findViewById<TextView>(R.id.nombre_movie)
        val duracion=findViewById<TextView>(R.id.duracion)
        val resumen= findViewById<TextView>(R.id.sinopsis)
        val fecha=findViewById<TextView>(R.id.ano)
        val director=findViewById<TextView>(R.id.director)
        val genero=findViewById<TextView>(R.id.genero)
        val movie = Movie(null, nombre.text.toString(), duracion.text.toString(),ruta, resumen.text.toString(), genero.text.toString(), fecha.text.toString(), director.text.toString(),"4.5")
        //val movie = Movie(null,"LOTR" ,"360",ruta,"Sipno","ficcion","2001","Peter Jackson","5")
        MovieDBServices(this).saveMovie(movie)






        //Toast.makeText(this, "Datos incorrectos",  Toast.LENGTH_SHORT).show()
    }
    fun cargarPeliculas(view: View){
        val intent = Intent(this, MoviesListActivity::class.java)
        startActivity(intent)
    }

}