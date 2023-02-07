package com.elshafee.androiden.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.elshafee.androiden.MainActivity
import com.elshafee.androiden.R
import com.elshafee.androiden.databinding.ActivitySignUpScreenBinding

class SignUpScreen : AppCompatActivity() {
    lateinit var binding:ActivitySignUpScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPrefrence = getSharedPreferences("authData",0)
        val editor = sharedPrefrence.edit()
        binding.btnSignUpScreen.setOnClickListener {
            var email = binding.etEmail.text.toString()
            var password = binding.etpassword.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()){
                editor.apply{
                    putString("email",email)
                    putString("password",password)
                    putBoolean("issigned", true)
                    apply()
                }
                val intent =  Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}