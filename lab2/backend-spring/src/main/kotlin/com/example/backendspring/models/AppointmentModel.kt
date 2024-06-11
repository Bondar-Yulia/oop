package com.example.backendspring.models

data class AppointmentModel(
    val id: Long,
    val patientId: Long,
    val doctorId: Long,
    val date: String,
    val diagnosis: String,
    val prescription: String
)
