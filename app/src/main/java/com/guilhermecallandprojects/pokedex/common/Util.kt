package com.guilhermecallandprojects.pokedex.common

import android.content.Context
import android.widget.Toast

fun showShortToast(context: Context, message: String) =
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()