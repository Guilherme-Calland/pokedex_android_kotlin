package com.guilhermecallandprojects.pokedex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guilhermecallandprojects.pokedex.R
import com.guilhermecallandprojects.pokedex.common.Common
import com.guilhermecallandprojects.pokedex.model.Evolution
import com.robertlevonyan.views.chip.Chip

class PokemonEvolutionAdapter(internal var context: Context, var evolutionList: List<Evolution>)
    :RecyclerView.Adapter<PokemonEvolutionAdapter.MyViewHolder>() {

    init{
        if(evolutionList == null){
            evolutionList = ArrayList()
        }
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        internal var chip: Chip

        init{
            chip = itemView.findViewById(R.id.chip) as Chip
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.chip_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = evolutionList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.chip.chipText = evolutionList[position].name
        holder.chip.changeBackgroundColor(Common.getColorByType(Common.findPokemonByNum(evolutionList[position].num!!)!!.type!![0]))

    }

}