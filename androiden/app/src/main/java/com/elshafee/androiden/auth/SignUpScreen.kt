package com.elshafee.androiden.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.elshafee.androiden.MainActivity
import com.elshafee.androiden.R
import com.elshafee.androiden.databinding.ActivitySignUpScreenBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpScreen : AppCompatActivity() {
    lateinit var binding:ActivitySignUpScreenBinding
    lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        val sharedPrefrence = getSharedPreferences("authData",0)
        val editor = sharedPrefrence.edit()
        binding.btnSignUpScreen.setOnClickListener {
            var email = binding.etEmail.text.toString()
            var password = binding.etpassword.text.toString()
            var fullname = binding.etUserName.text.toString()
            var phone = binding.etPhone.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){

                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                    if (it.isSuccessful){
                        val intent =  Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "Authentiction Error",Toast.LENGTH_LONG).show()
                    }
                }
//                editor.apply{
//                    putString("email",email)
//                    putString("password",password)
//                    putBoolean("issigned", true)
//                    apply()
//                }
//                val intent =  Intent(this,MainActivity::class.java)
//                startActivity(intent)
            }
        }
    }
}