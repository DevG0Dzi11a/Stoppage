package com.mgmtsapp.stoppage

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.mgmtsapp.stoppage.databinding.ActivityBusSearchBinding
import com.mgmtsapp.stoppage.databinding.ActivityEticketingBinding

class EticketingActivity :AppCompatActivity() {

    private lateinit var binding: ActivityEticketingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEticketingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.eBackBtn.setOnClickListener {
            startActivity(Intent(this, BusSearchActivity::class.java))
        }


    }
}