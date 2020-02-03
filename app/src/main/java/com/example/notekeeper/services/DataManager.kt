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
        val noteUserOne = Note(1L, "First Note", "This is a note", users[1L]!!)
        val noteUserTwo = Note(2L, "Second Note", "This is a note", users[2L]!!)

        notes.add(noteUserOne)
        notes.add(noteUserTwo)
    }
}