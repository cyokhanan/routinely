package com.cahya.utsjossjiss.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cahya.utsjossjiss.R
import com.cahya.utsjossjiss.model.Habit

class HabitAdapter(
    private var habits: List<Habit>,
    private val onPlusClicked: (String) -> Unit,
    private val onMinusClicked: (String) -> Unit
) : RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {

    class HabitViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgIcon: ImageView = view.findViewById(R.id.imgIcon)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
        val tvStatus: TextView = view.findViewById(R.id.tvStatus)
        val tvProgress: TextView = view.findViewById(R.id.tvProgress)
        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
        val btnPlus: Button = view.findViewById(R.id.btnPlus)
        val btnMinus: Button = view.findViewById(R.id.btnMinus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_habit, parent, false)
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        val habit = habits[position]
        holder.tvName.text = habit.name
        holder.tvDescription.text = habit.description

        // Icon Setup using PNG images from drawable
        when (habit.iconName) {
            "Checklist" -> holder.imgIcon.setImageResource(R.drawable.checklist)
            "Food" -> holder.imgIcon.setImageResource(R.drawable.food)
            "Sleep" -> holder.imgIcon.setImageResource(R.drawable.sleep)
            "Walk" -> holder.imgIcon.setImageResource(R.drawable.walk)
            "Water" -> holder.imgIcon.setImageResource(R.drawable.water)
            else -> holder.imgIcon.setImageResource(R.drawable.checklist)
        }

        holder.progressBar.max = habit.goal
        holder.progressBar.progress = habit.progress
        holder.tvProgress.text = "Progress: ${habit.progress} / ${habit.goal}"

        if (habit.isCompleted) {
            holder.tvStatus.text = "Completed"
            holder.tvStatus.setTextColor(android.graphics.Color.GREEN)
        } else {
            holder.tvStatus.text = "In Progress"
            holder.tvStatus.setTextColor(android.graphics.Color.BLUE)
        }

        holder.btnPlus.setOnClickListener { onPlusClicked(habit.id) }
        holder.btnMinus.setOnClickListener { onMinusClicked(habit.id) }
    }

    override fun getItemCount() = habits.size

    fun updateData(newHabits: List<Habit>) {
        habits = newHabits
        notifyDataSetChanged()
    }
}
