package com.example.notekeeper.adapters.views

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notekeeper.R
import com.example.notekeeper.activities.NoteActivity

class ViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {
    val title = itemView.findViewById<TextView?>(R.id.noteTitle)
    var notePosition = -1

    init {
        itemView.setOnClickListener {
            val intent = Intent(context, NoteActivity::class.java)
            intent.putExtra("EXTRA_NOTE_POSITION", notePosition)
            context.startActivity(intent)
        }
    }
}