package com.androidwcc.todayscocktail.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.androidwcc.todayscocktail.entities.CocktailItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [CocktailItem::class], version = 1)
abstract class DatabaseItems: RoomDatabase() {

    abstract fun cocktailItemDao(): CocktailItemDao


    companion object {
        @Volatile
        private var INSTANCE: DatabaseItems? =
            null


        fun getDatabase(context: Context, scope: CoroutineScope): DatabaseItems {
            return INSTANCE
                ?: synchronized(this) {
                    val instance =
                        Room.databaseBuilder(
                            context.applicationContext,
                            DatabaseItems::class.java,
                            "cocktail_item_database"
                        ).addCallback(DatabaseCallBack(scope)).build()
                    INSTANCE = instance
                    instance
                }
        }

    }

    private class DatabaseCallBack(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch { populateDatabase(database.cocktailItemDao()) }
            }
        }




        suspend fun populateDatabase(dao: CocktailItemDao) {

            val itemOne = CocktailItem(
                1,
                "Mojito",
                "https://www.thecocktaildb.com/images/media/drink/uxywyw1468877224.jpg"
            )

            dao.insert(itemOne)

        }

    }
}