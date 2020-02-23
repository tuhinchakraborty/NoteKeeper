package com.example.notekeeper.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notekeeper.R
import com.example.notekeeper.adapters.views.ViewHolder
import com.example.notekeeper.models.Note

class NoteRecyclerAdapter(
    private val context: Context,
    private val notes: List<Note>
) : RecyclerView.Adapter<ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(
            R.layout.item_note_list,
            parent,
            false
        )
        return ViewHolder(itemView, context)
    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.title?.text = note.title
        holder.notePosition = position
    }
}