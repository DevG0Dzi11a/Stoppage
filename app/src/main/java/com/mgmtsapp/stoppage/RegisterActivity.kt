package com.mgmtsapp.stoppage

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mgmtsapp.stoppage.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()


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


        binding.regSigninBtn.setOnClickListener {
            binding.regSigninBtn.setTextColor(Color.RED)
            startActivity(Intent(this@RegisterActivity, SignInActivity::class.java))
        }

        binding.regBtn.setOnClickListener {
            val regEmail = binding.regEmailText.text.toString().trim()
            val regPass = binding.regPassText.text.toString().trim()
            val regConPass = binding.regConPassText.text.toString().trim()
            val regPhone = binding.regPhone.text.toString().trim()
            binding.regpBar.visibility = View.VISIBLE

            if (regEmail.isNotEmpty() && regPass.isNotEmpty() && regConPass.isNotEmpty() && regPhone.length == 14) {
                if (regPass == regConPass) {
                    firebaseAuth.createUserWithEmailAndPassword(regEmail, regPass)
                        .addOnCompleteListener {
                            binding.regpBar.visibility = View.INVISIBLE
                            if (it.isSuccessful) {
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "Registered",
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity(
                                    Intent(
                                        this@RegisterActivity,
                                        SignInActivity::class.java
                                    )
                                )
                                binding.regpBar.visibility = View.INVISIBLE
                                finish()
                            } else {
                                binding.regpBar.visibility = View.INVISIBLE
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "Failed to register",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    binding.regpBar.visibility = View.INVISIBLE
                    Toast.makeText(this@RegisterActivity, "Password Mismatched", Toast.LENGTH_SHORT)
                        .show()
                    binding.regConPassText.error = "Password mismatch"
                    binding.regConPassText.requestFocus()
                }
            } else {
                binding.regpBar.visibility = View.INVISIBLE
                if (regPhone.length != 14) {
                    binding.regPhone.error = "Wrong phone number!"
                    binding.regPhone.requestFocus()
                }
                if (regConPass.isEmpty()) {
                    binding.regConPassText.error = "Empty field!"
                    binding.regConPassText.requestFocus()
                }
                if (regPass.isEmpty()) {
                    binding.regPassText.error = "Empty field!"
                    binding.regPassText.requestFocus()
                }
                if (regEmail.isEmpty()) {
                    binding.regEmailText.error = "Empty field!"
                    binding.regEmailText.requestFocus()
                }
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        binding.regSigninBtn.setTextColor(Color.parseColor("#9A62CC"))
        binding.regpBar.visibility = View.INVISIBLE
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