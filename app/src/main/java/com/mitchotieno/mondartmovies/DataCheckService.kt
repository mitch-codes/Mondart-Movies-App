package com.mitchotieno.mondartmovies

// DataCheckService.kt
import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlinx.coroutines.*

class DataCheckService : Service() {
    private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private lateinit var prefs: SizePreferences
    private lateinit var repository: DataRepository

    override fun onCreate() {
        super.onCreate()
        prefs = SizePreferences(applicationContext)
        repository = DataRepository()
        // Optionally start as a foreground service if it needs to run reliably for a long time
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startDataCheck()
        return START_STICKY // Service will be restarted if the system kills it
    }

    private fun startDataCheck() {
        // Run this job every 30 minutes (or whatever interval you need)
        scope.launch {
            while (isActive) {
                checkForUpdates()
                delay(30 * 60 * 1000L) // Delay for 30 minutes
            }
        }
    }

    private suspend fun checkForUpdates() {
        val lastSize = prefs.getLastArraySize()
        val currentSize = repository.getArrayLengthFromRemote()

        if (currentSize > lastSize) {
            // New data is available!
            val newItemsCount = currentSize - lastSize
            showNewDataNotification(applicationContext, newItemsCount)

            // IMPORTANT: Update the saved size after the notification is shown
            prefs.saveLastArraySize(currentSize)
        } else if (currentSize > 0) {
            // Case where the service starts for the first time or the array shrinks/stays the same
            // This prevents repeated notifications if the size is the same, but saves the size
            prefs.saveLastArraySize(currentSize)
        }
    }

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel() // Cancel all coroutines when the service is destroyed
    }
}