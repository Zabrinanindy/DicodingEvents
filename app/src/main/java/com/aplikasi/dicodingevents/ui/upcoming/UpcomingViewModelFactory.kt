package com.aplikasi.dicodingevents.ui.upcoming

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aplikasi.dicodingevents.data.remote.repository.EventRepository
import com.aplikasi.dicodingevents.data.di.Injection

class UpcomingViewModelFactory private constructor(
    private val eventRepository: EventRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(UpcomingViewModel::class.java) -> {
                UpcomingViewModel(eventRepository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }

    companion object {
        @Volatile
        private var instance: UpcomingViewModelFactory? = null

        fun getInstance(context: Context): UpcomingViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: UpcomingViewModelFactory(
                    Injection.provideRepository(context)
                ).also { instance = it }
            }
    }
}