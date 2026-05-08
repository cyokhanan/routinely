package com.cahya.utsjossjiss.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cahya.utsjossjiss.R
import com.cahya.utsjossjiss.viewmodel.CreateHabitViewModel

class CreateHabitFragment : Fragment() {

    private lateinit var viewModel: CreateHabitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_habit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CreateHabitViewModel::class.java)

        val etName = view.findViewById<EditText>(R.id.etName)
        val etDescription = view.findViewById<EditText>(R.id.etDescription)
        val etGoal = view.findViewById<EditText>(R.id.etGoal)
        val spinnerIcon = view.findViewById<Spinner>(R.id.spinnerIcon)
        val btnCreate = view.findViewById<Button>(R.id.btnCreate)

        // Setup Spinner options with the new PNG icon names
        val iconOptions = arrayOf("Checklist", "Food", "Sleep", "Walk", "Water")
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, iconOptions)
        spinnerIcon.adapter = spinnerAdapter

        btnCreate.setOnClickListener {
            val name = etName.text.toString()
            val desc = etDescription.text.toString()
            val goalStr = etGoal.text.toString()
            val selectedIcon = spinnerIcon.selectedItem.toString()

            if (name.isNotEmpty() && desc.isNotEmpty() && goalStr.isNotEmpty()) {
                val goal = goalStr.toIntOrNull()
                if (goal != null && goal > 0) {
                    viewModel.createHabit(requireContext(), name, desc, goal, selectedIcon)
                    Toast.makeText(requireContext(), "Habit Berhasil Dibuat", Toast.LENGTH_SHORT).show()
                    findNavController().navigateUp()
                } else {
                    Toast.makeText(requireContext(), "Goal harus berupa angka > 0", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
