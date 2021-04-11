package com.guilhermecallandprojects.pokedex.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.guilhermecallandprojects.pokedex.R
import com.guilhermecallandprojects.pokedex.adapter.PokemonEvolutionAdapter
import com.guilhermecallandprojects.pokedex.adapter.PokemonTypeAdapter
import com.guilhermecallandprojects.pokedex.common.Common
import com.guilhermecallandprojects.pokedex.model.Pokemon
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import kotlinx.android.synthetic.main.pokedex_item.*

class PokemonDetail : Fragment() {

    internal lateinit var pokemon_img: ImageView
    internal lateinit var pokemon_name: TextView
    internal lateinit var pokemon_weight: TextView
    internal lateinit var pokemon_height: TextView
    internal lateinit var pokemon_type: RecyclerView
    internal lateinit var pokemon_weakness: RecyclerView
    internal lateinit var pokemon_prev_evolution: RecyclerView
    internal lateinit var pokemon_next_evolution: RecyclerView

    companion object{
        internal var instance: PokemonDetail?=null

        fun getInstance(): PokemonDetail {
            if(instance == null){
                instance = PokemonDetail()
            }
            return instance!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val itemView = inflater.inflate(R.layout.fragment_pokemon_detail, container, false)

        val pokemon:Pokemon?
        if(arguments!!.getString("num") == null){
            pokemon = Common.pokedex[arguments!!.getInt("position")]
        } else {
            pokemon = Common.findPokemonByNum(arguments!!.getString("num"))
        }

        pokemon_img = itemView.findViewById(R.id.iv_pokemon_image) as ImageView
        pokemon_name = itemView.findViewById(R.id.tv_pokemon_name) as TextView
        pokemon_height = itemView.findViewById(R.id.tv_pokemon_height) as TextView
        pokemon_weight = itemView.findViewById(R.id.tv_pokemon_weight) as TextView

        pokemon_type = itemView.findViewById(R.id.rv_pokemon_type) as RecyclerView
        pokemon_type.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        pokemon_weakness = itemView.findViewById(R.id.rv_pokemon_weakness) as RecyclerView
        pokemon_weakness.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        pokemon_prev_evolution = itemView.findViewById(R.id.rv_pokemon_prev_evolution) as RecyclerView
        pokemon_prev_evolution.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        pokemon_next_evolution = itemView.findViewById(R.id.rv_pokemon_next_evolution) as RecyclerView
        pokemon_next_evolution.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        setDetailPokemon(pokemon)

        return itemView
    }

    private fun setDetailPokemon(pokemon: Pokemon?) {

        //load image
        Glide.with(activity!!).load(pokemon!!.img).into(pokemon_img)

        pokemon_name.text = pokemon.name
        pokemon_height.text = "Height: ${pokemon.height}"
        pokemon_weight.text = "Weight: ${pokemon.weight}"

        val typeAdapter = PokemonTypeAdapter(activity!!,pokemon.type!!)
        pokemon_type.adapter = typeAdapter

        val weaknessAdapter = PokemonTypeAdapter(activity!!,pokemon.weaknesses!!)
        pokemon_weakness.adapter = weaknessAdapter


        if(pokemon.prev_evolution != null){
            val prevEvolutionAdapter = PokemonEvolutionAdapter(activity!!, pokemon.prev_evolution!!)
            pokemon_prev_evolution.adapter = prevEvolutionAdapter
        }

        if(pokemon.next_evolution != null){
            val nextEvolutionAdapter = PokemonEvolutionAdapter(activity!!, pokemon.next_evolution!!)
            pokemon_next_evolution.adapter = nextEvolutionAdapter
        }


    }
}