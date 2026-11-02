package com.example.prediksimahasiswa.data.api

import com.example.prediksimahasiswa.data.model.PredictionResponse
import com.example.prediksimahasiswa.data.model.StudentRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface PredictionApi {

    @POST("predict")
    suspend fun predict(
        @Body data: StudentRequest
    ): PredictionResponse
}