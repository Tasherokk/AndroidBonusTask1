package com.example.ekg

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ekg.data.AppDatabase
import com.example.ekg.data.RepositoryProvider
import com.example.ekg.data.UserRepository
import kotlinx.coroutines.launch

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var repo: UserRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val etEmail = view.findViewById<EditText>(R.id.et_email)
        val etPassword = view.findViewById<EditText>(R.id.et_password)
        val etName = view.findViewById<EditText>(R.id.et_name)
        val btnRegister = view.findViewById<Button>(R.id.btn_register)

        val repo = RepositoryProvider.getRepository(requireContext())


        btnRegister.setOnClickListener {
            val email = etEmail.text.toString()
            val pass = etPassword.text.toString()
            val name = etName.text.toString()

            lifecycleScope.launch {
                val result = repo.register(email, pass, name)
                if (result != null) {
                    Toast.makeText(requireContext(), "Регистрация успешна!", Toast.LENGTH_SHORT).show()
                    // Возвращаемся на Login (или сразу логин?
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                } else {
                    Toast.makeText(requireContext(), "Пользователь с таким email уже есть", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
