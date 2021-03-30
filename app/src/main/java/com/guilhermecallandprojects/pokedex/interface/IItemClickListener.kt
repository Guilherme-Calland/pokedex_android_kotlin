package com.guilhermecallandprojects.pokedex.`interface`

import android.view.View

interface IItemClickListener {
    fun onClick(view: View, position: Int)
}