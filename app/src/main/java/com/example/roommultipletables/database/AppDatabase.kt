package com.example.roommultipletables.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roommultipletables.dao.CustomersDao
import com.example.roommultipletables.dao.ProductsDao
import com.example.roommultipletables.model.Customers
import com.example.roommultipletables.model.Products

@Database(
    entities = [Products::class, Customers::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getProducts(): ProductsDao
    abstract fun getCustomers(): CustomersDao

    companion object{
        @Volatile private var instance: AppDatabase? = null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app_db"
        ).build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
    }
}
