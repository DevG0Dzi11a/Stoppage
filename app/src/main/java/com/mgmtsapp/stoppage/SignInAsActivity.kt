package com.mgmtsapp.stoppage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mgmtsapp.stoppage.databinding.ActivitySignInAsBinding

class SignInAsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInAsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInAsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signinAsDri.setOnClickListener{
            startActivity(Intent(this@SignInAsActivity, SignInActivity::class.java).also {
                it.putExtra("EXTRA_MESSAGE", "Driver")
            })
        }

        binding.signinAsPass.setOnClickListener{
            startActivity(Intent(this@SignInAsActivity, SignInActivity::class.java).also {
                it.putExtra("EXTRA_MESSAGE", "Passenger")
            })
        }
    }
}