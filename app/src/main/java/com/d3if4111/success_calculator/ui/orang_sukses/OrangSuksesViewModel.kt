package com.d3if4111.success_calculator.ui.orang_sukses

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.d3if4111.success_calculator.MainActivity
import com.d3if4111.success_calculator.network.UpdateWorker
import java.util.concurrent.TimeUnit

class OrangSuksesViewModel: ViewModel() {
    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            MainActivity.CHANNEL_ID,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}