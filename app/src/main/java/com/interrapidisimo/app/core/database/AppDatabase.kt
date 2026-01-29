package com.interrapidisimo.app.core.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.interrapidisimo.app.Tables.data.db.TableDao
import com.interrapidisimo.app.login.data.db.UserDao
import com.interrapidisimo.app.core.database.entity.TableEntity
import com.interrapidisimo.app.core.database.entity.UserEntity

@Database(entities = [UserEntity::class, TableEntity::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract  fun  tablaDao(): TableDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun get(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "interrapidisimo_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}