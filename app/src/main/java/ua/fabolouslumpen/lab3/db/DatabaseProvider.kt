package ua.fabolouslumpen.lab3.db

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    private var db: AppDatabase? = null

    val database: AppDatabase
        get() {
            if (db == null) {
                throw IllegalStateException("Database has not been initialized!")
            }
            return db!!
        }

    fun initialize(context: Context) {
        if (db == null) {
            db = Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "music_database"
            ).build()
        }
    }
}