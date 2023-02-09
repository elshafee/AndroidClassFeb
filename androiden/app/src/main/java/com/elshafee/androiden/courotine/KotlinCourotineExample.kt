package com.elshafee.androiden.courotine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.elshafee.androiden.R
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class KotlinCourotineExample : AppCompatActivity() {

    val TAG = "CoroutineExample"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_courotine_example)

        GlobalScope.async(Dispatchers.IO){
            val time = measureTimeMillis {
                val answer1 =async{networkCall1()}
                val answer2 =async{networkCall2()}

                Log.d(TAG,"Answer 1 is ${answer1.await()}")
                Log.d(TAG,"Answer 2 is ${answer2.await()}")
                withContext(Dispatchers.Main){
                    Toast.makeText(this@KotlinCourotineExample, "$answer1",Toast.LENGTH_SHORT).show()
                }

            }
            Log.d(TAG, "Reaquest took $time ms")

            withContext(Dispatchers.Main){
                Toast.makeText(this@KotlinCourotineExample, "$time ms",Toast.LENGTH_SHORT).show()

            }
        }


    }

    suspend fun networkCall1():String{
        delay(3000L)
        return "Answer 1"
    }

    suspend fun networkCall2():String{
        delay(3000L)
        return "Answer 2"
    }
}