package com.cahya.utsjossjiss.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.cahya.utsjossjiss.R
import com.cahya.utsjossjiss.viewmodel.DashboardViewModel

class DashboardFragment : Fragment() {
    private lateinit var viewModel: DashboardViewModel
    private lateinit var habitAdapter: HabitAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[DashboardViewModel::class.java]
        viewModel.init(requireContext())

        val rvHabits = view.findViewById<RecyclerView>(R.id.rvHabits)
        val fabAddHabit = view.findViewById<FloatingActionButton>(R.id.fabAddHabit)
        val tvEmpty = view.findViewById<TextView>(R.id.tvEmpty)

        habitAdapter = HabitAdapter(
            emptyList(),
            onPlusClicked = { habitId ->
                viewModel.addProgress(habitId)
            },
            onMinusClicked = { habitId ->
                viewModel.decreaseProgress(habitId)
            }
        )
        rvHabits.layoutManager = LinearLayoutManager(requireContext())
        rvHabits.adapter = habitAdapter

        viewModel.habits.observe(viewLifecycleOwner, Observer { habits ->
            habitAdapter.updateData(habits)
            if (habits.isEmpty()) {
                tvEmpty.visibility = View.VISIBLE
                rvHabits.visibility = View.GONE
            } else {
                tvEmpty.visibility = View.GONE
                rvHabits.visibility = View.VISIBLE
            }
        })

        fabAddHabit.setOnClickListener {
            findNavController().navigate(
                R.id.action_dashboardFragment_to_createHabitFragment
            )
        }
        viewModel.loadHabits()
    }
}