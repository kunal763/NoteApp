package com.example.notetakingapp.Adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.notetakingapp.databinding.NoteLayoutBinding
import com.example.notetakingapp.homeDirections
import com.example.notetakingapp.model.Note
import java.util.*

class MyAdapter:RecyclerView.Adapter<MyViewHolder>() {

    private var diffUtil=object :DiffUtil.ItemCallback<Note>(){
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id==newItem.id && oldItem.noteBody==newItem.noteBody && oldItem.noteTitle==newItem.noteTitle
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem==newItem
        }

    }
    val differ=AsyncListDiffer(this,diffUtil)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            NoteLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentNote=differ.currentList[position]
        holder.itembinding.tvNoteTitle.text=currentNote.noteTitle
        holder.itembinding.tvNoteBody.text=currentNote.noteBody
        var random= Random()
        var color= Color.argb(
            255,
            random.nextInt(256),
            random.nextInt(256),
            random.nextInt(256)
        )
        holder.itembinding.ibColor.setBackgroundColor(color)
        holder.itemView.setOnClickListener {
            val direction=homeDirections.actionHome2ToUpdateNote(currentNote)
            it.findNavController().navigate(direction)

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}
class MyViewHolder(val itembinding:NoteLayoutBinding):RecyclerView.ViewHolder(itembinding.root){

}