package com.example.prutc.peershare_bank.data.repository

data class DataWrapper<out T>(val data: T?,
                              val error: Throwable?,
                              val statusCode: Int?,
                              val message: String?)