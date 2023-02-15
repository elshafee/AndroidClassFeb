package com.elshafee.androiden.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.elshafee.androiden.HomeScreen
import com.elshafee.androiden.MainActivity
import com.elshafee.androiden.auth.LoginScreen
import com.elshafee.androiden.databinding.ActivitySplashScreenBinding
import com.elshafee.androiden.todlistapp.TodoListActivity
import com.elshafee.androiden.shoppingitemlist.ui.ShoppingItemListActivity
import com.google.firebase.auth.FirebaseAuth

class SplashScreen : AppCompatActivity() {
    lateinit var binding:ActivitySplashScreenBinding
    lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        supportActionBar?.hide()
        val sharedPrefrence = getSharedPreferences("authData",0)
        val isSigned:Boolean = sharedPrefrence.getBoolean("issigned",false)
        val currentUser = auth.currentUser
        Handler().postDelayed({

            if (currentUser !=null){
                val intent = Intent(this, HomeScreen::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this, LoginScreen::class.java)
                startActivity(intent)
                finish()
            }

        },3000)



    }
}