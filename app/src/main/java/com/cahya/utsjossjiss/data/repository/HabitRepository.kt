package com.cahya.utsjossjiss.data.repository

import com.cahya.utsjossjiss.data.local.PreferenceHelper
import com.cahya.utsjossjiss.data.model.Habit

class HabitRepository(private val prefHelper: PreferenceHelper) {

    fun getAllHabits(): List<Habit> {
        return prefHelper.loadHabits()
    }

    fun addHabit(habit: Habit) {
        prefHelper.addHabit(habit)
    }

    fun increaseProgress(habitId: String, currentProgress: Int, targetGoal: Int): Int {
        val newProgress = (currentProgress + 1).coerceAtMost(targetGoal)
        prefHelper.updateHabitProgress(habitId, newProgress)
        return newProgress
    }

    fun decreaseProgress(habitId: String, currentProgress: Int): Int {
        val newProgress = (currentProgress - 1).coerceAtLeast(0)
        prefHelper.updateHabitProgress(habitId, newProgress)
        return newProgress
    }
}