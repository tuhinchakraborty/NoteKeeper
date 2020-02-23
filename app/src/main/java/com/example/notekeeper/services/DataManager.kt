package com.example.notekeeper.services

import com.example.notekeeper.models.Note
import com.example.notekeeper.models.User

object DataManager {

    val users = HashMap<Long, User>()
    val notes = ArrayList<Note>()

    init {
        initializeUsers()
        initializeNotes()
    }

    private fun initializeUsers() {
        val userOne = User(1L, "user one")
        val userTwo = User(2L, "user two")

        users.set(userOne.userId, userOne)
        users.set(userTwo.userId, userTwo)
    }

    private fun initializeNotes() {
        val noteOne = Note(1L, "First Note", "This is a note", users[1L]!!)
        val noteTwo = Note(2L, "Second Note", "This is a note", users[2L]!!)
        val noteThree = Note(2L, "Third Note", "This is a note", users[2L]!!)
        val noteFour = Note(2L, "Fourth Note", "This is a note", users[2L]!!)
        val noteFive = Note(2L, "Fifth Note", "This is a note", users[2L]!!)

        notes.add(noteOne)
        notes.add(noteTwo)
        notes.add(noteThree)
        notes.add(noteFour)
        notes.add(noteFive)
    }

    fun addNote(title: String, text: String): Int {
        val note = Note(3L, title, text, users[1L]!!)
        notes.add(note)
        return notes.lastIndex
    }

    fun findNote(title: String): List<Note> {
        return notes.filter {
            it.title == title
        }
    }
}