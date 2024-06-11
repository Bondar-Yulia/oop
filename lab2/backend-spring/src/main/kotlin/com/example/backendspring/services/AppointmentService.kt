package com.example.backendspring.services

import com.example.backendspring.models.AppointmentModel
import org.springframework.stereotype.Service

@Service
class AppointmentService {

    private val appointments = mutableListOf<AppointmentModel>()

    fun getAllAppointments(): List<AppointmentModel> {
        return appointments
    }

    fun createAppointment(appointment: AppointmentModel): AppointmentModel {
        appointments.add(appointment)
        return appointment
    }

    fun getAppointmentById(id: Long): AppointmentModel? {
        return appointments.find { it.id == id }
    }

    fun updateAppointment(id: Long, appointment: AppointmentModel): AppointmentModel? {
        val index = appointments.indexOfFirst { it.id == id }
        if (index != -1) {
            appointments[index] = appointment
            return appointment
        }
        return null
    }

    fun deleteAppointment(id: Long) {
        appointments.removeIf { it.id == id }
    }
}
