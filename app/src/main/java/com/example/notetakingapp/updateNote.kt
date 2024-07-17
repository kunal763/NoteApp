package com.example.notetakingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.notetakingapp.Adapter.MyAdapter
import com.example.notetakingapp.ViewModel.NoteViewModel
import com.example.notetakingapp.databinding.FragmentNewNoteBinding
import com.example.notetakingapp.databinding.FragmentUpdateNoteBinding
import com.example.notetakingapp.model.Note
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class updateNote : Fragment() {

    private var _binding: FragmentUpdateNoteBinding? = null
    private val binding get() = _binding!!
    private lateinit var notesViewModel: NoteViewModel
    private lateinit var notesAdapter: MyAdapter
    private lateinit var currentNote: Note
    private val args: updateNoteArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        super.onViewCreated(view, savedInstanceState)
        notesViewModel = (activity as MainActivity).noteViewModel
        currentNote = args.note!!
        binding.etNoteTitleUpdate.setText(currentNote.noteTitle)
        binding.etNoteBodyUpdate.setText(currentNote.noteBody)
        //if the user update the note
        binding.fabDone.setOnClickListener {
            val title = binding.etNoteTitleUpdate.text.toString()
            val body = binding.etNoteBodyUpdate.text.toString()
            if (title.isNotEmpty()) {
                val note = Note(currentNote.id, title, body)
                notesViewModel.updateData(note)
                view.findNavController().navigate(R.id.action_updateNote_to_home2)

            } else {
                Toast.makeText(context, "Enter note Title", Toast.LENGTH_SHORT).show()
            }


        }

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
       menu.clear()
        inflater.inflate(R.menu.menu_update_note,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_delete->{
                deleteNote()
            }
        }
        
        return super.onOptionsItemSelected(item)
    }

    private fun deleteNote() {
        activity?.let {
            AlertDialog.Builder(it).apply {
                setTitle("Delete Note")
                setMessage("You Want to delete this note")
                setPositiveButton("Delete"){
                    _,_->notesViewModel.deleteData(currentNote)
                    view?.findNavController()?.navigate(R.id.action_updateNote_to_home2)
                }
                setNegativeButton("Cancel",null)
            }.create().show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}