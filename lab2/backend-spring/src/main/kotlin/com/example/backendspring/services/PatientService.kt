package com.example.backendspring.services

import com.example.backendspring.models.PatientModel
import org.springframework.stereotype.Service

@Service
class PatientService {

    private val patients = mutableListOf<PatientModel>()

    fun getAllPatients(): List<PatientModel> {
        return patients
    }

    fun createPatient(patient: PatientModel): PatientModel {
        patients.add(patient)
        return patient
    }

    fun getPatientById(id: Long): PatientModel? {
        return patients.find { it.id == id }
    }

    fun updatePatient(id: Long, patient: PatientModel): PatientModel? {
        val index = patients.indexOfFirst { it.id == id }
        if (index != -1) {
            patients[index] = patient
            return patient
        }
        return null
    }

    fun deletePatient(id: Long) {
        patients.removeIf { it.id == id }
    }
}
