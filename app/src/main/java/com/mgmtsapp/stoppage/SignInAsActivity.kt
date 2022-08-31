package com.mgmtsapp.stoppage

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.mgmtsapp.stoppage.databinding.ActivitySignInAsBinding

class SignInAsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInAsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInAsBinding.inflate(layoutInflater)
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