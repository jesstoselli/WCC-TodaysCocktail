package com.androidwcc.todayscocktail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.androidwcc.todayscocktail.data.CocktailItemDao
import com.androidwcc.todayscocktail.data.DatabaseItems
import com.androidwcc.todayscocktail.repository.CocktailsListRepository
import com.androidwcc.todayscocktail.viewmodel.CocktailsListViewModel
import com.androidwcc.todayscocktail.viewmodel.CocktailsListViewModelFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = DatabaseItems.getDatabase(this, CoroutineScope(Dispatchers.IO))
        val dao = database.cocktailItemDao()
        val viewModelFactory = CocktailsListViewModelFactory(CocktailsListRepository(dao))
        val viewModel = ViewModelProvider(this, viewModelFactory).get(CocktailsListViewModel::class.java)
        val list = viewModel.cocktailList
        list.observe(this, Observer {
            val imageView = findViewById<ImageView>(R.id.imageView)
            Glide.with(this).load(it[0].thumbUrl)
                .into(imageView)
            findViewById<TextView>(R.id.textView).text = it[0].name
        })
    }
}