package com.androidwcc.todayscocktail.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CocktailsAPIService {
    @GET("filter.php?a=Alcoholic")
    suspend fun getAlcoholicCocktails(): CocktailsResponse

    @GET("filter.php?a=Non_Alcoholic")
    suspend fun getNonAlcoholicCocktails(): CocktailsResponse

}

object CocktailsAPI {
    val retrofitService: CocktailsAPIService by lazy {
        retrofit.create(CocktailsAPIService::class.java)
    }
}
