package com.bytezaptech.texttospeech.data.repository

import android.R.attr.data

sealed class ApiResponse() {
    data class Success<T>(val data: T): ApiResponse()
    data class Error(val error: String): ApiResponse()
    object Progress: ApiResponse()
}