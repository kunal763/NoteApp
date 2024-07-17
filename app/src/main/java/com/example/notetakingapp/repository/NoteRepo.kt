package com.example.notetakingapp.repository

import com.example.notetakingapp.database.NoteDb
import com.example.notetakingapp.model.Note

class NoteRepo(private val db:NoteDb) {

    suspend fun insertNote(note:Note)=db.getNoteDao().insert(note)
    suspend fun updateNote(note:Note)=db.getNoteDao().update(note)
    suspend fun deleteNote(note:Note)=db.getNoteDao().delete(note)
    fun getAllNotes()=db.getNoteDao().getAllNotes()
    fun searchNote(query:String?)=db.getNoteDao().searchNote(query)

}