package com.example.notekeeper

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.notekeeper.models.Note
import com.example.notekeeper.services.DataManager
import com.google.android.material.snackbar.Snackbar

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    var position: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        position = getNotePosition(savedInstanceState)

        if (position != -1)
            displayNote(position)
        else
            createNote()

        Log.d(this::class.simpleName, "onCreate")
    }

    override fun onPause() {
        saveNote()
        super.onPause()
        Log.d(this::class.simpleName, "onPause")
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (position >= DataManager.notes.lastIndex) {
            val nextMenuItem = menu?.findItem(R.id.action_next)
            if (nextMenuItem != null) nextMenuItem.icon = getDrawable(R.drawable.ic_block_black_24dp)
        } else if (position <= 0) {
            val previousMenuItem = menu?.findItem(R.id.action_back)
            if (previousMenuItem != null) previousMenuItem.icon = getDrawable(R.drawable.ic_block_black_24dp)
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_next -> {
                if (position < DataManager.notes.lastIndex) moveNext()
                else Snackbar.make(noteTitleText, "No More Notes", Snackbar.LENGTH_LONG).show()
                true
            }
            R.id.action_back -> {
                if (position > 0) moveBack()
                else Snackbar.make(noteTitleText, "No More Notes", Snackbar.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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
        Log.i(this::class.simpleName, "Note Position $position")
        val note = DataManager.notes[position]
        noteTitleText.setText(note.title)
        noteBodyText.setText(note.text)
    }

    private fun createNote() {
        DataManager.notes.add(Note())
        position = DataManager.notes.lastIndex
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

    private fun moveBack() {
        displayNote(-- position)
        invalidateOptionsMenu()
    }

}
