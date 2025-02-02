package com.example.ekg

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ekg.data.AppDatabase
import com.example.ekg.data.RepositoryProvider
import com.example.ekg.data.UserRepository
import kotlinx.coroutines.launch

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = requireContext()

        val repo = RepositoryProvider.getRepository(requireContext())


        val tvUserName = view.findViewById<TextView>(R.id.tv_profile_info)
        val btnHistory = view.findViewById<Button>(R.id.btn_history)
        val btnLogout = view.findViewById<Button>(R.id.btn_logout)

        lifecycleScope.launch {
            val user = repo.getCurrentUser()
            if (user == null) {
                // На всякий случай, если кто-то сбросил сессию
//                findNavController().navigateUp()
            } else {
                tvUserName.text = "Привет, ${user.name ?: user.email}"
            }
        }

        btnHistory.setOnClickListener {
            Toast.makeText(context, "История ЭКГ (пока не реализована)", Toast.LENGTH_SHORT).show()
        }

        btnLogout.setOnClickListener {
            repo.logout()
            Toast.makeText(context, "Вы вышли из аккаунта", Toast.LENGTH_SHORT).show()
            // Вернёмся назад, т.е. в login
            findNavController().navigateUp()
        }
    }
}
