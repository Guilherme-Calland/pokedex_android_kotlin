package com.guilhermecallandprojects.pokedex.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.guilhermecallandprojects.pokedex.R
import com.guilhermecallandprojects.pokedex.`interface`.IItemClickListener
import com.guilhermecallandprojects.pokedex.common.Common
import com.guilhermecallandprojects.pokedex.common.showShortToast
import com.guilhermecallandprojects.pokedex.model.Pokemon
import kotlinx.android.synthetic.main.pokedex_item.view.*

class PokedexAdapter(
    internal val context: Context,
    internal val pokemonList: List<Pokemon>
    ) : RecyclerView.Adapter<PokedexAdapter.MyViewHolder>() {

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var imgPokemon = itemView.findViewById(R.id.iv_pokemon_image) as ImageView
        var namePokemon = itemView.findViewById(R.id.tv_pokemon_name) as TextView

        internal var itemClickListener: IItemClickListener ?= null

        fun setItemClickListener(iItemClickListener: IItemClickListener){
            this.itemClickListener = iItemClickListener
        }

        init{
            imgPokemon = itemView.findViewById(R.id.iv_pokemon_image) as ImageView
            namePokemon = itemView.findViewById(R.id.tv_pokemon_name) as TextView
            itemView.setOnClickListener { view -> itemClickListener!!.onClick(view, adapterPosition) }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView= LayoutInflater.from(context).inflate(R.layout.pokedex_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int = pokemonList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        loadPokemonImage(position, holder)
    }

    private fun loadPokemonImage(
        position: Int,
        holder: MyViewHolder
    ) {
        //glide is an image loading and caching library
        Glide.with(context).load(pokemonList[position].img).into(holder.imgPokemon)
        holder.namePokemon.text = pokemonList[position].name

        holder.setItemClickListener(object: IItemClickListener{
            override fun onClick(view: View, position: Int) {
                LocalBroadcastManager.getInstance(context)
                        .sendBroadcast(Intent(Common.KEY_ENABLE_HOME)
                                .putExtra("position", position))
            }
        })
    }
}