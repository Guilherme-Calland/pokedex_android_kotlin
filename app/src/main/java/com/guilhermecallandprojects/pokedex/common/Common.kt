package com.guilhermecallandprojects.pokedex.common

import android.content.Context
import android.widget.Toast
import com.guilhermecallandprojects.pokedex.model.Pokemon

object Common {
    fun findPokemonByNum(num: String?): Pokemon? {
        for(pokemon in Common.pokedex){
            if(pokemon.num.equals(num))
                return pokemon
        }
        return null
    }

    var pokedex: List<Pokemon> = ArrayList()
    const val KEY_ENABLE_HOME: String = "position"


}
