package com.d3if4111.success_calculator.ui.quotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.d3if4111.success_calculator.db.InputDao
import com.d3if4111.success_calculator.db.InputEntity
import com.d3if4111.success_calculator.model.Output
import com.d3if4111.success_calculator.model.generateKataKata
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeViewModel(private val db: InputDao): ViewModel() {
    private val kataKata = MutableLiveData<Output?>()

    fun generateKataKata(nama: String) {
        val dataQuotes = InputEntity(
            nama = nama,
        )
        kataKata.value = dataQuotes.generateKataKata()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataQuotes)
            }
        }
    }

    fun getKataKata(): LiveData<Output?> = kataKata
}