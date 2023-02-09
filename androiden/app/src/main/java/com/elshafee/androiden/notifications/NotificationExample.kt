package com.elshafee.androiden.notifications

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.elshafee.androiden.R
import com.elshafee.androiden.databinding.ActivityNotificationExampleBinding

class NotificationExample : AppCompatActivity() {
    val CHANNEL_ID = "gdscEventsID"
    val CHANNEL_NAME = "gdscEventsNAME"
    val NOTIFICATION_ID = 0
    lateinit var binding: ActivityNotificationExampleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationExampleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNotificationChannel()
        val intent = Intent(this, NotificationExample::class.java)
        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }


        val eventTicketNotification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Your Ticket")
            .setContentText("You are invited to attend our event")
            .setSmallIcon(R.drawable.android_icon)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        val eventManager = NotificationManagerCompat.from(this)

        binding.btnShowNotification.setOnClickListener {

            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.

            }
            eventManager.notify(NOTIFICATION_ID, eventTicketNotification)

        }


    }

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
        ) {

            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                lightColor = Color.RED
                enableLights(true)
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}