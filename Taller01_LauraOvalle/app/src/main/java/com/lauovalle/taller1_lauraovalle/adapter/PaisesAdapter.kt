package com.lauovalle.taller1_lauraovalle.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lauovalle.taller1_lauraovalle.R
import com.lauovalle.taller1_lauraovalle.paises

class PaisesAdapter(private val paisesList:List<paises>) : RecyclerView.Adapter<PaisesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaisesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PaisesViewHolder(layoutInflater.inflate(R.layout.item_paises,parent,false))
    }

    override fun getItemCount(): Int {
        return paisesList.size
    }

    override fun onBindViewHolder(holder: PaisesViewHolder, position: Int) {
        val item = paisesList[position]
        holder.render(item)
    }

}