package com.mgmtsapp.stoppage

import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.mgmtsapp.stoppage.databinding.ActivityForgotPasswordBinding
import java.util.regex.Pattern

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotPasswordBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()




        //For full screen
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }



        binding.resetBtn.setOnClickListener{
            val email = binding.forPassEmail.text.toString().trim()



            if (email.isNotEmpty()){
                if(Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    binding.forpBar.visibility = View.VISIBLE
                    firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener{
                        if(it.isSuccessful)
                        {
                            startActivity(Intent(this@ForgotPasswordActivity, SignInActivity::class.java))
                            Toast.makeText(this@ForgotPasswordActivity, "Please check your e-mail", Toast.LENGTH_SHORT)
                        }
                        else{
                            Toast.makeText(this@ForgotPasswordActivity, "An error occurred! Please try again", Toast.LENGTH_SHORT)
                        }
                    }
                }else{
                    binding.forPassEmail.setError("Invalid E-mail address")
                    binding.forPassEmail.requestFocus()
                }
            }else{
                binding.forPassEmail.setError("E-mail is required")
                binding.forPassEmail.requestFocus()
            }
        }

    }

    override fun onRestart() {
        super.onRestart()
        binding.forpBar.visibility=View.INVISIBLE
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