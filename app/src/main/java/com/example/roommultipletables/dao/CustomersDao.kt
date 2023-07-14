package com.example.roommultipletables.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.roommultipletables.model.Customers
import com.example.roommultipletables.model.Products

@Dao
interface CustomersDao {
    //Create
    @Insert
    fun addCustomer(customers: Customers)

    //Read
    @Query("SELECT * FROM customers")
    fun getAllCustomers(): List<Customers>

    //Update
    @Update
    fun updateCustomer(customers: Customers)

    //Delete
    @Delete
    fun deleteCustomer(customers: Customers)
}