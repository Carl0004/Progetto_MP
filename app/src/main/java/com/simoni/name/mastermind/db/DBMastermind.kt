package com.simoni.name.mastermind.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Game::class], version = 3)
abstract class DBMastermind : RoomDatabase(){
    abstract fun daoGameHistory() : MastermindDao

    companion object{
        @Volatile
        private var INSTANCE: DBMastermind? = null

        fun getInstance(context: Context): DBMastermind{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DBMastermind::class.java,
                    "game_history_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}