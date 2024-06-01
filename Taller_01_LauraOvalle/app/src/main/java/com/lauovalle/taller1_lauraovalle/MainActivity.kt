package com.lauovalle.taller1_lauraovalle

import android.R
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.lauovalle.taller1_lauraovalle.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var countrySpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        var regionSeleccionada: String

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        countrySpinner = binding.spinner

        val regionesGeograficas = arrayOf("Africa", "Americas", "Asia", "Europa", "Oceania")
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, regionesGeograficas)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        countrySpinner.adapter = adapter

        // Agrega un oyente al `Spinner`
        countrySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                // Haz algo cuando el usuario selecciona una opción
                regionSeleccionada = regionesGeograficas[position]
                binding.boton1.setOnClickListener{ view ->
                    val intent = Intent(this@MainActivity, SecondActivity::class.java)
                    intent.putExtra("region", regionSeleccionada)
                    startActivity(intent)
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // No es necesario hacer nada aquí
            }
        }
    }
}
