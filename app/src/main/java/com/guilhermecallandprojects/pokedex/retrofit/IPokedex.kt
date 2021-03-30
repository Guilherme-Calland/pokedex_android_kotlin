package com.guilhermecallandprojects.pokedex.retrofit

import com.guilhermecallandprojects.pokedex.model.Pokedex
import io.reactivex.Observable
import retrofit2.http.GET

//retrofit turns your HTTP API into an interface

interface IPokedex {
    @get:GET("pokedex.json")
    val pokedex: Observable<Pokedex>
}