package com.mgmtsapp.stoppage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mgmtsapp.stoppage.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        supportActionBar?.show()
        setContentView(binding.root)

    }
}