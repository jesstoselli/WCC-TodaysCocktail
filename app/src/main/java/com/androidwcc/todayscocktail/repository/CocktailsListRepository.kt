package com.androidwcc.todayscocktail.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidwcc.todayscocktail.network.Cocktail
import com.androidwcc.todayscocktail.network.CocktailsAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CocktailsListRepository {
    private val cocktailListResponse = MutableLiveData<List<Cocktail>>()

    val  cocktailList: LiveData<List<Cocktail>>
        get() = cocktailListResponse

    init {
        getCocktailsList()
    }

    private fun getCocktailsList() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val listResult = CocktailsAPI.retrofitService.getAlcoholicCocktails().cocktailsList
                cocktailListResponse.postValue(listResult)

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    cocktailListResponse.postValue(listOf())
                }
            }
        }
    }
}