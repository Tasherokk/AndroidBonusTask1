package com.example.ekg

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.ekg.data.AppDatabase
import com.example.ekg.data.RepositoryProvider
import com.example.ekg.data.UserRepository
import kotlinx.coroutines.launch

class ProfileCheckerFragment : Fragment(R.layout.fragment_profile_checker) {
    // Можно сделать пустой layout или прогресс-бар

    private lateinit var repo: UserRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repo = RepositoryProvider.getRepository(requireContext())


        // При загрузке - сразу проверяем статус
        lifecycleScope.launch {
            val currentUser = repo.getCurrentUser()
            if (currentUser == null) {
                // Не залогинен -> на экран логина
                findNavController().navigate(R.id.action_profileCheckerFragment_to_loginFragment)
            } else {
                // Залогинен -> сразу в профиль
                findNavController().navigate(R.id.action_profileCheckerFragment_to_profileFragment)
            }
        }
    }
}
