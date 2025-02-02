package com.example.ekg.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "my_app_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

object RepositoryProvider {
    private var repo: UserRepository? = null

    fun getRepository(context: Context): UserRepository {
        if (repo == null) {
            val db = AppDatabase.getInstance(context)
            repo = UserRepository(db.userDao())
        }
        return repo!!
    }
}
