package com.example.dao

import com.example.models.DiagnosisModel
import java.sql.Connection
import java.sql.ResultSet

class DiagnosisDao(private val connection: Connection) {

    fun getAllDiagnoses(): List<DiagnosisModel> {
        val diagnoses = mutableListOf<DiagnosisModel>()
        val statement = connection.prepareStatement("SELECT * FROM diagnoses")
        val resultSet: ResultSet = statement.executeQuery()

        while (resultSet.next()) {
            diagnoses.add(mapRowToDiagnosis(resultSet))
        }

        return diagnoses
    }

    fun createDiagnosis(diagnosis: DiagnosisModel) {
        val statement = connection.prepareStatement(
            "INSERT INTO diagnoses (appointmentId, description) VALUES (?, ?)"
        )
        statement.setLong(1, diagnosis.appointmentId)
        statement.setString(2, diagnosis.description)
        statement.executeUpdate()
    }

    fun getDiagnosisById(id: Long): DiagnosisModel? {
        val statement = connection.prepareStatement("SELECT * FROM diagnoses WHERE id = ?")
        statement.setLong(1, id)
        val resultSet: ResultSet = statement.executeQuery()

        return if (resultSet.next()) {
            mapRowToDiagnosis(resultSet)
        } else {
            null
        }
    }

    fun updateDiagnosis(id: Long, diagnosis: DiagnosisModel) {
        val statement = connection.prepareStatement(
            "UPDATE diagnoses SET appointmentId = ?, description = ? WHERE id = ?"
        )
        statement.setLong(1, diagnosis.appointmentId)
        statement.setString(2, diagnosis.description)
        statement.setLong(3, id)
        statement.executeUpdate()
    }

    fun deleteDiagnosis(id: Long) {
        val statement = connection.prepareStatement("DELETE FROM diagnoses WHERE id = ?")
        statement.setLong(1, id)
        statement.executeUpdate()
    }

    private fun mapRowToDiagnosis(resultSet: ResultSet): DiagnosisModel {
        return DiagnosisModel(
            id = resultSet.getLong("id"),
            appointmentId = resultSet.getLong("appointmentId"),
            description = resultSet.getString("description")
        )
    }
}
