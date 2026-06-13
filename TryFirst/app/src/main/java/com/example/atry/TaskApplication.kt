package com.example.atry

import android.app.Application
import com.example.atry.data.TaskDatabase

class TaskApplication : Application() {
    val database: TaskDatabase by lazy { TaskDatabase.getDatabase(this) }
}