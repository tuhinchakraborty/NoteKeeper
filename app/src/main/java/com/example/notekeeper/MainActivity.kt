package com.example.notekeeper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.notekeeper.models.Note
import com.example.notekeeper.services.DataManager

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        position = getNotePosition(savedInstanceState)

        if (position != -1) displayNote(position) else {
            DataManager.notes.add(Note())
            position = DataManager.notes.lastIndex
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                moveNext()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (position >= DataManager.notes.lastIndex) {
            val menuItem = menu?.findItem(R.id.action_next)
            if (menuItem != null) {
                menuItem.icon = getDrawable(R.drawable.ic_block_black_24dp)
                menuItem.isEnabled = false
            }
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("EXTRA_NOTE_POSITION", position)
    }

    private fun getNotePosition(savedInstanceState: Bundle?): Int =
        savedInstanceState?.getInt("EXTRA_NOTE_POSITION", -1)
            ?: intent.getIntExtra(
                "EXTRA_NOTE_POSITION",
                -1
            )

    private fun displayNote(position: Int) {
        val note = DataManager.notes[position]
        noteTitleText.setText(note.title)
        noteBodyText.setText(note.text)
    }

    override fun onPause() {
        saveNote()
        super.onPause()
    }

    private fun saveNote() {
        val note = DataManager.notes[position]
        note.title = noteTitleText.text.toString()
        note.text = noteBodyText.text.toString()
    }

    private fun moveNext() {
        displayNote(++ position)
        invalidateOptionsMenu()
    }

}
