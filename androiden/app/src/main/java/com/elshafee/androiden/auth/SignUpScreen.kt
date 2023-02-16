package com.elshafee.androiden.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.elshafee.androiden.HomeScreen
import com.elshafee.androiden.MainActivity
import com.elshafee.androiden.R
import com.elshafee.androiden.auth.model.ProfileInformation
import com.elshafee.androiden.databinding.ActivitySignUpScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpScreen : AppCompatActivity() {
    lateinit var binding:ActivitySignUpScreenBinding
    lateinit var auth: FirebaseAuth
    private val profileInformationCollectionRef = Firebase.firestore.collection("ProfileInformation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = FirebaseAuth.getInstance()
        val sharedPrefrence = getSharedPreferences("authData",0)
        val editor = sharedPrefrence.edit()

        binding.btnSignUpScreen.setOnClickListener {
            val profileInformation = getProfileInformation()

            if (profileInformation != null){
                saveProfileInformation(profileInformation)
            }

            val email = profileInformation.emailAdress
            val password = profileInformation.password


            if (email.isNotEmpty() && password.isNotEmpty()){

                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                    if (it.isSuccessful){
                        val intent =  Intent(this,HomeScreen::class.java)
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

    private fun getProfileInformation() :ProfileInformation{
        var email = binding.etEmail.text.toString()
        var password = binding.etpassword.text.toString()
        var fullname = binding.etUserName.text.toString()
        var phone = binding.etPhone.text.toString()

        return ProfileInformation(fullname,email,phone,password)
    }
    private fun saveProfileInformation(profileInformation: ProfileInformation){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                profileInformationCollectionRef.add(profileInformation)
                withContext(Dispatchers.Main){
                    Toast.makeText(this@SignUpScreen,"Data Saved Successfully",Toast.LENGTH_LONG).show()
                }
            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(this@SignUpScreen,e.message,Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}