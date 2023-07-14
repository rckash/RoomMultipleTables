package com.example.roommultipletables.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.roommultipletables.R
import com.example.roommultipletables.database.AppDatabase
import com.example.roommultipletables.databinding.ActivityCustomersBinding
import com.example.roommultipletables.model.Customers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CustomersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCustomersBinding
    private lateinit var appDb: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDb = AppDatabase.invoke(this)

        //create
        binding.btnSave.setOnClickListener {
            var name = binding.etCustomerName.text.toString()
            var address = binding.etCustomerPrice.text.toString()
            val customer = Customers(0, name, address)
            save(customer)
        }
        //read
        binding.btnView.setOnClickListener {
            view()
        }
        //update
        binding.btnUpdate.setOnClickListener {
            var id = binding.etCustomerId.text.toString().toInt()
            var name = binding.etCustomerName.text.toString()
            var address = binding.etCustomerPrice.text.toString()
            val customer = Customers(id, name, address)
            update(customer)
        }
        //delete
        binding.btnDelete.setOnClickListener {
            var id = binding.etCustomerId.text.toString().toInt()
            val customer = Customers(id, "", "")
            delete(customer)
        }
    }

    //Create
    private fun save(customers: Customers) {
        GlobalScope.launch(Dispatchers.IO) {
            appDb.getCustomers().addCustomer(customers)
        }
        Toast.makeText(applicationContext, "Created!", Toast.LENGTH_SHORT).show()
    }

    //Read
    private fun view() {
        lateinit var customers: List<Customers>
        GlobalScope.launch(Dispatchers.IO) {
            customers = appDb.getCustomers().getAllCustomers()

            withContext(Dispatchers.Main) {
                Toast.makeText(applicationContext, customers.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    //Update
    private fun update(customers: Customers) {
        GlobalScope.launch(Dispatchers.IO) {
            appDb.getCustomers().updateCustomer(customers)
        }
        Toast.makeText(applicationContext, "Updated!", Toast.LENGTH_SHORT).show()
    }

    //Delete
    private fun delete(customers: Customers) {
        GlobalScope.launch(Dispatchers.IO) {
            appDb.getCustomers().deleteCustomer(customers)
        }
        Toast.makeText(applicationContext, "Deleted!", Toast.LENGTH_SHORT).show()
    }
}