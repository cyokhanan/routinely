package com.cahya.utsjossjiss.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.cahya.utsjossjiss.model.Habit
import com.cahya.utsjossjiss.utils.HabitRepository
import java.util.UUID

class CreateHabitViewModel : ViewModel() {

    fun createHabit(context: Context, name: String, description: String, goal: Int, iconName: String) {
        val repository = HabitRepository.getInstance(context)
        val newHabit = Habit(
            id = UUID.randomUUID().toString(),
            name = name,
            description = description,
            goal = goal,
            iconName = iconName
        )
        repository.addHabit(newHabit)
    }
}
