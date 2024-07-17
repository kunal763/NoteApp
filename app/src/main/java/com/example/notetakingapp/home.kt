package com.example.notetakingapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.*
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notetakingapp.Adapter.MyAdapter
import com.example.notetakingapp.ViewModel.NoteViewModel
import com.example.notetakingapp.databinding.FragmentHomeBinding
import com.example.notetakingapp.model.Note

class home : Fragment(R.layout.fragment_home),SearchView.OnQueryTextListener {

    private var binding: FragmentHomeBinding?=null
    private val _binding  get() =binding!!
    private lateinit var notesViewModel: NoteViewModel
    private lateinit var noteAdapter:MyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        notesViewModel=(activity as MainActivity).noteViewModel
        setUpRecyclerView()
        _binding.fabAddNote.setOnClickListener{
            it.findNavController().navigate(R.id.action_home2_to_newNote)
        }
    }

    private fun setUpRecyclerView() {
        noteAdapter=MyAdapter()
        val nameObserver = Observer<List<Note>> { note->
            // Update the UI, in this case, a TextView.
            noteAdapter.differ.submitList(note)
            updateUI(note)

        }
        _binding.recyclerView.apply {
            layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter=noteAdapter
        }
        activity?.let {
            notesViewModel.getAllNotes().observe(
                viewLifecycleOwner,nameObserver)


        }

    }
    private fun updateUI(note:List<Note>?){
        if (note != null) {
            if(note.isNotEmpty()){
                _binding.cardView.visibility=View.GONE
                _binding.recyclerView.visibility=View.VISIBLE
            }
            else{
                _binding.cardView.visibility=View.VISIBLE
                _binding.recyclerView.visibility=View.GONE
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.home_menu,menu)
        val mMenuSearch=menu.findItem(R.id.menu_search).actionView as SearchView
        mMenuSearch.isSubmitButtonEnabled=false
        mMenuSearch.setOnQueryTextListener(this)

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
      //  searchNote(query)
        return false
    }



    override fun onQueryTextChange(newText: String?): Boolean {
        if(newText!=null){
            searchNote(newText)
        }
        return true
    }
    private fun searchNote(query: String?) {
            val searchQuery="%$query"
            notesViewModel.searchNote(searchQuery).observe(
                this,{list-> noteAdapter.differ.submitList(list)}
            )
    }

    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }



}