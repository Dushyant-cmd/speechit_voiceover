package com.bytezaptech.texttospeech.data.repository.introduction

import com.bytezaptech.texttospeech.data.repository.ApiResponse
import com.bytezaptech.texttospeech.domain.repository.IntroductionRepository

class IntroductionRepositoryImpl: IntroductionRepository {
    override fun saveUserDetails(): ApiResponse {
        return ApiResponse.Success("success")
    }
}