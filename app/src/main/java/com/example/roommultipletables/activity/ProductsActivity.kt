package com.example.roommultipletables.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.roommultipletables.database.AppDatabase
import com.example.roommultipletables.databinding.ActivityProductsBinding
import com.example.roommultipletables.model.Products
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductsBinding
    private lateinit var appDb: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDb = AppDatabase.invoke(this)

        //create
        binding.btnSave.setOnClickListener {
            var name = binding.etProductName.text.toString()
            var price = binding.etProductPrice.text.toString().toInt()
            val product = Products(0, name, price)
            save(product)
        }
        //read
        binding.btnView.setOnClickListener {
            view()
        }
        //update
        binding.btnUpdate.setOnClickListener {
            var id = binding.etProductId.text.toString().toInt()
            var name = binding.etProductName.text.toString()
            var price = binding.etProductPrice.text.toString().toInt()
            val product = Products(id, name, price)
            update(product)
        }
        //delete
        binding.btnDelete.setOnClickListener {
            var id = binding.etProductId.text.toString().toInt()
            val product = Products(id, "", 0)
            delete(product)
        }
    }

    //Create
    private fun save(products: Products) {
        GlobalScope.launch(Dispatchers.IO) {
            appDb.getProducts().addProduct(products)
        }
        Toast.makeText(applicationContext, "Created!", Toast.LENGTH_SHORT).show()
    }

    //Read
    private fun view() {
        lateinit var products: List<Products>
        GlobalScope.launch(Dispatchers.IO) {
            products = appDb.getProducts().getAllProducts()

            withContext(Dispatchers.Main) {
                Toast.makeText(applicationContext, products.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    //Update
    private fun update(products: Products) {
        GlobalScope.launch(Dispatchers.IO) {
            appDb.getProducts().updateProduct(products)
        }
        Toast.makeText(applicationContext, "Updated!", Toast.LENGTH_SHORT).show()
    }

    //Delete
    private fun delete(products: Products) {
        GlobalScope.launch(Dispatchers.IO) {
            appDb.getProducts().deleteProduct(products)
        }
        Toast.makeText(applicationContext, "Deleted!", Toast.LENGTH_SHORT).show()
    }
}