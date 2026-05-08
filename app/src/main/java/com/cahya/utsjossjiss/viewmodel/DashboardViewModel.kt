package com.cahya.utsjossjiss.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cahya.utsjossjiss.model.Habit
import com.cahya.utsjossjiss.utils.HabitRepository

class DashboardViewModel : ViewModel() {
    val habits = MutableLiveData<List<Habit>>()
    private var repository: HabitRepository? = null

    fun init(context: Context) {
        if (repository == null) {
            repository = HabitRepository.getInstance(context)
        }
    }

    fun loadHabits() {
        habits.value = repository?.getAllHabits() ?: emptyList()
    }

    fun addProgress(habitId: String) {
        repository?.increaseProgress(habitId)
        loadHabits()
    }

    fun decreaseProgress(habitId: String) {
        repository?.decreaseProgress(habitId)
        loadHabits()
    }
}
