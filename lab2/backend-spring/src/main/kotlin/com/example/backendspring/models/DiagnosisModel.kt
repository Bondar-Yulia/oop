package com.example.backendspring.models

data class DiagnosisModel(
    val id: Long,
    val appointmentId: Long,
    val description: String
)
