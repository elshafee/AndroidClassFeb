package com.elshafee.androiden.firebasenotification.model

data class PushNotification(
    val data:NotificationData,
    val to:String
) {
}