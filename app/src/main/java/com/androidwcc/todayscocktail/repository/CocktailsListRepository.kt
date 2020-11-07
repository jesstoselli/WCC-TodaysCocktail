package com.androidwcc.todayscocktail.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.androidwcc.todayscocktail.data.CocktailItemDao
import com.androidwcc.todayscocktail.data.DatabaseItems
import com.androidwcc.todayscocktail.entities.CocktailItem
import com.androidwcc.todayscocktail.network.Cocktail
import com.androidwcc.todayscocktail.network.CocktailsAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CocktailsListRepository(private val dao: CocktailItemDao) {
    private val cocktailListResponse = MutableLiveData<List<CocktailItem>>()

    val  cocktailList: LiveData<List<CocktailItem>>
        get() = cocktailListResponse

    init {
        getCocktailsList()
    }

    private fun getCocktailsList() {

        CoroutineScope(Dispatchers.IO).launch {
                val listResult: List<CocktailItem> = dao.getAll()
               cocktailListResponse.postValue(listResult)
        }

        if (cocktailListResponse.value === null) {

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val listResult = CocktailsAPI.retrofitService.getAlcoholicCocktails().cocktailsList as List<CocktailItem>
                    cocktailListResponse.postValue(listResult)
                    listResult.forEach {
                        dao.insert(it)
                    }

                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        cocktailListResponse.postValue(listOf())
                    }
                }
            }

        }


    }
}