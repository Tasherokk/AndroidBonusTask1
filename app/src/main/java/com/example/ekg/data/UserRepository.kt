package com.example.ekg.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class UserRepository(private val userDao: UserDao) {

    var currentUserId: Long? = null

    suspend fun register(email: String, password: String, name: String?): Long? {
        val existingUser = userDao.findUserByEmail(email).firstOrNull() // Получаем первый элемент из потока
        if (existingUser != null) {
            return null
        }
        val newUser = UserEntity(email = email, password = password, name = name)
        return userDao.insertUser(newUser)
    }

    suspend fun login(email: String, password: String): Boolean {
        val user = userDao.findUserByEmail(email).firstOrNull() // Получаем UserEntity из Flow
        if (user != null && user.password == password) {
            currentUserId = user.id
            return true
        }
        return false
    }

    suspend fun getCurrentUser(): UserEntity? {
        val id = currentUserId ?: return null
        return userDao.findUserById(id).firstOrNull()
    }

    fun logout() {
        currentUserId = null
    }
}
