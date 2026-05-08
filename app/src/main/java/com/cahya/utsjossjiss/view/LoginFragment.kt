package com.cahya.utsjossjiss.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.cahya.utsjossjiss.R
import com.cahya.utsjossjiss.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        val etUsername = view.findViewById<EditText>(R.id.etUsername)
        val etPassword = view.findViewById<EditText>(R.id.etPassword)
        val btnLogin = view.findViewById<Button>(R.id.btnLogin)
        val tvErrorMessage = view.findViewById<TextView>(R.id.tvErrorMessage)

        viewModel.loginStatus.observe(viewLifecycleOwner, Observer { isSuccess ->
            if (isSuccess) {
                tvErrorMessage.visibility = View.GONE
                Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_dashboardFragment)
            } else {
                tvErrorMessage.visibility = View.VISIBLE
            }
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMsg ->
            tvErrorMessage.text = errorMsg
        })

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            viewModel.attemptLogin(username, password)
        }
    }
}
