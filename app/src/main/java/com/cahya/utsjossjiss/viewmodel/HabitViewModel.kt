package com.cahya.utsjossjiss.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cahya.utsjossjiss.data.model.Habit
import com.cahya.utsjossjiss.data.repository.HabitRepository

class HabitViewModel(private val repository: HabitRepository) : ViewModel() {

    private val _habitList = MutableLiveData<List<Habit>>()
    val habitList: LiveData<List<Habit>> = _habitList

    fun refreshHabits() {
        _habitList.value = repository.getAllHabits()
    }

    fun createNewHabit(habit: Habit) {
        repository.addHabit(habit)
        refreshHabits()
    }

    fun addProgress(habitId: String, currentProgress: Int, goal: Int) {
        repository.increaseProgress(habitId, currentProgress, goal)
        refreshHabits()
    }

    fun subtractProgress(habitId: String, currentProgress: Int) {
        repository.decreaseProgress(habitId, currentProgress)
        refreshHabits()
    }

    fun getStatusText(habit: Habit): String {
        return if (habit.isCompleted) "Completed" else "In Progress"
    }
}