package com.bytezaptech.texttospeech.domain.repository

import com.bytezaptech.texttospeech.data.repository.ApiResponse

interface IntroductionRepository {
    fun saveUserDetails(): ApiResponse
}