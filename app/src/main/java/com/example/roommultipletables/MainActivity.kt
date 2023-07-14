package com.example.roommultipletables

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.roommultipletables.activity.CustomersActivity
import com.example.roommultipletables.activity.ProductsActivity
import com.example.roommultipletables.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnMainProduct.setOnClickListener {
            val myIntent = Intent(this, ProductsActivity::class.java)
            startActivity(myIntent)
        }
        binding.btnMainCustomer.setOnClickListener {
            val myIntent = Intent(this, CustomersActivity::class.java)
            startActivity(myIntent)
        }
    }
}