package com.elshafee.androiden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.elshafee.androiden.ui.SplashScreen


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        val text = findViewById<TextView>(R.id.textView)
        val etname = findViewById<EditText>(R.id.etName)
        val check =findViewById<CheckBox>(R.id.cbDuration)



        button.setOnClickListener {
            val name  = etname.text.toString()
            text.text = name
            val isChecked = check.isChecked
            if (isChecked){
                Toast.makeText(this,"$name",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this,"$name",Toast.LENGTH_SHORT).show()
            }

            val intent = Intent(this, SplashScreen::class.java)
            startActivity(intent)
            finish()


        }

    }
}
