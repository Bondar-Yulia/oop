package com.example.backendspring.controllers

import com.example.backendspring.models.PatientModel
import com.example.backendspring.services.PatientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/patients")
class PatientsController @Autowired constructor(private val patientService: PatientService) {

    @GetMapping
    fun getAllPatients(): ResponseEntity<List<PatientModel>> {
        return ResponseEntity.ok(patientService.getAllPatients())
    }

    @PostMapping
    fun createPatient(@RequestBody patient: PatientModel): ResponseEntity<PatientModel> {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.createPatient(patient))
    }

    @GetMapping("/{id}")
    fun getPatientById(@PathVariable id: Long): ResponseEntity<PatientModel> {
        return ResponseEntity.ok(patientService.getPatientById(id))
    }

    @PutMapping("/{id}")
    fun updatePatient(@PathVariable id: Long, @RequestBody patient: PatientModel): ResponseEntity<PatientModel> {
        return ResponseEntity.ok(patientService.updatePatient(id, patient))
    }

    @DeleteMapping("/{id}")
    fun deletePatient(@PathVariable id: Long): ResponseEntity<Void> {
        patientService.deletePatient(id)
        return ResponseEntity.noContent().build()
    }
}
