package com.example.notetakingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.notetakingapp.Adapter.MyAdapter
import com.example.notetakingapp.ViewModel.NoteViewModel
import com.example.notetakingapp.databinding.FragmentNewNoteBinding
import com.example.notetakingapp.model.Note
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class newNote : Fragment(R.layout.fragment_new_note) {
    private var _binding: FragmentNewNoteBinding?=null
    private val binding  get() =_binding!!
    private lateinit var notesViewModel: NoteViewModel
    private lateinit var noteAdapter: MyAdapter
    private lateinit var mView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentNewNoteBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel=(activity as MainActivity).noteViewModel
        mView=view
    }
    private fun saveNote(view: View){
                val noteTitle = binding.etNoteTitle.text.toString().trim()
                val noteBody = binding.etNoteBody.text.toString().trim()
                if (noteTitle.isNotEmpty()) {
                    val note = Note(0, noteTitle, noteBody)
                    notesViewModel.insertData(note = note)
                    Toast.makeText(mView.context, "Note Saved Succesfully", Toast.LENGTH_SHORT)
                        .show()
                    view.findNavController().navigate(R.id.action_newNote_to_home2)
                } else {
                    Toast.makeText(mView.context, "Please enter a Title", Toast.LENGTH_SHORT).show()
                }
            }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_new_note,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_save->{
                saveNote(mView)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}