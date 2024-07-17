package com.example.notetakingapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notetakingapp.model.Note


@Database(entities = [Note::class], version = 1)
abstract class NoteDb:RoomDatabase() {
    abstract fun getNoteDao():NoteDao
    //we make companion object so that we only create one instance of the database at runtime so that we can
    companion object{
        @Volatile
        private var instance:NoteDb?=null
        private val LOCK=Any()
        operator fun invoke(context:Context)= instance?: synchronized(LOCK){
            instance?:
            createDb(context).also{
                instance=it
            }
        }

        private fun createDb(context: Context)=
            Room.databaseBuilder(context.applicationContext,NoteDb::class.java,"note_db").build()




    }
}