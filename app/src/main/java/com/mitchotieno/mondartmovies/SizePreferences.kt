package com.mitchotieno.mondartmovies

// SizePreferences.kt
import android.content.Context

class SizePreferences(context: Context) {
    private val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun getLastArraySize(): Int {
        return prefs.getInt(KEY_LAST_ARRAY_SIZE, 0)
    }

    fun saveLastArraySize(size: Int) {
        prefs.edit().putInt(KEY_LAST_ARRAY_SIZE, size).apply()
    }
}