package com.example.prediksimahasiswa.data.model

data class PredictionResponse(
    val prediction: String,
    val probabilities: Map<String, Double>
)