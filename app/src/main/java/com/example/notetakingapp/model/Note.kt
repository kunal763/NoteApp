package com.example.notetakingapp.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
@Entity("Note")
@Parcelize
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val noteTitle:String,
    val noteBody:String

):Parcelable
