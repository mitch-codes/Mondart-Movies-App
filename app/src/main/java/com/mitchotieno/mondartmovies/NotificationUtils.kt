package com.mitchotieno.mondartmovies

// NotificationUtils.kt
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService

const val CHANNEL_ID = "NewDataChannel"
const val NOTIFICATION_ID = 101

fun showNewDataNotification(context: Context, newCount: Int) {
    val manager = context.getSystemService<NotificationManager>() ?: return

    // 1. Create the Notification Channel (required for Android 8.0+)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "New Data Alerts",
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "Alerts for new items available."
        }
        manager.createNotificationChannel(channel)
    }

    // 2. Build the Notification
    val notification = NotificationCompat.Builder(context, CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_notification) // Use your own icon
        .setContentTitle("New Data Available!")
        .setContentText("Found $newCount new items since last check.")
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)
        .build()

    // 3. Display the Notification
    manager.notify(NOTIFICATION_ID, notification)
}