package com.elshafee.androiden.firebasenotification.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.elshafee.androiden.R
import com.elshafee.androiden.databinding.ActivityCloudMessagingBinding
import com.elshafee.androiden.firebasenotification.model.NotificationData
import com.elshafee.androiden.firebasenotification.model.PushNotification
import com.elshafee.androiden.firebasenotification.services.FirebaseService
import com.elshafee.androiden.firebasenotification.services.RetrofitInstance
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val TOPIC ="/topics/myTopic"
class CloudMessaging : AppCompatActivity() {
    val TAG ="CloudMessagingApp"

    private lateinit var binding:ActivityCloudMessagingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityCloudMessagingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseService.sharedPref = getSharedPreferences("sharedPrefFile", Context.MODE_PRIVATE)
        val messaging = FirebaseMessaging.getInstance()
        messaging.token.addOnSuccessListener {
            FirebaseService.token = it
            binding.etNotificationToken.setText(it)
        }

        messaging.subscribeToTopic(TOPIC)

        binding.btnSendNotification.setOnClickListener {
            val notificationTitle = binding.etNotificationTitle.text.toString()
            val notificationMessage = binding.etNotificationMessage.text.toString()
            val recipentToken = binding.etNotificationToken.text.toString()
            if (notificationTitle.isNotEmpty() && notificationMessage.isNotEmpty() && recipentToken.isNotEmpty()){
                PushNotification(NotificationData(notificationTitle,notificationMessage),recipentToken).also {
                    sendNotification(it)
                }
            }
        }
    }

    private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = RetrofitInstance.api.postNotification(notification)
            if (response.isSuccessful){
                Log.d(TAG,"Sent Successfully")
            }else{
                Log.d(TAG,response.errorBody().toString())

            }

        }catch (e:Exception){

            Log.d(TAG,e.message.toString())


        }
    }
}