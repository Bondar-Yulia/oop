package com.example.backendspring.services

import com.example.backendspring.models.DiagnosisModel
import org.springframework.stereotype.Service

@Service
class DiagnosisService {

    private val diagnoses = mutableListOf<DiagnosisModel>()

    fun getAllDiagnoses(): List<DiagnosisModel> {
        return diagnoses
    }

    fun createDiagnosis(diagnosis: DiagnosisModel): DiagnosisModel {
        diagnoses.add(diagnosis)
        return diagnosis
    }

    fun getDiagnosisById(id: Long): DiagnosisModel? {
        return diagnoses.find { it.id == id }
    }

    fun updateDiagnosis(id: Long, diagnosis: DiagnosisModel): DiagnosisModel? {
        val index = diagnoses.indexOfFirst { it.id == id }
        if (index != -1) {
            diagnoses[index] = diagnosis
            return diagnosis
        }
        return null
    }

    fun deleteDiagnosis(id: Long) {
        diagnoses.removeIf { it.id == id }
    }
}
