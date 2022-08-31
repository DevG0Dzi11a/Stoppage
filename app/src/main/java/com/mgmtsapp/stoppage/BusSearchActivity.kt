package com.mgmtsapp.stoppage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.view.isEmpty
import com.mgmtsapp.stoppage.databinding.ActivityBusSearchBinding
import com.mgmtsapp.stoppage.databinding.ActivityHomeBinding

class BusSearchActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityBusSearchBinding
    val locations = arrayOf("Tibbot", "Farmage","Cantonment")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchBtn.setOnClickListener{
            startActivity(Intent(this,EticketingActivity::class.java))
        }
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, locations)
        binding.spinner.adapter = arrayAdapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }
}








