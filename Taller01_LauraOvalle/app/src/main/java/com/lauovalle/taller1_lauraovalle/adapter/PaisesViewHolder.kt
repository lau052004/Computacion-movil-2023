package com.lauovalle.taller1_lauraovalle.adapter

import android.R
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lauovalle.taller1_lauraovalle.SecondActivity
import com.lauovalle.taller1_lauraovalle.ThirdActivity
import com.lauovalle.taller1_lauraovalle.databinding.ItemPaisesBinding
import com.lauovalle.taller1_lauraovalle.paises

class PaisesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemPaisesBinding.bind(view)

    fun render(paisModel: paises) {
        binding.nombre.text = paisModel.Name
        binding.nombreNativo.text = paisModel.NativeName
        binding.codigo.text = paisModel.Alpha3Code
        binding.nombreMoneda.text = paisModel.CurrencyName
        binding.simbolo.text = paisModel.CurrencySymbol
        Glide.with(binding.bandera.context).load(paisModel.Flag).into(binding.bandera)

        binding.bandera.setOnClickListener{view ->
            val intent = Intent(itemView.context, ThirdActivity::class.java)
            //itemView.context.intent.putExtra(paisModel.Name)
            itemView.context.startActivity(intent)
        }

        binding.floatingActionButton.setOnClickListener {
                // Crea un Intent para la acción ACTION_DIAL y establece el número de teléfono
                val intent = Intent(Intent.ACTION_DIAL).apply {
                    data = Uri.parse("tel:$paisModel.NumericCode.toString()")
                }

                // Obtiene el PackageManager desde el contexto
                val packageManager = itemView.context.packageManager

                // Verifica si hay una actividad que pueda manejar el intent
                if (intent.resolveActivity(packageManager) != null) {
                    // Inicia la actividad de marcado de teléfono
                    itemView.context.startActivity(intent)
                } else {
                    // Manejo si no se encuentra una actividad para el intent
                    // Por ejemplo, mostrar un mensaje de error al usuario
                }
            }
        }
    }

