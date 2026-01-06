package com.bytezaptech.texttospeech.data.local.room.entity

data class UserDetailsEntity(
    val name: String = "Guest",
    val useCase: String = "Daily",
    val playbackSpeed: Float = 1f,
)