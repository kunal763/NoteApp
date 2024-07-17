package com.example.notetakingapp.ViewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.notetakingapp.repository.NoteRepo

class NoteViewModelFactory(val app:Application,private val repo: NoteRepo):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
       return NoteViewModel(app,repo) as T
    }

}