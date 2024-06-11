package com.example.dao

import com.example.models.AppointmentModel
import java.sql.Connection
import java.sql.ResultSet

class AppointmentDao(private val connection: Connection) {

    fun getAllAppointments(): List<AppointmentModel> {
        val appointments = mutableListOf<AppointmentModel>()
        val statement = connection.prepareStatement("SELECT * FROM appointments")
        val resultSet: ResultSet = statement.executeQuery()

        while (resultSet.next()) {
            appointments.add(mapRowToAppointment(resultSet))
        }

        return appointments
    }

    fun createAppointment(appointment: AppointmentModel) {
        val statement = connection.prepareStatement(
            "INSERT INTO appointments (patientId, doctorId, date, diagnosis, prescription) VALUES (?, ?, ?, ?, ?)"
        )
        statement.setLong(1, appointment.patientId)
        statement.setLong(2, appointment.doctorId)
        statement.setString(3, appointment.date)
        statement.setString(4, appointment.diagnosis)
        statement.setString(5, appointment.prescription)
        statement.executeUpdate()
    }

    fun getAppointmentById(id: Long): AppointmentModel? {
        val statement = connection.prepareStatement("SELECT * FROM appointments WHERE id = ?")
        statement.setLong(1, id)
        val resultSet: ResultSet = statement.executeQuery()

        return if (resultSet.next()) {
            mapRowToAppointment(resultSet)
        } else {
            null
        }
    }

    fun updateAppointment(id: Long, appointment: AppointmentModel) {
        val statement = connection.prepareStatement(
            "UPDATE appointments SET patientId = ?, doctorId = ?, date = ?, diagnosis = ?, prescription = ? WHERE id = ?"
        )
        statement.setLong(1, appointment.patientId)
        statement.setLong(2, appointment.doctorId)
        statement.setString(3, appointment.date)
        statement.setString(4, appointment.diagnosis)
        statement.setString(5, appointment.prescription)
        statement.setLong(6, id)
        statement.executeUpdate()
    }

    fun deleteAppointment(id: Long) {
        val statement = connection.prepareStatement("DELETE FROM appointments WHERE id = ?")
        statement.setLong(1, id)
        statement.executeUpdate()
    }

    private fun mapRowToAppointment(resultSet: ResultSet): AppointmentModel {
        return AppointmentModel(
            id = resultSet.getLong("id"),
            patientId = resultSet.getLong("patientId"),
            doctorId = resultSet.getLong("doctorId"),
            date = resultSet.getString("date"),
            diagnosis = resultSet.getString("diagnosis"),
            prescription = resultSet.getString("prescription")
        )
    }
}
