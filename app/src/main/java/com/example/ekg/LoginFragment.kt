package com.example.ekg

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ekg.data.RepositoryProvider
import com.example.ekg.data.UserRepository
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var repo: UserRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val etEmail = view.findViewById<EditText>(R.id.et_email)
        val etPassword = view.findViewById<EditText>(R.id.et_password)
        val btnLogin = view.findViewById<Button>(R.id.btn_login)
        val btnGoRegister = view.findViewById<Button>(R.id.btn_go_register)

        val repo = RepositoryProvider.getRepository(requireContext())


        btnLogin.setOnClickListener {
            val email = etEmail.text.toString()
            val pass = etPassword.text.toString()

            lifecycleScope.launch {
                val success = repo.login(email, pass)
                if (success) {
                    Toast.makeText(requireContext(), "Успешный вход!", Toast.LENGTH_SHORT).show()
                    // Переходим на profileFragment
                    findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
                } else {
                    Toast.makeText(requireContext(), "Неверные данные", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnGoRegister.setOnClickListener {
            // Переходим на экран регистрации
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }
}
