package com.example.ekg.data
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity): Long

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    fun findUserByEmail(email: String): Flow<UserEntity?>

    @Query("SELECT * FROM users WHERE id = :id")
    fun findUserById(id: Long): Flow<UserEntity?>
}
