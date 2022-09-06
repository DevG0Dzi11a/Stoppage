package com.mgmtsapp.stoppage

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.core.view.isEmpty
import com.mgmtsapp.stoppage.databinding.ActivityBusSearchBinding
import com.mgmtsapp.stoppage.databinding.ActivityHomeBinding

class BusSearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBusSearchBinding
    val locations: Array<String> = arrayOf(
        "",
        "Tibbot",
        "Banani",
        "Dhaka Cantonment",
        "Farmgate",
        "Gulshan",
        "Ecb Chattar",
        "Rampura",
        "Sahabag",
        "Airport",
        "Mohakhali",
        "Dhanmondi"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBusSearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchBtn.setOnClickListener {
            if (binding.editTextTime.text.isEmpty()) {
                binding.editTextTime.setError("Empty field")

            } else if (binding.editTextTime.text.toString()
                    .toInt() > 23 || binding.editTextTime.text.toString().toInt() < 0
            ) {
                binding.editTextTime.setError("Wrong input")
            } else if (binding.editTextTimeM.text.isEmpty()) {
                binding.editTextTimeM.setError("Empty field")
            } else if (binding.editTextTimeM.text.toString()
                    .toInt() > 59 || binding.editTextTimeM.text.toString().toInt() < 0
            ) {
                binding.editTextTimeM.setError("Wrong input")

            }else

                startActivity(Intent(this, EticketingActivity::class.java))
        }
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, locations)
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
        binding.spinner1.adapter = arrayAdapter
        binding.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

    override fun onBackPressed() {
        super.onBackPressed()
        finish();
    }


}

