package com.cahya.utsjossjiss.data.model

data class Habit(
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val iconName: String = "",
    val goal: Int = 0,
    var progress: Int = 0
) {
    val isCompleted: Boolean
        get() = progress >= goal
}