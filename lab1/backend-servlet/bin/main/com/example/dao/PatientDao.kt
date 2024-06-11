package com.example.dao

import com.example.models.PatientModel
import java.sql.Connection
import java.sql.ResultSet

class PatientDao(private val connection: Connection) {

    fun getAllPatients(): List<PatientModel> {
        val patients = mutableListOf<PatientModel>()
        val statement = connection.prepareStatement("SELECT * FROM patients")
        val resultSet: ResultSet = statement.executeQuery()

        while (resultSet.next()) {
            patients.add(mapRowToPatient(resultSet))
        }

        return patients
    }

    fun createPatient(patient: PatientModel) {
        val statement = connection.prepareStatement(
            "INSERT INTO patients (name, birthdate) VALUES (?, ?)"
        )
        statement.setString(1, patient.name)
        statement.setString(2, patient.birthdate)
        statement.executeUpdate()
    }

    fun getPatientById(id: Long): PatientModel? {
        val statement = connection.prepareStatement("SELECT * FROM patients WHERE id = ?")
        statement.setLong(1, id)
        val resultSet: ResultSet = statement.executeQuery()

        return if (resultSet.next()) {
            mapRowToPatient(resultSet)
        } else {
            null
        }
    }

    fun updatePatient(id: Long, patient: PatientModel) {
        val statement = connection.prepareStatement(
            "UPDATE patients SET name = ?, birthdate = ? WHERE id = ?"
        )
        statement.setString(1, patient.name)
        statement.setString(2, patient.birthdate)
        statement.setLong(3, id)
        statement.executeUpdate()
    }

    fun deletePatient(id: Long) {
        val statement = connection.prepareStatement("DELETE FROM patients WHERE id = ?")
        statement.setLong(1, id)
        statement.executeUpdate()
    }

    private fun mapRowToPatient(resultSet: ResultSet): PatientModel {
        return PatientModel(
            id = resultSet.getLong("id"),
            name = resultSet.getString("name"),
            birthdate = resultSet.getString("birthdate")
        )
    }
}
