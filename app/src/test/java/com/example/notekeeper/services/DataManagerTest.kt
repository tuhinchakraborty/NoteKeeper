package com.example.notekeeper.services

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DataManagerTest {

    @Test
    fun shouldAddNote() {
        val title = "A Title"
        val text = "A note text"

        val index = DataManager.addNote(title, text)

        assertEquals(DataManager.notes.size - 1, index)
        assertEquals(title, DataManager.notes.get(index).title)
        assertEquals(text, DataManager.notes.get(index).text)
    }

    @Test
    fun shouldFindNote() {
        val title = "A Title"
        val text = "A note text"
        DataManager.addNote(title, text)

        val foundNote = DataManager.findNote(title)

        assertEquals(1, foundNote.size)
        assertEquals(title, foundNote[0].title)
    }
}