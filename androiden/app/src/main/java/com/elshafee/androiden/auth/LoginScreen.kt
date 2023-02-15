package com.elshafee.androiden.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.elshafee.androiden.HomeScreen
import com.elshafee.androiden.databinding.ActivityLoginScreenBinding
import com.google.firebase.auth.FirebaseAuth

class LoginScreen : AppCompatActivity() {
    lateinit var biniding: ActivityLoginScreenBinding
    private lateinit var auth: FirebaseAuth

//    override fun onStart() {
//        super.onStart()
//        val currentUser = auth.currentUser
//        if (currentUser != null){
//            val intent = Intent(this, HomeScreen::class.java)
//            startActivity(intent)
//            finish()
//        }
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biniding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(biniding.root)
        auth = FirebaseAuth.getInstance()
        val sharedPrefrence = getSharedPreferences("authData", 0)
        val editor = sharedPrefrence.edit()
        biniding.btnLoginScreen.setOnClickListener {
            var email: String = biniding.etEmail.text.toString()
            var password: String = biniding.etpassword.text.toString()

            val semail = sharedPrefrence.getString("email", "")
            val spassword = sharedPrefrence.getString("password", "")

            if (email.isNotEmpty() && password.isNotEmpty()) {

                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, HomeScreen::class.java)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this,"Wrong Email or password",Toast.LENGTH_LONG).show()
                    }
                }
//                if (email == semail && password == spassword){
//                    editor.putBoolean("issigned",true)
//                    editor.apply()
//                    val intent = Intent(this, HomeScreen::class.java)
//                    startActivity(intent)
//                    finish()
//                }else{
//                    Toast.makeText(this,"Wrong Email or password",Toast.LENGTH_LONG).show()
//
//                }

            } else {
                Toast.makeText(this, "Please enter Your Email and password", Toast.LENGTH_LONG)
                    .show()
            }

        }
        biniding.txRegistration.setOnClickListener {
            val intent = Intent(this, SignUpScreen::class.java)
            startActivity(intent)
        }
    }
}