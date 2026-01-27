package com.interrapidisimo.app.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.interrapidisimo.app.data.db.Dao.TableDao
import com.interrapidisimo.app.data.db.Dao.UserDao
import com.interrapidisimo.app.data.db.entity.TableEntity
import com.interrapidisimo.app.data.db.entity.UserEntity

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