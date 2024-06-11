package com.example.backendspring.controllers

import com.example.backendspring.models.DiagnosisModel
import com.example.backendspring.services.DiagnosisService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/diagnoses")
class DiagnosesController @Autowired constructor(private val diagnosisService: DiagnosisService) {

    @GetMapping
    fun getAllDiagnoses(): ResponseEntity<List<DiagnosisModel>> {
        return ResponseEntity.ok(diagnosisService.getAllDiagnoses())
    }

    @PostMapping
    fun createDiagnosis(@RequestBody diagnosis: DiagnosisModel): ResponseEntity<DiagnosisModel> {
        return ResponseEntity.status(HttpStatus.CREATED).body(diagnosisService.createDiagnosis(diagnosis))
    }

    @GetMapping("/{id}")
    fun getDiagnosisById(@PathVariable id: Long): ResponseEntity<DiagnosisModel> {
        return ResponseEntity.ok(diagnosisService.getDiagnosisById(id))
    }

    @PutMapping("/{id}")
    fun updateDiagnosis(@PathVariable id: Long, @RequestBody diagnosis: DiagnosisModel): ResponseEntity<DiagnosisModel> {
        return ResponseEntity.ok(diagnosisService.updateDiagnosis(id, diagnosis))
    }

    @DeleteMapping("/{id}")
    fun deleteDiagnosis(@PathVariable id: Long): ResponseEntity<Void> {
        diagnosisService.deleteDiagnosis(id)
        return ResponseEntity.noContent().build()
    }
}
