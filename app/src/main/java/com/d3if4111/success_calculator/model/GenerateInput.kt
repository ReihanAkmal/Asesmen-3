package com.d3if4111.success_calculator.model

import com.d3if4111.success_calculator.db.InputEntity

fun InputEntity.generateKataKata(): Output {
    // TODO
    val nama = nama
    val randomNumber = (1..100).random()
    fun kataKata(): String {
        return nama + " : " + randomNumber + "% Sukses"
    }

    val kataKataText = kataKata()

    return Output(nama, kataKataText)
}