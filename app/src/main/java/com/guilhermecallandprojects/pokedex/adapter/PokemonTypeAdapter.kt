package com.guilhermecallandprojects.pokedex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.guilhermecallandprojects.pokedex.R
import com.guilhermecallandprojects.pokedex.`interface`.IItemClickListener
import com.guilhermecallandprojects.pokedex.common.Common
import com.robertlevonyan.views.chip.Chip
import kotlinx.android.synthetic.main.chip_item.view.*

class PokemonTypeAdapter(internal var context: Context, internal var typeList:List<String>)
    : RecyclerView.Adapter<PokemonTypeAdapter.MyViewHolder>()
{
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        internal var chip: Chip

        init{
            chip = itemView.findViewById(R.id.chip) as Chip
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonTypeAdapter.MyViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.chip_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int  = typeList.size

    override fun onBindViewHolder(holder: PokemonTypeAdapter.MyViewHolder, position: Int) {
        holder.chip.chipText = typeList[position]
        holder.chip.changeBackgroundColor(Common.getColorByType(typeList[position]))
    }

}