package com.example.notekeeper.models

data class Note(
    val noteId: Long? = null,
    var title: String? = null,
    var text: String? = null,
    val user: User? = null
)

class User(
    val userId: Long,
    val username: String
)