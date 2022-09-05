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

        //For full screen
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

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
        binding.busBackBtn.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
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

    private fun setWindowFlag(bits: Int, on: Boolean) {
        //For full screen
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }


}

