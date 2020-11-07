package com.androidwcc.todayscocktail.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.androidwcc.todayscocktail.entities.CocktailItem

@Dao
interface CocktailItemDao {
    @Query("SELECT * FROM cocktail_item ORDER BY name ASC")
    fun getAll(): List<CocktailItem>

    @Insert
    suspend fun insert(item: CocktailItem)
}