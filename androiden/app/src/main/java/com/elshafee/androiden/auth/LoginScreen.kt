package com.elshafee.androiden.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.elshafee.androiden.MainActivity
import com.elshafee.androiden.R
import com.elshafee.androiden.databinding.ActivityLoginScreenBinding
import kotlinx.coroutines.MainScope

class LoginScreen : AppCompatActivity() {
    lateinit var biniding:ActivityLoginScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        biniding = ActivityLoginScreenBinding.inflate(layoutInflater)
        setContentView(biniding.root)
        val sharedPrefrence = getSharedPreferences("authData",0)
        val editor = sharedPrefrence.edit()
        biniding.btnLoginScreen.setOnClickListener {
            var email:String = biniding.etEmail.text.toString()
            var password:String = biniding.etpassword.text.toString()

            val semail = sharedPrefrence.getString("email","")
            val spassword = sharedPrefrence.getString("password", "")

            if (email.isNotEmpty() && password.isNotEmpty()){
                if (email == semail && password == spassword){
                    editor.putBoolean("issigned",true)
                    editor.apply()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this,"Wrong Email or password",Toast.LENGTH_LONG).show()

                }

            }else{
                Toast.makeText(this,"Please enter Your Email and password",Toast.LENGTH_LONG).show()
            }

        }
        biniding.txRegistration.setOnClickListener {
            val intent = Intent(this,SignUpScreen::class.java)
            startActivity(intent)
        }
    }
}