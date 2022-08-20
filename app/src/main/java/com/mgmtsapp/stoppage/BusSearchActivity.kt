package com.mgmtsapp.stoppage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class BusSearchActivity : AppCompatActivity() {
    //lateinit var spin : Spinner
    //lateinit var locaton : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_search)

        /*spin = findViewById(R.id.spinner) as Spinner
        locaton = findViewById(R.id.textView) as TextView

       val options = arrayOf("1.Dhaka Cantonment", "2.Tibbot")
       spin.adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,options)
        spin.onItemClickListener = object : AdapterView.OnItemSelectedListener,
            AdapterView.OnItemClickListener {

           override fun onItemSelected(
               parent: AdapterView<*>?,
               view: View?,
               position: Int,
               id: Long
           ) {
                    locaton.text=options.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

                locaton.text="Please select a option"
            }

            override fun onItemClick(
               parent: AdapterView<*>?,
              view: View?,
              position: Int,
                id: Long
            ) {
            }

        }*/


    }

}