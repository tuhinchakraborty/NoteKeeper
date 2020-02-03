package com.example.notekeeper.activities

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.notekeeper.MainActivity
import com.example.notekeeper.R
import com.example.notekeeper.services.DataManager

import kotlinx.android.synthetic.main.activity_note_list.*
import kotlinx.android.synthetic.main.content_note_list.*

class NoteListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val listOfNotes = DataManager.notes.map {
            it.title
        }.toList()

        noteList.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listOfNotes
        )
    }

}
