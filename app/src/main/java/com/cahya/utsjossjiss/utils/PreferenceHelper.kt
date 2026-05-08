package com.cahya.utsjossjiss.utils

import android.content.Context
import android.content.SharedPreferences
import com.cahya.utsjossjiss.model.Habit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PreferenceHelper(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("habit_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val HABITS_KEY = "habits_list"

    fun saveHabits(habits: List<Habit>) {
        val json = gson.toJson(habits)
        prefs.edit().putString(HABITS_KEY, json).apply()
    }

    fun loadHabits(): List<Habit> {
        val json = prefs.getString(HABITS_KEY, null)
        if (json == null) return emptyList()
        val type = object : TypeToken<List<Habit>>() {}.type
        return gson.fromJson(json, type)
    }

    fun addHabit(habit: Habit) {
        val habits = loadHabits().toMutableList()
        habits.add(habit)
        saveHabits(habits)
    }

    fun updateHabitProgress(habitId: String, newProgress: Int) {
        val habits = loadHabits().toMutableList()
        val index = habits.indexOfFirst { it.id == habitId }
        if (index != -1) {
            habits[index] = habits[index].copy(progress = newProgress)
            saveHabits(habits)
        }
    }
}
