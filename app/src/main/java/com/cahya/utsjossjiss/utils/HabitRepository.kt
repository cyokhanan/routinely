package com.cahya.utsjossjiss.utils

import android.content.Context
import com.cahya.utsjossjiss.model.Habit

class HabitRepository private constructor(context: Context) {
    private val prefHelper = PreferenceHelper(context)

    companion object {
        @Volatile
        private var instance: HabitRepository? = null

        fun getInstance(context: Context): HabitRepository {
            return instance ?: synchronized(this) {
                instance ?: HabitRepository(context).also { instance = it }
            }
        }
    }

    fun getAllHabits(): List<Habit> {
        return prefHelper.loadHabits()
    }

    fun addHabit(habit: Habit) {
        prefHelper.addHabit(habit)
    }

    fun updateHabitProgress(habitId: String, newProgress: Int) {
        prefHelper.updateHabitProgress(habitId, newProgress)
    }

    fun increaseProgress(habitId: String): Int {
        val habits = getAllHabits()
        val habit = habits.find { it.id == habitId } ?: return 0
        val newProgress = (habit.progress + 1).coerceAtMost(habit.goal)
        updateHabitProgress(habitId, newProgress)
        return newProgress
    }

    fun decreaseProgress(habitId: String): Int {
        val habits = getAllHabits()
        val habit = habits.find { it.id == habitId } ?: return 0
        val newProgress = (habit.progress - 1).coerceAtLeast(0)
        updateHabitProgress(habitId, newProgress)
        return newProgress
    }
}
