package com.example.notetakingapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.notetakingapp.model.Note
import com.example.notetakingapp.repository.NoteRepo
import kotlinx.coroutines.launch

class NoteViewModel(val app:Application, private val repo:NoteRepo ):AndroidViewModel(app){

    fun insertData(note: Note)=viewModelScope.launch {
        repo.insertNote(note)
    }
    fun updateData(note: Note)=viewModelScope.launch {
        repo.updateNote(note)
    }
     fun deleteData(note: Note)=viewModelScope.launch {
        repo.deleteNote(note)
    }
    fun getAllNotes()= repo.getAllNotes()

    fun searchNote(query:String?)= repo.searchNote(query)


}