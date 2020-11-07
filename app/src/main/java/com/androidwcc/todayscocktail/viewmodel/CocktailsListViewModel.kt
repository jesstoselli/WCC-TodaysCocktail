package com.androidwcc.todayscocktail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.androidwcc.todayscocktail.entities.CocktailItem
import com.androidwcc.todayscocktail.network.Cocktail
import com.androidwcc.todayscocktail.repository.CocktailsListRepository

class CocktailsListViewModel(private val repository: CocktailsListRepository): ViewModel() {
    val  cocktailList: LiveData<List<CocktailItem>>
        get() = repository.cocktailList
}
