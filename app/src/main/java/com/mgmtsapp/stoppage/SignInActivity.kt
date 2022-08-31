package com.mgmtsapp.stoppage

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.mgmtsapp.stoppage.databinding.ActivitySignInBinding


class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()

        val message = intent.getStringExtra("EXTRA_MESSAGE")

        if(message == "Passenger"){
            binding.googleSign.visibility = View.VISIBLE
            binding.havensText.visibility = View.VISIBLE
            binding.signinRegBtn.visibility = View.VISIBLE
            binding.signinusingText.visibility = View.VISIBLE
        }else{
            binding.googleSign.visibility = View.INVISIBLE
            binding.havensText.visibility = View.INVISIBLE
            binding.signinRegBtn.visibility = View.INVISIBLE
            binding.signinusingText.visibility = View.INVISIBLE
        }

        //google sign in
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("1068484491341-jv6soi836ovo8jpoo0bn02c9i12tf2i1.apps.googleusercontent.com")
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)


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

        binding.signinRegBtn.setOnClickListener {
            binding.signinRegBtn.setTextColor(Color.RED)
            startActivity(Intent(this@SignInActivity, RegisterActivity::class.java))
        }

        binding.forgetPassBtn.setOnClickListener {
            binding.forgetPassBtn.setTextColor(Color.BLACK)
            startActivity(Intent(this@SignInActivity, ForgotPasswordActivity::class.java))
        }


        binding.signinBtn.setOnClickListener {

            val signEmail = binding.signinEmailText.text.toString().trim()
            val signPass = binding.signinPassText.text.toString().trim()
            if (signEmail.isNotEmpty() && signPass.isNotEmpty()) {
                //if email and pass is provided
                if (Patterns.EMAIL_ADDRESS.matcher(signEmail).matches()) {
                    binding.signpBar.visibility = View.VISIBLE
                    firebaseAuth.signInWithEmailAndPassword(signEmail, signPass)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                startActivity(Intent(this@SignInActivity, HomeActivity::class.java))
                                Toast.makeText(this, "Successfully Logged in", Toast.LENGTH_SHORT)
                                    .show()
                                binding.signpBar.visibility = View.INVISIBLE
                                finish()
                            } else {

                                binding.signpBar.visibility = View.INVISIBLE
                                Toast.makeText(this, "Wrong Credentials", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    binding.signinEmailText.error = "Invalid E-mail"
                }
            } else {
                //If any field is empty
                if (signEmail.isEmpty() && signPass.isEmpty()) {
                    binding.signinEmailText.error = "Empty field"
                    binding.signinPassText.error = "Empty field"
                    binding.signinEmailText.requestFocus()
                } else if (signPass.isEmpty()) {
                    binding.signinPassText.error = "Empty field"
                    binding.signinEmailText.requestFocus()
                } else if (signEmail.isEmpty()) {
                    binding.signinEmailText.error = "Empty field"
                    binding.signinEmailText.requestFocus()

                }
            }
        }


        binding.googleSign.setOnClickListener{
            signIn()
        }

    }
    companion object{
        val RC_SIGN_IN = 1001
        val EXTRA_NAME = "EXTRA_NAME"
    }


    override fun onRestart() {
        super.onRestart()
        binding.signinRegBtn.setTextColor(Color.parseColor("#9A62CC"))
        binding.forgetPassBtn.setTextColor(Color.RED)
        binding.signpBar.visibility = View.INVISIBLE
        binding.googleSign.visibility = View.INVISIBLE
        binding.havensText.visibility = View.INVISIBLE
        binding.signinRegBtn.visibility = View.INVISIBLE
        binding.signinusingText.visibility = View.INVISIBLE

    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = firebaseAuth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    updateUI(null)
                }
            }
    }
    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(applicationContext, HomeActivity::class.java)
            intent.putExtra(EXTRA_NAME, user.displayName)
            startActivity(intent)
            finish()
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