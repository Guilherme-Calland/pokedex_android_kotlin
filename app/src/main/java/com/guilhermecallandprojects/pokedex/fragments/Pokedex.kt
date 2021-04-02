package com.guilhermecallandprojects.pokedex.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guilhermecallandprojects.pokedex.R
import com.guilhermecallandprojects.pokedex.adapter.PokedexAdapter
import com.guilhermecallandprojects.pokedex.common.Common
import com.guilhermecallandprojects.pokedex.retrofit.IPokedex
import com.guilhermecallandprojects.pokedex.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_pokemon_list.*

//TODO: things this project uses: rerofit, glide, local broadcast manager

class Pokedex : Fragment() {

    internal var compositeDisposable = CompositeDisposable()
    internal var iPokedex: IPokedex

    internal lateinit var recyclerView: RecyclerView

    init{
        val retrofit = RetrofitClient.instance
        iPokedex = retrofit.create(IPokedex::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        // Inflate the layout for this fragment
        val itemView = inflater.inflate(R.layout.fragment_pokemon_list, container, false)
        recyclerView = itemView.findViewById(R.id.pokemon_recyclerview) as RecyclerView
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = GridLayoutManager(activity, 3)
        retreiveData()
        return itemView
    }

    private fun retreiveData() {
        compositeDisposable.add(iPokedex.pokedex
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { pokemonDex ->
                    Common.pokedex = pokemonDex.pokemon!!
                    val adapter = PokedexAdapter(activity!!, Common.pokedex)
                    recyclerView.adapter = adapter
                }
        )
    }
}