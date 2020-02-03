package com.example.notekeeper.models

class Note(
    val noteId: Long,
    val title: String,
    val text: String,
    val user: User
)

class User(
    val userId: Long,
    val username: String
)